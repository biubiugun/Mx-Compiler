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

public class SemanticChecker implements ASTVisitor {
    GlobalScope gScope;

    public SemanticChecker(GlobalScope _gScope){
        gScope = _gScope;
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
}
