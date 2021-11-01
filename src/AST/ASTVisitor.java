package AST;

import AST.DefNode.ClassDefNode;
import AST.DefNode.FuncDefNode;
import AST.DefNode.varDeclarationNode;
import AST.ExprNode.*;
import AST.RootNode.ProgramSectionNode;
import AST.RootNode.RootNode;
import AST.StmtNode.*;
import AST.DefNode.varDefNode;
import AST.TypeNode.ConstNode;
import AST.TypeNode.TypeNode;

public interface ASTVisitor {
    void visit(RootNode it);

    void visit(ProgramSectionNode it);

    void visit(BlockStmtNode it);

    void visit(ifStmtNode it);

    void visit(ReturnStmtNode it);

    void visit(WhileStmtNode it);

    void visit(varDefNode it);

    void visit(ForStmtNode it);

    void visit(pureExprStmtNode it);

    void visit(varDefStmtNode it);

    void visit(ContinueStmtNode it);

    void visit(BreakStmtNode it);

    void visit(TypeNode it);

    void visit(MemberExprNode it);

    void visit(MemberFuncExprNode it);

    void visit(CreateExprNode it);

    void visit(IndexExprNode it);

    void visit(FunctionExprNode it);

    void visit(LambdaExprNode it);

    void visit(SuffixExprNode it);

    void visit(PrefixExprNode it);

    void visit(AssignExprNode it);

    void visit(BinaryExprNode it);

    void visit(FuncDefNode it);

    void visit(ClassDefNode it);

    void visit(varDeclarationNode it);

    void visit(ConstNode it);
}
