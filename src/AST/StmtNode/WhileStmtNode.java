package AST.StmtNode;

import AST.ASTVisitor;
import AST.ExprNode.ExprNode;
import Util.position;

public class WhileStmtNode extends StmtNode{
    ExprNode condition;
    StmtNode thenStmt;

    public WhileStmtNode(position _pos,ExprNode _condition,StmtNode _thenStmt){
        super(_pos);
        condition = _condition;
        thenStmt = _thenStmt;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
