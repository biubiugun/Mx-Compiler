package Frontend;

import AST.ASTVisitor;
import AST.DefNode.ClassDefNode;
import AST.DefNode.FuncDefNode;
import AST.DefNode.varDeclarationNode;
import AST.DefNode.varDefNode;
import AST.ExprNode.*;
import AST.RootNode.ProgramSectionNode;
import AST.RootNode.RootNode;
import AST.StmtNode.*;
import AST.TypeNode.ConstNode;
import AST.TypeNode.TypeNode;
import Util.GlobalScope;
import Util.Scope;
import Util.error.SemanticError;

public class SemanticChecker implements ASTVisitor {
    GlobalScope gScope;
    Scope currentScope;
    boolean inFunction = false,inClass = false;
    int inLoop = 0,inLambda = 0;

    FuncDefNode currentFunction;
    ClassDefNode currentClass;

    public TypeNode NULL_TYPE = new TypeNode(null,"null");
    public TypeNode INT_TYPE = new TypeNode(null,"int");
    public TypeNode BOOL_TYPE = new TypeNode(null,"bool");
    public TypeNode STRING_TYPE = new TypeNode(null,"string");
    public TypeNode VOID_TYPE = new TypeNode(null,"void");

    TypeNode lambdaType = VOID_TYPE;

    private TypeNode getConstType(ConstNode it){
        switch (it.type){

            case DecimalInteger -> {
                return INT_TYPE;
            }
            case True, False -> {
                return BOOL_TYPE;
            }
            case STRING -> {
                return STRING_TYPE;
            }
            case This -> {
                return new TypeNode(null,"this");
            }
            case NULL -> {
                return NULL_TYPE;
            }
            case Identifier -> {
                return new TypeNode(null,it.name + "'s class");
            }
        }
            throw new SemanticError("none type fit!",it.pos);
    }

    public SemanticChecker(GlobalScope _gScope){
        gScope = _gScope;
        currentScope = gScope;
    }

    @Override
    public void visit(RootNode it) {
        it.NodeList.forEach(node-> node.accept(this));
    }

    @Override
    public void visit(ProgramSectionNode it) {
        it.accept(this);
    }

    @Override
    public void visit(BlockStmtNode it) {
        currentScope = new Scope(currentScope);
        if(it.stmts != null){
            for(var i : it.stmts){
                if(i != null)i.accept(this);
            }
        }
        currentScope = currentScope.parent;
    }

    @Override
    public void visit(ifStmtNode it) {
        if(it.condition == null)throw new SemanticError("if condition is necessary!",it.pos);
        it.condition.accept(this);
        if(!it.condition.type.Equals(BOOL_TYPE))throw new SemanticError("if condition cannot be judged!",it.pos);
        else {
            currentScope = new Scope(currentScope);
            if(it.thenStmt != null)it.thenStmt.accept(this);
            currentScope = currentScope.parent;
        }
        if(it.elseStmt != null){
            currentScope = new Scope(currentScope);
            it.elseStmt.accept(this);
            currentScope = currentScope.parent;
        }
    }

    @Override
    public void visit(ReturnStmtNode it) {
        if(!inFunction && inLambda <= 0)throw new SemanticError("not in function or Lambda but return!",it.pos);
        if(inFunction){
            if (it.expr == null) {
                if (!currentFunction.typename.Equals(VOID_TYPE) && currentFunction.typename.typename != null) {
                    throw new SemanticError("only void and construct function can return nothing!", it.pos);
                }
            } else {
                it.expr.accept(this);
                //deal with "this" case in visit(atomExprNode),let the typename be the class name
                if (!it.expr.type.Equals(currentFunction.typename) && !it.expr.type.Equals(NULL_TYPE)) {
                    System.out.println(it.expr.type);
                    System.out.println(currentFunction.typename.typename);
                    throw new SemanticError("the returnType is wrong!", it.pos);
                }
            }
            currentFunction.hasReturnStmt = true;
        }
        if(inLambda > 0){
            if(it.expr == null)lambdaType = VOID_TYPE;
            else{
                it.expr.accept(this);
                lambdaType = it.expr.type;
            }
        }
    }

    @Override
    public void visit(WhileStmtNode it) {
        inLoop ++;
        if(it.condition == null)throw new SemanticError("while condition is necessary!",it.pos);
        it.condition.accept(this);
        if(!it.condition.type.Equals(BOOL_TYPE))throw new SemanticError("while condition cannot be judged!",it.pos);
        else{
            currentScope = new Scope(currentScope);
            if(it.thenStmt != null)it.thenStmt.accept(this);
            currentScope = currentScope.parent;
        }
        inLoop --;
    }

    @Override
    public void visit(varDefNode it) {
        if(it.varList != null){
            it.varList.forEach(var->var.accept(this));
        }
    }

    @Override
    public void visit(ForStmtNode it) {
        inLoop ++;
        currentScope = new Scope(currentScope);
        if(it.init1 != null)it.init1.accept(this);
        if(it.init2 != null)it.init2.accept(this);
        if(it.cond != null) {
            it.cond.accept(this);
            if (!it.cond.type.Equals(BOOL_TYPE))
                throw new SemanticError("for condition cannot be judged!", it.pos);
        }
        if(it.iter != null)it.iter.accept(this);
        if(it.thenStmt != null){
            if (it.thenStmt instanceof BlockStmtNode)
                ((BlockStmtNode) it.thenStmt).stmts.forEach(stmtNode -> stmtNode.accept(this));
            else it.thenStmt.accept(this);
        }
        currentScope = currentScope.parent;
        inLoop --;
    }

    @Override
    public void visit(pureExprStmtNode it) {
        it.expr.accept(this);
    }

    @Override
    public void visit(varDefStmtNode it) {
        if(it.varDef != null) {
            it.varDef.accept(this);
        }
    }

    @Override
    public void visit(ContinueStmtNode it) {
        if(inLoop <= 0)throw new SemanticError("continue sentence out of loops!",it.pos);
    }

    @Override
    public void visit(BreakStmtNode it) {
        if(inLoop <= 0)throw new SemanticError("break sentence out of loops!",it.pos);
    }

    @Override
    public void visit(TypeNode it) {}

    @Override
    public void visit(MemberExprNode it) {
        if(it.objExpr == null)throw new SemanticError("what the hell you used '.Identifier' format, Veriloging?", it.pos);
        it.objExpr.accept(this);
        if(it.objExpr.type.Equals(NULL_TYPE))throw new SemanticError("wow you use null as an object!",it.pos);
        if(!gScope.containsClass(it.objExpr.type.typename))throw new SemanticError("the class doesn't exist!",it.pos);
        else{
            GlobalScope tmp_class = gScope.classTable.get(it.objExpr.type.typename);
            if(!tmp_class.containsVariable(it.member_name))throw new SemanticError("the class has no such member!",it.pos);
            else{
                it.type = tmp_class.getVarType(it.member_name);
                it.isAssignable = true;
            }
        }
    }

    @Override
    public void visit(MemberFuncExprNode it) {
        if(it.objExpr == null)throw new SemanticError("what the hell you used '.Identifier()' format, Veriloging?", it.pos);
        it.objExpr.accept(this);
        if(it.objExpr.type.Equals(NULL_TYPE))throw new SemanticError("wow you use null as an object!",it.pos);
        if(!gScope.containsClass(it.objExpr.type.typename))throw new SemanticError("the class doesn't exist!",it.pos);
        else{
            GlobalScope tmp_class = gScope.classTable.get(it.objExpr.type.typename);
            if(!tmp_class.containsFunc(it.Func_name) && !(it.objExpr.type.dim > 0 && it.Func_name.equals("size")))throw new SemanticError("the class has no such member!",it.pos);
            else{
                if(it.Func_name.equals("size")){
                    it.type = INT_TYPE;
                }
                else{
                    FuncDefNode tmp_func = tmp_class.getFunc(it.Func_name);
                    it.type = tmp_func.typename;
                    if (it.paraList != null) {
                        if(tmp_func.paraList == null || it.paraList.size() != tmp_func.paraList.size())throw new SemanticError("parameters not fit",it.pos);
                        it.paraList.forEach(para -> para.accept(this));
                        for (int i = 0; i < it.paraList.size(); ++i) {
                            if (!it.paraList.get(i).type.Equals(tmp_func.paraList.get(i).type) && !it.paraList.get(i).type.equals(NULL_TYPE))
                                throw new SemanticError("two function parameters' types are not same. ", it.pos);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void visit(CreateExprNode it) {
        if(it.typename.equals(NULL_TYPE))throw new SemanticError("fancy dream for null array.",it.pos);
        if(!gScope.containsClass(it.typename.typename)) throw new SemanticError("no such class for creator!",it.pos);
        else{
            if(it.exprList != null){
                it.exprList.forEach(expr->{
                    expr.accept(this);
                    if(!expr.type.Equals(INT_TYPE))throw new SemanticError("terrible index in creator!",it.pos);
                });
            }
            it.type = it.typename;
        }
    }

    @Override
    public void visit(IndexExprNode it) {
        it.objExpr.accept(this);
        if(it.objExpr.type.dim == 0)throw new SemanticError("no memory set for this array",it.pos);
        else{
            it.indexExpr.accept(this);
            if(!it.indexExpr.type.Equals(INT_TYPE))throw new SemanticError("terrible index in array!",it.pos);
            else {
                it.type.typename = it.objExpr.type.typename;
                it.type.dim = it.objExpr.type.dim - 1;
                it.isAssignable = true;
            }
        }
    }

    @Override
    public void visit(FunctionExprNode it) {
        //the func_name has to be an identifier.
        if(inClass){
            GlobalScope nowClassScope = gScope.classTable.get(currentClass.class_name);
            if(!nowClassScope.containsFunc(it.func_name.content) && !gScope.containsFunc(it.func_name.content))throw new SemanticError("the function was not found!",it.pos);
            if(nowClassScope.containsFunc(it.func_name.content)) {
                FuncDefNode tmp_func = nowClassScope.getFunc(it.func_name.content);
                it.type = tmp_func.typename;
                if(it.paraList != null){
                    if(tmp_func.paraList == null || it.paraList.size() != tmp_func.paraList.size()){
                        throw new SemanticError("wrong size of parameter list!", it.pos);
                    }else{
                        it.paraList.forEach(para->para.accept(this));
                        for (int i = 0; i < it.paraList.size(); ++i) {
                            if (!it.paraList.get(i).type.Equals(tmp_func.paraList.get(i).type) && !it.paraList.get(i).type.equals(NULL_TYPE))
                                throw new SemanticError("two function parameters' types are not same. ", it.pos);
                        }
                    }
                }
            }
            else it.type = gScope.getFunc(it.func_name.content).typename;
        }
        else{
            if(!gScope.containsFunc(it.func_name.content))throw new SemanticError("the function was not found!",it.pos);
            FuncDefNode tmp_func = gScope.getFunc(it.func_name.content);
            it.type = tmp_func.typename;
            if(it.paraList != null){
                if(tmp_func.paraList == null || it.paraList.size() != tmp_func.paraList.size()){
                    throw new SemanticError("wrong size of parameter list!", it.pos);
                }else{
                    it.paraList.forEach(para->para.accept(this));
                    for (int i = 0; i < it.paraList.size(); ++i) {
                        if (!it.paraList.get(i).type.Equals(tmp_func.paraList.get(i).type) && !it.paraList.get(i).type.equals(NULL_TYPE))
                            throw new SemanticError("two function parameters' types are not same. ", it.pos);
                    }
                }
            }
        }
    }

    @Override
    public void visit(LambdaExprNode it) {
        currentScope = new Scope(currentScope);
        inLambda++;
        if((it.paraList == null && it.exprList != null) || (it.paraList != null && it.exprList == null))throw new SemanticError("invalid assignment for lambda!",it.pos);
        if(it.paraList != null){
            if(it.paraList.size() != it.exprList.size())throw new SemanticError("values are not equal to objects!",it.pos);
            for(int i = 0; i < it.paraList.size(); ++i){
                it.paraList.get(i).accept(this);
                it.exprList.get(i).accept(this);
                if(!it.paraList.get(i).type.Equals(it.exprList.get(i).type)){
                    throw new SemanticError("Types are not same in lambda!",it.pos);
                }
            }
        }
        if(it.stmt != null)it.stmt.accept(this);
        it.type = lambdaType;
        lambdaType = VOID_TYPE;
        inLambda--;
        currentScope = currentScope.parent;
    }

    @Override
    public void visit(SuffixExprNode it) {
        it.obj_name.accept(this);
        if(!it.obj_name.isAssignable)throw new SemanticError("object could not be assigned",it.pos);
        if(!it.obj_name.type.Equals(INT_TYPE))throw new SemanticError("object's type isn't integer for suffix.",it.pos);
        it.type = INT_TYPE;
    }

    @Override
    public void visit(PrefixExprNode it) {
        it.obj_name.accept(this);
        if((it.OP == PrefixExprNode.p_op.SelfMinus || it.OP == PrefixExprNode.p_op.SelfPlus) && !it.obj_name.isAssignable){
            throw new SemanticError("right value could not be operated in prefix!", it.pos);
        }
        switch (it.OP){
            case SelfPlus,SelfMinus,Tilde,Plus,Minus -> {
                if(!it.obj_name.type.Equals(INT_TYPE))throw new SemanticError("object's type isn't integer for prefix.",it.pos);
                it.type = INT_TYPE;
            }
            case Not -> {
                if(!it.obj_name.type.Equals(BOOL_TYPE))throw new SemanticError("object's type isn't bool for prefix.",it.pos);
                it.type = BOOL_TYPE;
            }
            case ERROR -> {
            }
        }
    }

    @Override
    public void visit(AssignExprNode it) {
        it.lExpr.accept(this);
        it.rExpr.accept(this);
        if(!it.lExpr.isAssignable)throw new SemanticError("right value could not be assigned!",it.pos);
        if(!it.lExpr.type.Equals(it.rExpr.type) && !it.rExpr.type.Equals(NULL_TYPE))throw new SemanticError("left and right expression types are not equal!",it.pos);
        if(it.rExpr.type.Equals(NULL_TYPE) && (it.lExpr.type.Equals(INT_TYPE) || it.lExpr.type.Equals(BOOL_TYPE) || it.lExpr.type.Equals(STRING_TYPE))){
            throw new SemanticError("builtInType couldn't be null!", it.pos);
        }
        it.type = null;
    }

    @Override
    public void visit(BinaryExprNode it) {
        it.lExpr.accept(this);
        it.rExpr.accept(this);
        if(!it.lExpr.type.Equals(it.rExpr.type) && !it.OP.equals(BinaryExprNode.op.Equal) && !it.OP.equals(BinaryExprNode.op.NotEqual))throw new SemanticError("left and right expression types are not equal!",it.pos);
        switch (it.OP){

            case Mul,Div,Mod,Minus,LeftShift,RightShift,Caret,And,Or -> {
                if(!it.lExpr.type.Equals(INT_TYPE))throw new SemanticError("type is not int",it.pos);
                it.type = INT_TYPE;
            }
            case Less,Greater,LessEqual,GreaterEqual -> {
                if(!it.lExpr.type.Equals(INT_TYPE) && !it.lExpr.type.Equals(STRING_TYPE))throw new SemanticError("type is not int or string",it.pos);
                it.type = BOOL_TYPE;
            }
            case Plus -> {
                if(!it.lExpr.type.Equals(INT_TYPE) && !it.lExpr.type.Equals(STRING_TYPE))throw new SemanticError("type is not int or string",it.pos);
                it.type = it.lExpr.type;
            }
            case AndAnd,OrOr->{
                if(!it.lExpr.type.Equals(BOOL_TYPE))throw new SemanticError("type is not bool",it.pos);
                it.type = BOOL_TYPE;
            }
            case Equal,NotEqual -> {
                if(!it.lExpr.type.Equals(it.rExpr.type) && !it.rExpr.type.Equals(NULL_TYPE))throw new SemanticError("left and right expression types are not proper!",it.pos);
                it.type = BOOL_TYPE;
            }
        }
    }

    @Override
    public void visit(FuncDefNode it) {
        currentScope = new Scope(currentScope);
        inFunction = true;
        currentFunction = it;
        if(it.typename.typename != null && !it.typename.Equals(VOID_TYPE) && !gScope.containsClass(it.typename.typename))
            throw new SemanticError("no class for return type!", it.pos);
        if(it.paraList != null){
            it.paraList.forEach(para-> para.accept(this));
        }
        if(it.stmts.stmts != null){
            for(var i : it.stmts.stmts){
                if(i != null)i.accept(this);
            }
        }
        if(it.typename.typename != null && !it.typename.Equals(VOID_TYPE) && !it.hasReturnStmt && !it.func_name.equals("main"))
            throw new SemanticError("the function needs return value!",it.pos);
        inFunction = false;
        currentFunction = null;
        currentScope = currentScope.parent;
    }

    @Override
    public void visit(ClassDefNode it) {
        currentScope = gScope.classTable.get(it.class_name);
        inClass = true;
        currentClass = it;
        if(it.member != null){
            it.member.forEach(varDefNode -> {
                if(varDefNode.varList != null){
                    varDefNode.varList.forEach(var -> var.accept(this));
                }
            });
        }
        if(it.memberFunc != null){
            it.memberFunc.forEach(funcDefNode -> funcDefNode.accept(this));
        }
        inClass = false;
        currentClass = null;
        currentScope = currentScope.parent;
    }

    @Override
    public void visit(varDeclarationNode it) {
        if(inClass && !inFunction){
            if(!gScope.containsClass(it.type.typename))throw new SemanticError("no such type for variable!",it.pos);
            if(it.init != null){
                it.init.accept(this);
                if(!it.init.type.Equals(NULL_TYPE) && !it.init.type.Equals(it.type)){
                    throw new SemanticError("initial type does not fit to variable type!",it.pos);
                }
            }
        }else {
            if (currentScope.containsVariable(it.name) || gScope.containsClass(it.name)) throw new SemanticError("duplicate variable defined!", it.pos);
            else if(!gScope.containsClass(it.type.typename))throw new SemanticError("no such type for variable!",it.pos);
            else{
                if(it.init != null){
                    it.init.accept(this);
                    if(!it.init.type.Equals(NULL_TYPE) && !it.init.type.Equals(it.type)){
                        throw new SemanticError("initial type does not fit to variable type!",it.pos);
                    }
                }
                currentScope.SetVariable(it.name,it.type);
            }
        }
    }

    @Override
    public void visit(AtomExprNode it){
        if(it.constNode.type == ConstNode.constType.Identifier){
            if(currentScope.containsVariable(it.constNode.name)){
                it.type.typename = currentScope.getVarType(it.constNode.name).typename;
                it.type.dim = currentScope.getVarType(it.constNode.name).dim;
            }else{
                Scope nowScope = currentScope;
                boolean isFound = false;
                while(nowScope.parent != null){
                    nowScope = nowScope.parent;
                    if(nowScope.containsVariable(it.constNode.name)){
                        it.type.typename = nowScope.getVarType(it.constNode.name).typename;
                        it.type.dim =  nowScope.getVarType(it.constNode.name).dim;
                        isFound = true;
                        break;
                    }
                }
                if(!isFound)throw new SemanticError("undefined variable!",it.pos);
            }
            it.isAssignable = true;
        }else if(it.constNode.type == ConstNode.constType.This){
            if(!inClass)throw new SemanticError("no template class for This", it.pos);
            else{
                it.type.typename = currentClass.class_name;
                it.isAssignable = true;
            }
        }else it.type = getConstType(it.constNode);
    }

    @Override
    public void visit(ConstNode it) {}
}
