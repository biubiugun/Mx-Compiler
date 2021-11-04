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
    String inFuncType;

    private String getConstType(ConstNode it){
        switch (it.type){

            case DecimalInteger -> {
                return "int";
            }
            case True, False -> {
                return "bool";
            }
            case STRING -> {
                return "string";
            }
            case This -> {
                return "this";
            }
            case NULL -> {
                return "null";
            }
            case Identifier -> {
                return it.name;
            }
        }
            throw new SemanticError("none type fit!",it.pos);
    }

    public SemanticChecker(GlobalScope _gScope){
        gScope = _gScope;
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
        if(it.stmts != null){
            it.stmts.forEach(stmtNode -> stmtNode.accept(this));
        }
    }

    @Override
    public void visit(ifStmtNode it) {
        it.condition.accept(this);
    }

    @Override
    public void visit(ReturnStmtNode it) {
        if(!inFunction)throw new SemanticError("not in function but return!",it.pos);
        it.expr.accept(this);
        String check_type = it.expr.type;
        if(check_type != null && !inFuncType.equals(check_type)){
            throw new SemanticError("returnType is not proper",it.pos);
        }
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
    public void visit(AtomExprNode it){}

    @Override
    public void visit(ConstNode it) {

    }
}
