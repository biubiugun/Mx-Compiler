package IR;

import AST.ASTNode;
import AST.ASTVisitor;
import AST.DefNode.*;
import AST.ExprNode.*;
import AST.RootNode.*;
import AST.StmtNode.*;
import AST.TypeNode.*;
import IR.Instruction.*;
import IR.Operand.*;
import IR.TypeSystem.*;
import Util.GlobalScope;
import IR.Instruction.InstructionBinary.Operation;
import IR.Instruction.InstructionIcmp.cmp_method;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class IRBuilder implements ASTVisitor {
    public GlobalScope gScope;
    public IRScope nowScope;
    public HashMap<String, IRType> typeTable;
    public HashMap<String, IRFunction> functionTable;
    public HashMap<String, StringConst> stringTable;
    public LinkedList<varDeclarationNode> globalInitList;
    public IRBasicBlock nowBlock;
    public IRFunction nowFunction;
    public StructType nowClass;

    private final Stack<IRBasicBlock> continueBlocks;
    private final Stack<IRBasicBlock> breakBlocks;

    public IRModule build_module;

    public IRBuilder(IRModule _module, GlobalScope _gScope){
        build_module = _module;
        gScope = _gScope;
        typeTable = new HashMap<>();
        functionTable = new HashMap<>();
        stringTable = new HashMap<>();
        globalInitList = new LinkedList<>();
        nowScope = new IRScope(null, IRScope.scope_type.Global);
        nowBlock = null;nowFunction = null;nowClass = null;
        continueBlocks = new Stack<>();
        breakBlocks = new Stack<>();
        builder_initialize();
    }

    private IRType getType(TypeNode AST_type){
        IRType type = typeTable.get(AST_type.typename);
        if(AST_type.dim != 0){
            type = new PointerType(type,AST_type.dim);
        }
        return type;
    }

    private void addCtrl(IRBasicBlock now_block, IRBasicBlock true_block, IRBasicBlock false_block, Value cond_flag){
        assert !cond_flag.type.equals(new IntegerType(1));
        Value cond = new InstructionTrunc(cond_flag,new IntegerType(1),now_block);
        new InstructionBr(now_block,true_block,false_block,cond);
    }

    private Value getStrPtr(Value str){
        assert str instanceof StringConst;
        InstructionGetelementptr ptr = new InstructionGetelementptr(new PointerType(new IntegerType(8),1),str,nowBlock);
        ptr.addIndex(new IntegerConst(0)).addIndex(new IntegerConst(0));
        return ptr;
    }

    private Value setArraySize(Value address){
        Value i32_pointer = new InstructionBitcast(address,new PointerType(new IntegerType(32),1),nowBlock);
        InstructionGetelementptr bias_address = new InstructionGetelementptr(new PointerType(new IntegerType(32),1),i32_pointer,nowBlock);
        bias_address.addIndex(new IntegerConst(-1));
        return new InstructionLoad("array_size",bias_address,nowBlock);
    }

    private Value getAddress(ASTNode node){
        if((node instanceof ConstNode && ((ConstNode)node).type.equals(ConstNode.constType.Identifier)) || (node instanceof AtomExprNode && ((AtomExprNode)node).constNode.type == ConstNode.constType.Identifier)){
            String name;
            if(node instanceof ConstNode)name = ((ConstNode) node).name;
            else name = ((AtomExprNode)node).constNode.name;
            Value returnValue = nowScope.getValue(name);
            if(nowScope.isClassMember(name)){
                assert nowClass != null;
                assert nowFunction != null;
                Value this_ptr = nowScope.getValue("_this");
                assert this_ptr != null;
                this_ptr = new InstructionLoad("_this",this_ptr,nowBlock);
                returnValue = new InstructionGetelementptr(new PointerType(nowClass.typeTable.get(name),1),this_ptr,nowBlock);
                ((InstructionGetelementptr)returnValue).addIndex(new IntegerConst(0)).addIndex(new IntegerConst(nowClass.indexTable.get(name)));
            }
            return returnValue;
        }else if(node instanceof MemberExprNode){
            ((MemberExprNode)node).objExpr.accept(this);
            Value objAddress = ((MemberExprNode)node).objExpr.IROperand;
            StructType objType = (StructType) objAddress.type.dePointed();
            InstructionGetelementptr returnValue = new InstructionGetelementptr(new PointerType(objType.typeTable.get(((MemberExprNode) node).member_name),1),objAddress,nowBlock);
            returnValue.addIndex(new IntegerConst(0)).addIndex(new IntegerConst(objType.indexTable.get(((MemberExprNode) node).member_name)));
            return returnValue;
        }else if(node instanceof IndexExprNode){
            Value ptrAddress = getAddress(((IndexExprNode)node).objExpr);
            Value address = new InstructionLoad("_array",ptrAddress,nowBlock);
            ((IndexExprNode)node).indexExpr.accept(this);
            InstructionGetelementptr biasAddress = new InstructionGetelementptr(address.type,address,nowBlock);
            biasAddress.addIndex(((IndexExprNode)node).indexExpr.IROperand);
            return biasAddress;
        }else if(node instanceof SuffixExprNode || node instanceof PrefixExprNode){
            if(node instanceof SuffixExprNode){
                return getAddress(((SuffixExprNode)node).obj_name);
            }else {
                return getAddress(((PrefixExprNode)node).obj_name);
            }
        }else throw new RuntimeException("[Debug]:getAddress ASTNode type fault");
    }

    private Value Malloc(IRType targetType, Value byteSize){
        IRFunction malloc = functionTable.get("_malloc");
        Value returnValue = new InstructionCall(malloc,nowBlock);
        ((InstructionCall)returnValue).addArg(byteSize);
        malloc.beenUsed = true;
        if(!targetType.equals(returnValue.type)){
            returnValue = new InstructionBitcast(returnValue,targetType,nowBlock);
        }
        return returnValue;
    }

    private Value recursively_create(LinkedList<ExprNode> initList, IRType targetType){
        IRType elementType = targetType.dePointed();
        //get element number
        initList.getFirst().accept(this);
        Value element_number = initList.getFirst().IROperand;
        initList.removeFirst();
        Value elementByteSize = new InstructionBinary(nowBlock,element_number,new IntegerConst(elementType.byteSize()),Operation.mul);
        //add array byte size
        Value totalByteSize = new InstructionBinary(nowBlock,elementByteSize,new IntegerConst(4),Operation.add);
        Value i32Pointer = Malloc(new PointerType(new IntegerType(32),1),totalByteSize);
        new InstructionStore(element_number,i32Pointer,nowBlock);
        InstructionGetelementptr biasPointer = new InstructionGetelementptr(new PointerType(new IntegerType(32),1),i32Pointer,nowBlock);
        biasPointer.addIndex(new IntegerConst(1));
        Value realPointer = new InstructionBitcast(biasPointer,targetType,nowBlock);
        if(initList.isEmpty())return realPointer;
        //enrolling loop
        Value ptrAddress = new InstructionAlloc("array_ptr_",new IntegerType(32),nowFunction.blockList.get(0));
        new InstructionStore(new IntegerConst(0),ptrAddress,nowBlock);
        IRBasicBlock conditionBlock = new IRBasicBlock("array_new_condition",nowFunction);
        IRBasicBlock loopBlock = new IRBasicBlock("array_new_body",nowFunction);
        IRBasicBlock termBlock = new IRBasicBlock(nowFunction.name,nowFunction);
        new InstructionBr(nowBlock,conditionBlock);
        nowBlock = conditionBlock;
        Value ptr = new InstructionLoad("array_ptr",ptrAddress,nowBlock);
        Value branch_flag = new InstructionIcmp(nowBlock,ptr,element_number,cmp_method.ne);
        new InstructionBr(nowBlock,loopBlock,termBlock,branch_flag);
        nowBlock = loopBlock;
        InstructionGetelementptr element_ptr = new InstructionGetelementptr(targetType,realPointer,nowBlock);
        element_ptr.addIndex(ptr);
        Value element = recursively_create(initList,targetType);
        new InstructionStore(element,element_ptr,nowBlock);
        new InstructionStore(new InstructionBinary(nowBlock,ptr,new IntegerConst(1),Operation.add),ptrAddress,nowBlock);
        new InstructionBr(nowBlock,conditionBlock);
        nowBlock = termBlock;
        return realPointer;
    }

    public void process_initialize(){
        if(globalInitList.isEmpty())return;
        IRFunction IREntryFunction = new IRFunction("_GLOBAL_", new FunctionType(new VoidType()));
        IRBasicBlock MainBlock = new IRBasicBlock(IREntryFunction.name,IREntryFunction);
        globalInitList.forEach(var -> {
            IRFunction tmpFunction = new IRFunction("_global_var_init", new FunctionType(new VoidType()));
            Value address = nowScope.getValue(var.name);
            nowFunction = tmpFunction;
            nowBlock = new IRBasicBlock(var.name,nowFunction);
            IRBasicBlock exitBlock = new IRBasicBlock(var.name,nowFunction);
            new InstructionRet(new Value("anonymous",new VoidType()),exitBlock);
            Value tmpValue;
            if(var.init == null)
                tmpValue = new NullConst();
            else {
                var.init.accept(this);
                tmpValue = var.init.IROperand;
            }
            if(tmpValue instanceof NullConst)tmpValue.type = getType(var.type);
            if(tmpValue instanceof StringConst)tmpValue = getStrPtr(tmpValue);
            new InstructionStore(tmpValue,address,nowBlock);
            new InstructionBr(nowBlock,exitBlock);
            build_module.addInitFunc(nowFunction);
            new InstructionCall(tmpFunction,MainBlock);
        });
        new InstructionRet(new Value("anonymous",new VoidType()),MainBlock);
        build_module.addInitFunc(IREntryFunction);
    }

    public void builder_initialize(){
        gScope.classTable.forEach((name,scope) -> {
                    switch (name){
                        case "int" -> typeTable.put(name,new IntegerType(32));
                        case "bool" -> typeTable.put(name,new BoolType());
                        case "string" -> typeTable.put(name,new PointerType(new IntegerType(8),1));
                        default -> {
                            StructType _class = new StructType(name);
                            typeTable.put(name,new PointerType(_class,1));
                            build_module.addClass(_class);
                        }
                    }
                }
        );
        typeTable.put("void",new VoidType());
        gScope.functionTable.forEach((name,function) -> {
                    FunctionType functionType = new FunctionType(getType(function.typename));
                    int w = 0;
                    if(name.equals("_malloc")){
                        w = 1;
                    }
                    if(function.paraList != null){
                        for (var i : function.paraList)
                            functionType.addPara(getType(i.type),i.name);
                    }
                    IRFunction newFunction = new IRFunction("_f_" + name,functionType);
                    newFunction.isBuiltin = function.isBuiltin;
                    functionTable.put(name,newFunction);
                    build_module.addFunc(newFunction);
                }
        );
        gScope.classTable.forEach((name,scope) -> {
            if(!name.equals("int") && !name.equals("bool")){
                IRType pendingType = typeTable.get(name).dePointed();
                assert scope.classTable.isEmpty();
                if(!name.equals("string")){
                    scope.VarTable.forEach((id,type) -> {
                        ((StructType) pendingType).addMember(id,getType(type));
                    });
                }
                scope.functionTable.forEach((func_name,func_node) -> {
                    IRType returnType;
                    if(func_node.typename.typename == null){
                        returnType = new VoidType();
                    }else returnType = getType(func_node.typename);
                    FunctionType funcType = new FunctionType(returnType);
                    IRType argType = new PointerType(pendingType,1);
                    funcType.addPara(argType,"_this");//*this
                    if(func_node.paraList != null){
                        func_node.paraList.forEach(para -> funcType.addPara(getType(para.type),para.name));
                    }
                    IRFunction newFunction = new IRFunction("_class_" + name + "_" + func_name,funcType);
                    newFunction.isBuiltin = func_node.isBuiltin;
                    functionTable.put(newFunction.name,newFunction);
                    build_module.addFunc(newFunction);
                });
                if(!name.equals("string") && !functionTable.containsKey("_class_" + name + "_" + name)){
                    FunctionType funcType = new FunctionType(new VoidType());
                    funcType.addPara(new PointerType(pendingType,1),"_this");
                    IRFunction constructFunction = new IRFunction("_class_" + name + "_" + name,funcType);
                    constructFunction.addPara(new Value("_this_ptr",new PointerType(pendingType,1)));//add this ptr
                    IRBasicBlock constructBlock = new IRBasicBlock(constructFunction.name,constructFunction);
                    new InstructionRet(new Value("anonymous",new VoidType()),constructBlock);
                    functionTable.put(constructFunction.name,constructFunction);
                    build_module.addFunc(constructFunction);
                }
            }
        });

    }

    private Value ShortCircuit(BinaryExprNode node, Value rs1){
        String opString;
        if(node.OP == BinaryExprNode.op.AndAnd)opString = "logic_and";
        else opString = "logic_or";
        Value address = new InstructionAlloc(opString,new BoolType(),nowFunction.blockList.get(0));
        IRBasicBlock directBlock = new IRBasicBlock("_dBlock",nowFunction);
        IRBasicBlock secondBlock = new IRBasicBlock("_sBlock",nowFunction);
        IRBasicBlock terminalBlock = new IRBasicBlock("_tBlock",nowFunction);
        switch (node.OP){
            case AndAnd -> addCtrl(nowBlock,secondBlock,directBlock,rs1);
            case OrOr -> addCtrl(nowBlock,directBlock,secondBlock,rs1);
        }
        nowBlock = directBlock;
        new InstructionStore(rs1,address,nowBlock);
        new InstructionBr(nowBlock,terminalBlock);
        //short circuit didn't pass
        nowBlock = secondBlock;
        node.rExpr.accept(this);
        Value rs2 = node.rExpr.IROperand;
        new InstructionStore(rs2,address,nowBlock);
        new InstructionBr(nowBlock,terminalBlock);
        nowBlock = terminalBlock;
        return new InstructionLoad("circuit",address,nowBlock);
    }

    private Const operation_result_const(BinaryExprNode.op op,Const rs1,Const rs2){
        assert rs1.type == rs2.type;
        Const return_const;
        switch (op){
            case Plus,Minus,Mul,Div,Mod,LeftShift,RightShift,And,Or,Caret,AndAnd,OrOr -> {
                if(rs1 instanceof IntegerConst){
                    int rs1_value = ((IntegerConst)rs1).value;
                    int rs2_value = ((IntegerConst)rs2).value;
                    int result;
                    switch (op){
                        case Plus -> result = rs1_value + rs2_value;
                        case Minus -> result = rs1_value - rs2_value;
                        case Mul -> result = rs1_value * rs2_value;
                        case Div -> result = rs1_value / rs2_value;
                        case Mod -> result = rs1_value % rs2_value;
                        case LeftShift -> result = rs1_value << rs2_value;
                        case RightShift -> result = rs1_value >> rs2_value;
                        case And -> result = rs1_value & rs2_value;
                        case Or -> result = rs1_value | rs2_value;
                        case Caret -> result = rs1_value ^ rs2_value;
                        default -> throw new RuntimeException("[Debug]:operation_result_const wrong");
                    }
                    return_const = new IntegerConst(result);
                }else {
                    boolean rs1_value = ((BoolConst)rs1).value;
                    boolean rs2_value = ((BoolConst)rs2).value;
                    boolean result;
                    switch (op){
                        case AndAnd -> result = rs1_value && rs2_value;
                        case OrOr -> result = rs1_value || rs2_value;
                        default -> throw new RuntimeException("[Debug]:operation_result_const wrong");
                    }
                    return_const = new BoolConst(result);
                }
            }
            case Less,Greater,LessEqual,GreaterEqual,Equal,NotEqual -> {
                if(rs1 instanceof IntegerConst){
                    int rs1_value = ((IntegerConst)rs1).value;
                    int rs2_value = ((IntegerConst)rs2).value;
                    boolean result;
                    switch (op){
                        case Less -> result = rs1_value < rs2_value;
                        case Greater -> result = rs1_value > rs2_value;
                        case LessEqual -> result = rs1_value <= rs2_value;
                        case GreaterEqual -> result = rs1_value >= rs2_value;
                        case Equal -> result = rs1_value == rs2_value;
                        case NotEqual -> result = rs1_value != rs2_value;
                        default -> throw new RuntimeException("[Debug]:operation_result_const wrong");
                    }
                    return_const = new BoolConst(result);
                }else {
                    boolean rs1_value = ((BoolConst)rs1).value;
                    boolean rs2_value = ((BoolConst)rs2).value;
                    boolean result;
                    switch (op){
                        case Equal -> result = rs1_value == rs2_value;
                        case NotEqual -> result = rs1_value != rs2_value;
                        default -> throw new RuntimeException("[Debug]:operation_result_const wrong");
                    }
                    return_const = new BoolConst(result);
                }
            }
            default -> throw new RuntimeException("[Debug]:operation_result_const wrong");
        }
        return return_const;
    }

    @Override
    public void visit(RootNode it) {
        it.NodeList.forEach(node -> node.accept(this));
    }

    @Override
    public void visit(ProgramSectionNode it) {
        it.accept(this);
    }

    @Override
    public void visit(BlockStmtNode it) {
        if(!nowScope.validity)return;
        nowScope = new IRScope(nowScope, IRScope.scope_type.Common);
        if(it.stmts != null){
            it.stmts.forEach(stmt -> stmt.accept(this));
        }
        nowScope = nowScope.Predecessor();
    }

    @Override
    public void visit(ifStmtNode it) {
        if(!nowScope.validity)return;
        it.IROperand = null;
        nowScope = new IRScope(nowScope, IRScope.scope_type.Flow);
        IRBasicBlock trueBlock = new IRBasicBlock("if_then_",nowFunction);//then
        IRBasicBlock termBlock = new IRBasicBlock(nowFunction.name,nowFunction);
        it.condition.accept(this);
        if(it.elseStmt != null){//else
            IRBasicBlock falseBlock = new IRBasicBlock("if_else_",nowFunction);
            addCtrl(nowBlock,trueBlock,falseBlock, it.condition.IROperand);
            nowBlock = falseBlock;
            it.elseStmt.accept(this);
            new InstructionBr(nowBlock,termBlock);//ret
        }else {
            addCtrl(nowBlock,trueBlock,termBlock,it.condition.IROperand);
        }
        nowBlock = trueBlock;
        it.thenStmt.accept(this);
        new InstructionBr(nowBlock,termBlock);
        nowBlock = termBlock;
        nowScope = nowScope.Predecessor();
    }

    @Override
    public void visit(ReturnStmtNode it) {
        if(!nowScope.validity)return;
        if(it.expr != null){
            it.expr.accept(this);
            Value returnValue = it.expr.IROperand;
            if(returnValue instanceof StringConst){
                returnValue = getStrPtr(returnValue);
            }
            if(returnValue instanceof NullConst){
                ((NullConst) returnValue).type = ((FunctionType)nowFunction.type).returnType;
            }
            new InstructionStore(returnValue,nowFunction.returnAddress,nowBlock);
        }
        new InstructionBr(nowBlock,nowFunction.blockList.get(1));//back to the exit_block;
        nowScope.setInvalidity();
    }

    @Override
    public void visit(WhileStmtNode it) {
        if(!nowScope.validity)return;
        nowScope = new IRScope(nowScope, IRScope.scope_type.Flow);
        IRBasicBlock conditionBlock = new IRBasicBlock("while_condition",nowFunction);
        IRBasicBlock loopBlock = new IRBasicBlock("while_loopBody",nowFunction);
        IRBasicBlock termBlock = new IRBasicBlock(nowFunction.name,nowFunction);
        continueBlocks.push(conditionBlock);
        breakBlocks.push(termBlock);
        new InstructionBr(nowBlock,conditionBlock);
        nowBlock = conditionBlock;
        it.condition.accept(this);
        addCtrl(nowBlock,loopBlock,termBlock,it.condition.IROperand);
        nowBlock = loopBlock;
        it.thenStmt.accept(this);
        new InstructionBr(nowBlock,conditionBlock);
        nowBlock = termBlock;
        continueBlocks.pop();breakBlocks.pop();
        nowScope = nowScope.Predecessor();
    }

    @Override
    public void visit(varDefNode it) {
        if(!nowScope.validity)return;
        it.varList.forEach(var -> var.accept(this));
    }

    @Override
    public void visit(ForStmtNode it) {
        if(!nowScope.validity)return;
        nowScope = new IRScope(nowScope, IRScope.scope_type.Flow);
        if(it.init1 != null || it.init2 != null){
            if(it.init1 != null)it.init1.accept(this);
            if(it.init2 != null)it.init2.accept(this);
        }
        IRBasicBlock conditionBlock = new IRBasicBlock("for_condition",nowFunction);
        IRBasicBlock iterBlock = new IRBasicBlock("for_iter",nowFunction);
        IRBasicBlock loopBlock = new IRBasicBlock("for_loopBody",nowFunction);
        IRBasicBlock termBlock = new IRBasicBlock(nowFunction.name,nowFunction);
        continueBlocks.push(iterBlock);breakBlocks.push(termBlock);
        new InstructionBr(nowBlock,conditionBlock);
        nowBlock = conditionBlock;
        if(it.cond != null){
            it.cond.accept(this);
            addCtrl(nowBlock,loopBlock,termBlock,it.cond.IROperand);
        }else {
            new InstructionBr(nowBlock,loopBlock);
        }
        nowBlock = loopBlock;
        if(it.thenStmt != null)it.thenStmt.accept(this);//thenStmt != null
        new InstructionBr(nowBlock,iterBlock);
        nowBlock = iterBlock;
        if(it.iter != null)it.iter.accept(this);
        new InstructionBr(nowBlock,conditionBlock);
        nowBlock = termBlock;
        continueBlocks.pop();breakBlocks.pop();
        nowScope = nowScope.Predecessor();
    }

    @Override
    public void visit(pureExprStmtNode it) {
        if(!nowScope.validity)return;
        it.expr.accept(this);
    }

    @Override
    public void visit(varDefStmtNode it) {
        it.varDef.accept(this);
    }

    @Override
    public void visit(ContinueStmtNode it) {
        if(!nowScope.validity)return;
        IRBasicBlock continueBlock = continueBlocks.peek();
        new InstructionBr(nowBlock,continueBlock);
        nowScope.setInvalidity();
    }

    @Override
    public void visit(BreakStmtNode it) {
        if(!nowScope.validity)return;
        IRBasicBlock breakBlock = breakBlocks.peek();
        new InstructionBr(nowBlock,breakBlock);
        nowScope.setInvalidity();
    }

    @Override
    public void visit(TypeNode it) {
        //just empty
    }

    @Override
    public void visit(MemberExprNode it) {
        if(!nowScope.validity)return;
        it.objExpr.accept(this);
        Value obj_addr = it.objExpr.IROperand;
        StructType obj_type = (StructType) obj_addr.type.dePointed();
        InstructionGetelementptr get_ptr = new InstructionGetelementptr(new PointerType(obj_type.typeTable.get(it.member_name),1),obj_addr,nowBlock);
        get_ptr.addIndex(new IntegerConst(0)).addIndex(new IntegerConst(obj_type.indexTable.get(it.member_name)));
        it.IROperand = new InstructionLoad(it.member_name,get_ptr,nowBlock);
    }

    @Override
    public void visit(MemberFuncExprNode it) {
        if(!nowScope.validity)return;
        IRFunction member_function = null;
        Value this_ptr = null;
        it.objExpr.accept(this);
        this_ptr = it.objExpr.IROperand;
        if(it.objExpr.type.dim != 0){
            it.IROperand = setArraySize(this_ptr);
            return;
        }
        String class_name;
        IRType class_type = this_ptr.type.dePointed();
        if(class_type instanceof StructType){
            class_name = ((StructType) class_type).name;
        }else {
            assert class_type instanceof IntegerType;
            class_name = "class_string";
        }
        int w = 0;
        member_function = functionTable.get("_" + class_name + "_" + it.Func_name);
        assert member_function != null;
        if(it.paraList != null){
            for(int i = 0;i < it.paraList.size(); ++i){
                ASTNode node = it.paraList.get(i);
                node.accept(this);
                Value argument = node.IROperand;
                if(argument instanceof StringConst)argument = getStrPtr(argument);
                if(argument instanceof NullConst)((NullConst)argument).type = ((FunctionType)member_function.type).paraTypeList.get(i);
                node.IROperand = argument;
            }
        }
        InstructionCall newOperand = new InstructionCall(member_function,nowBlock);
        if(this_ptr != null)newOperand.addOperand(this_ptr);
        if(it.paraList != null){
            for(var i : it.paraList){
                newOperand.addArg(i.IROperand);
            }
        }
        member_function.beenUsed = true;
        it.IROperand = newOperand;
    }

    @Override
    public void visit(CreateExprNode it) {
        if(!nowScope.validity)return;
        Value newOperand;
        if(it.dim > 0){
            LinkedList<ExprNode> initList = new LinkedList<>(it.exprList);
            newOperand = recursively_create(initList,new PointerType(getType(it.type),it.dim));
        }else {
            String class_name = it.typename.typename;
            StructType class_type = (StructType) typeTable.get(class_name).dePointed();
            newOperand = Malloc(new PointerType(class_type,1),new IntegerConst(class_type.byteSize()));
            InstructionCall creator = new InstructionCall(functionTable.get("_" + class_type.name + "_" + class_name),nowBlock);
            creator.addArg(newOperand);
        }
        it.IROperand = newOperand;
    }

    @Override
    public void visit(IndexExprNode it) {
        if(!nowScope.validity)return;
        Value address = getAddress(it);
        assert address != null;
        it.IROperand = new InstructionLoad("_array",address,nowBlock);
    }

    @Override
    public void visit(FunctionExprNode it) {
        if(!nowScope.validity)return;
        IRFunction function = null;
        Value this_ptr = null;
        String function_name = it.func_name.content;//func_name is an identifier but not expression
//        if(nowClass != null){
//            function = functionTable.get("_" + nowClass.name + "_" + function_name);
//            this_ptr = nowScope.getValue("_this");
//            assert this_ptr != null;
//            this_ptr = new InstructionLoad("_this",this_ptr,nowBlock);
//        }else {
//            function = functionTable.get(function_name);
//        }
        if(nowClass != null)function = functionTable.get("_" + nowClass.name + "_" + function_name);
        if(function != null){
            this_ptr = nowScope.getValue("_this");
            this_ptr = new InstructionLoad("_this",this_ptr,nowBlock);
        }else function = functionTable.get(function_name);
        assert function != null;
        if(it.paraList != null){
            for(int i = 0;i < it.paraList.size(); ++i){
                ASTNode node = it.paraList.get(i);
                node.accept(this);
                Value argument = node.IROperand;
                if(argument instanceof StringConst)argument = getStrPtr(argument);
                if(argument instanceof NullConst)((NullConst)argument).type = ((FunctionType)function.type).paraTypeList.get(i);
                node.IROperand = argument;
            }
        }
        InstructionCall newOperand = new InstructionCall(function,nowBlock);
        if(this_ptr != null)newOperand.addOperand(this_ptr);
        if(it.paraList != null){
            for(var i : it.paraList){
                newOperand.addArg(i.IROperand);
            }
        }
        function.beenUsed = true;
        it.IROperand = newOperand;
    }

    @Override
    public void visit(LambdaExprNode it) {

    }

    @Override
    public void visit(SuffixExprNode it) {
        if(!nowScope.validity)return;
        it.obj_name.accept(this);
        Value originValue = it.obj_name.IROperand;
        Value address = getAddress(it.obj_name);
        assert address != null;
        Value newValue = null;
        switch (it.OP){
            case SelfPlus -> {
                newValue = new InstructionBinary(nowBlock,originValue,new IntegerConst(1),Operation.add);
            }
            case SelfMinus -> {
                newValue = new InstructionBinary(nowBlock,originValue,new IntegerConst(1),Operation.sub);
            }
        }
        assert newValue != null;
        new InstructionStore(newValue,address,nowBlock);
        it.IROperand = originValue;
    }

    @Override
    public void visit(PrefixExprNode it) {
        if(!nowScope.validity)return;
        it.obj_name.accept(this);
        Value originValue = it.obj_name.IROperand;
        Value newOperand = originValue;
        switch (it.OP){
            case SelfMinus,SelfPlus -> {
                Value address = getAddress(it.obj_name);
                Value newValue = null;
                switch (it.OP){
                    case SelfPlus -> newValue = newOperand = new InstructionBinary(nowBlock,originValue,new IntegerConst(1),Operation.add);
                    case SelfMinus -> newValue = newOperand = new InstructionBinary(nowBlock,originValue,new IntegerConst(-1),Operation.add);
                }
                assert newValue != null;
                assert address != null;
                new InstructionStore(newValue,address,nowBlock);
            }
            case Not,Tilde,Plus,Minus -> {
                if(originValue instanceof Const){
                    switch (it.OP){
                        case Not -> newOperand = new BoolConst(!((BoolConst)originValue).value);
                        case Tilde -> newOperand = new IntegerConst(~((IntegerConst)originValue).value);
                        case Plus -> {/* no need for operation */}
                        case Minus -> newOperand = new IntegerConst(-((IntegerConst)originValue).value);
                    }
                }else {
                    switch (it.OP) {
                        case Not -> newOperand = new InstructionBinary(nowBlock, originValue, new BoolConst(true), Operation.xor);
                        case Tilde -> newOperand = new InstructionBinary(nowBlock, originValue, new IntegerConst(-1), Operation.xor);
                        case Plus -> {/* no need for operation */}
                        case Minus -> newOperand = new InstructionBinary(nowBlock, new IntegerConst(0), originValue, Operation.sub);
                    }
                }
            }
        }
        it.IROperand = newOperand;
    }

    @Override
    public void visit(AssignExprNode it) {
        if(!nowScope.validity)return;
        it.rExpr.accept(this);
        Value rs2 = it.rExpr.IROperand;
        Value address = getAddress(it.lExpr);
        assert address != null;
        if(rs2 instanceof NullConst)((NullConst)rs2).type = address.type.dePointed();
        if(rs2 instanceof StringConst)rs2 = getStrPtr(rs2);
        new InstructionStore(rs2,address,nowBlock);
        it.IROperand = rs2;
    }

    @Override
    public void visit(BinaryExprNode it) {
        if(!nowScope.validity)return;
        Value newOperand = null;
        if(it.OP == BinaryExprNode.op.AndAnd || it.OP == BinaryExprNode.op.OrOr){
            it.lExpr.accept(this);
            Value rs1 = it.lExpr.IROperand;
            switch (it.OP){
                case AndAnd -> {
                    if(rs1 instanceof BoolConst){
                        if(!((BoolConst)rs1).value){
                            newOperand = rs1;
                        }else {
                            it.rExpr.accept(this);
                            newOperand = it.rExpr.IROperand;
                        }
                    }else{
                        newOperand = ShortCircuit(it,rs1);
                    }
                }
                case OrOr -> {
                    if(rs1 instanceof BoolConst){
                        if(((BoolConst)rs1).value){
                            newOperand = rs1;
                        }else {
                            it.rExpr.accept(this);
                            newOperand = it.rExpr.IROperand;
                        }
                    }else{
                        newOperand = ShortCircuit(it,rs1);
                    }
                }
            }
        }else {
            it.lExpr.accept(this);
            it.rExpr.accept(this);
            Value rs1 = it.lExpr.IROperand;
            Value rs2 = it.rExpr.IROperand;
            if(rs1 instanceof StringConst)rs1 = getStrPtr(rs1);
            if(rs2 instanceof StringConst)rs2 = getStrPtr(rs2);
            if(rs1 instanceof Const && rs2 instanceof Const){
                //const result
                newOperand = operation_result_const(it.OP,(Const) rs1,(Const) rs2);
            }else {
                if(rs1.type.equals(new PointerType(new IntegerType(8),1))){
                    assert rs2.type.equals(new PointerType(new IntegerType(8),1));
                    //string operation result
                    IRFunction strFunction;
                    switch (it.OP){
                        case Plus -> {
                            strFunction = functionTable.get("_str_splice");
                            assert strFunction != null;
                            newOperand = new InstructionCall(strFunction,nowBlock);
                            strFunction.beenUsed = true;
                        }
                        case Less -> {
                            strFunction = functionTable.get("_str_slt");
                            assert strFunction != null;
                            newOperand = new InstructionCall(strFunction,nowBlock);
                            strFunction.beenUsed = true;
                        }
                        case Greater -> {
                            strFunction = functionTable.get("_str_sgt");
                            assert strFunction != null;
                            newOperand = new InstructionCall(strFunction,nowBlock);
                            strFunction.beenUsed = true;
                        }
                        case LessEqual -> {
                            strFunction = functionTable.get("_str_sle");
                            assert strFunction != null;
                            newOperand = new InstructionCall(strFunction,nowBlock);
                            strFunction.beenUsed = true;
                        }
                        case GreaterEqual -> {
                            strFunction = functionTable.get("_str_sge");
                            assert strFunction != null;
                            newOperand = new InstructionCall(strFunction,nowBlock);
                            strFunction.beenUsed = true;
                        }
                        case Equal -> {
                            strFunction = functionTable.get("_str_eq");
                            assert strFunction != null;
                            newOperand = new InstructionCall(strFunction,nowBlock);
                            strFunction.beenUsed = true;
                        }
                        case NotEqual -> {
                            strFunction = functionTable.get("_str_ne");
                            assert strFunction != null;
                            newOperand = new InstructionCall(strFunction,nowBlock);
                            strFunction.beenUsed = true;
                        }
                        default -> throw new RuntimeException("[Debug]:IR called string function operator wrong");
                    }
                    ((InstructionCall)newOperand).addArg(rs1).addArg(rs2);
                }else {
                    switch (it.OP){
                        case Mul -> newOperand = new InstructionBinary(nowBlock,rs1,rs2,Operation.mul);
                        case Plus -> newOperand = new InstructionBinary(nowBlock,rs1,rs2,Operation.add);
                        case Minus -> newOperand = new InstructionBinary(nowBlock,rs1,rs2,Operation.sub);
                        case Div -> newOperand = new InstructionBinary(nowBlock,rs1,rs2,Operation.sdiv);
                        case Mod -> newOperand = new InstructionBinary(nowBlock,rs1,rs2,Operation.srem);
                        case LeftShift -> newOperand = new InstructionBinary(nowBlock,rs1,rs2,Operation.shl);
                        case RightShift -> newOperand = new InstructionBinary(nowBlock,rs1,rs2,Operation.ashr);
                        case Caret -> newOperand = new InstructionBinary(nowBlock,rs1,rs2,Operation.xor);
                        case Or -> newOperand = new InstructionBinary(nowBlock,rs1,rs2,Operation.or);
                        case And -> newOperand = new InstructionBinary(nowBlock,rs1,rs2,Operation.and);
                        case Less -> {
                            if(rs2 instanceof NullConst)rs2.type = rs1.type;
                            newOperand = new InstructionIcmp(nowBlock,rs1,rs2,cmp_method.slt);
                            newOperand = new InstructionZext(newOperand,new BoolType(),nowBlock);
                        }
                        case Greater -> {
                            if(rs2 instanceof NullConst)rs2.type = rs1.type;
                            newOperand = new InstructionIcmp(nowBlock,rs1,rs2,cmp_method.sgt);
                            newOperand = new InstructionZext(newOperand,new BoolType(),nowBlock);
                        }
                        case LessEqual -> {
                            if(rs2 instanceof NullConst)rs2.type = rs1.type;
                            newOperand = new InstructionIcmp(nowBlock,rs1,rs2,cmp_method.sle);
                            newOperand = new InstructionZext(newOperand,new BoolType(),nowBlock);
                        }
                        case GreaterEqual -> {
                            if(rs2 instanceof NullConst)rs2.type = rs1.type;
                            newOperand = new InstructionIcmp(nowBlock,rs1,rs2,cmp_method.sge);
                            newOperand = new InstructionZext(newOperand,new BoolType(),nowBlock);
                        }
                        case Equal -> {
                            if(rs2 instanceof NullConst)rs2.type = rs1.type;
                            newOperand = new InstructionIcmp(nowBlock,rs1,rs2,cmp_method.eq);
                            newOperand = new InstructionZext(newOperand,new BoolType(),nowBlock);
                        }
                        case NotEqual -> {
                            if(rs2 instanceof NullConst)rs2.type = rs1.type;
                            newOperand = new InstructionIcmp(nowBlock,rs1,rs2,cmp_method.ne);
                            newOperand = new InstructionZext(newOperand,new BoolType(),nowBlock);
                        }
                        default -> throw new RuntimeException("[Debug] unknown ir operator ");
                    }
                }
            }
        }
        it.IROperand = newOperand;
    }

    @Override
    public void visit(FuncDefNode it) {
        assert nowScope.validity;
        nowFunction = nowClass == null ? functionTable.get(it.func_name) : functionTable.get("_" + nowClass.name + "_" + it.func_name);
        FunctionType funcType = (FunctionType) nowFunction.type;
        nowScope = new IRScope(nowScope, IRScope.scope_type.Function);
        //entryBlock set
        IRBasicBlock entryBlock = new IRBasicBlock(nowFunction.name,nowFunction);
        //exitBlock set
        IRBasicBlock exitBlock = new IRBasicBlock(nowFunction.name,nowFunction);
        Value returnValue;
        if(!funcType.toString().equals("void")){
            nowFunction.returnAddress = new InstructionAlloc("_return",funcType.returnType,nowFunction.blockList.get(0)/* entry block */);
            returnValue = new InstructionLoad("_return",nowFunction.returnAddress,exitBlock);
        }else returnValue = new Value("anonymous",new VoidType());
        new InstructionRet(returnValue,exitBlock);
        nowBlock = nowFunction.blockList.get(0);//entry block
        for(int i = 0;i < funcType.paraNameList.size(); ++i){
            Value arg = new Value("_arg",funcType.paraTypeList.get(i));
            nowFunction.addPara(arg);
            InstructionAlloc real_arg = new InstructionAlloc("_arg",arg.type,nowFunction.blockList.get(0));
            new InstructionStore(arg,real_arg,nowBlock);
            nowScope.setVariable(funcType.paraNameList.get(i),real_arg);
        }
        if(it.stmts != null)it.stmts.stmts.forEach(stmt -> stmt.accept(this));
        if(nowBlock.terminalInst == null)new InstructionBr(nowBlock,nowFunction.blockList.get(1)/* exit block */);
        nowBlock = null;
        nowScope = nowScope.Predecessor();
    }

    @Override
    public void visit(ClassDefNode it) {
        assert nowScope.validity;
        assert nowClass == null;
        nowClass = (StructType) typeTable.get(it.class_name).dePointed();
        nowScope = new IRScope(nowScope, IRScope.scope_type.Class);
        nowClass.indexTable.forEach(
                (id,index) -> nowScope.setVariable(id,new IntegerConst(index))
        );
        if(it.memberFunc != null)it.memberFunc.forEach(func -> func.accept(this));
        nowClass = null;
        nowScope = nowScope.Predecessor();
    }

    @Override
    public void visit(varDeclarationNode it) {
        if(!nowScope.validity)return;
        IRType valueType = getType(it.type);
        Value value;
        if(nowScope.fatherScope == null){
            value = new InstructionGlobal(it.name,valueType);
            build_module.addVar((InstructionGlobal) value);
        }else
            value = new InstructionAlloc(it.name,valueType,nowFunction.blockList.get(0));
        nowScope.setVariable(it.name,value);
        it.IROperand = value;
        if(it.init != null){
            if(nowScope.fatherScope != null){
                it.init.accept(this);
                Value initOperand = it.init.IROperand;
                if(initOperand instanceof NullConst) ((NullConst) initOperand).type = valueType;
                if (initOperand instanceof StringConst) initOperand = getStrPtr(initOperand);
                new InstructionStore(initOperand,value,nowBlock);
            }else
                globalInitList.add(it);
        }else {
            if(it.type.dim > 0){
                if(nowScope.fatherScope != null){
                    new InstructionStore(new NullConst((PointerType) valueType),value,nowBlock);
                }else
                    globalInitList.add(it);
            }
        }
    }

    @Override
    public void visit(ConstNode it) {
        if(!nowScope.validity)return;
        switch (it.type){
            case DecimalInteger -> {
                it.IROperand = new IntegerConst(Integer.parseInt(it.name));
            }
            case True -> {
                it.IROperand = new BoolConst(true);
            }
            case False -> {
                it.IROperand = new BoolConst(false);
            }
            case STRING -> {
                StringConst str = stringTable.get(it.name);
                if(str == null){
                    it.name = it.name.substring(1,it.name.length() - 1)
                            .replace("\\\\","\\")
                            .replace("\\n","\n")
                            .replace("\\\"","\"")
                            .replace("\\t","\t")
                            + "\0";
                    str = new StringConst(it.name);
                    build_module.addStr(str);
                    stringTable.put(it.name,str);
                }
                it.IROperand = str;
            }
            case This -> {
                assert nowClass != null;
                Value this_ptr = nowScope.getValue("_this");
                assert this_ptr != null;
                it.IROperand = new InstructionLoad("_this",this_ptr,nowBlock);
            }
            case NULL -> {
                it.IROperand = new NullConst();
            }
            case Identifier -> {
                it.IROperand = new InstructionLoad(it.name,getAddress(it),nowBlock);
            }
        }
    }

    @Override
    public void visit(AtomExprNode it) {
        it.constNode.accept(this);
        it.IROperand = it.constNode.IROperand;
    }
}
