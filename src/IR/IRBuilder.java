package IR;

import AST.ASTVisitor;
import AST.DefNode.*;
import AST.ExprNode.*;
import AST.RootNode.*;
import AST.StmtNode.*;
import AST.TypeNode.*;
import IR.Instruction.Instruction;
import IR.Instruction.InstructionRet;
import IR.Operand.StringConst;
import IR.Operand.Value;
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
                if(function.paraList != null){
                    for (var i : function.paraList)
                        functionType.addPara(getType(i.type),i.name);
                }
                IRFunction newFunction = new IRFunction("_ir_f_" + name,functionType);
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
                    if(func_node.typename == null){
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
                    functionTable.put(func_name,newFunction);
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



    @Override
    public void visit(RootNode it) {

    }

    @Override
    public void visit(ProgramSectionNode it) {

    }

    @Override
    public void visit(BlockStmtNode it) {

    }

    @Override
    public void visit(ifStmtNode it) {

    }

    @Override
    public void visit(ReturnStmtNode it) {

    }

    @Override
    public void visit(WhileStmtNode it) {

    }

    @Override
    public void visit(varDefNode it) {

    }

    @Override
    public void visit(ForStmtNode it) {

    }

    @Override
    public void visit(pureExprStmtNode it) {

    }

    @Override
    public void visit(varDefStmtNode it) {

    }

    @Override
    public void visit(ContinueStmtNode it) {

    }

    @Override
    public void visit(BreakStmtNode it) {

    }

    @Override
    public void visit(TypeNode it) {

    }

    @Override
    public void visit(MemberExprNode it) {

    }

    @Override
    public void visit(MemberFuncExprNode it) {

    }

    @Override
    public void visit(CreateExprNode it) {

    }

    @Override
    public void visit(IndexExprNode it) {

    }

    @Override
    public void visit(FunctionExprNode it) {

    }

    @Override
    public void visit(LambdaExprNode it) {

    }

    @Override
    public void visit(SuffixExprNode it) {

    }

    @Override
    public void visit(PrefixExprNode it) {

    }

    @Override
    public void visit(AssignExprNode it) {

    }

    @Override
    public void visit(BinaryExprNode it) {

    }

    @Override
    public void visit(FuncDefNode it) {

    }

    @Override
    public void visit(ClassDefNode it) {

    }

    @Override
    public void visit(varDeclarationNode it) {

    }

    @Override
    public void visit(ConstNode it) {

    }

    @Override
    public void visit(AtomExprNode it) {

    }
}
