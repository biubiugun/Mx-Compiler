package AST.StmtNode;

import AST.ASTVisitor;
import AST.ExprNode.ExprNode;
import Util.position;

public class ifStmtNode extends StmtNode{
    StmtNode thenStmt,elseStmt;
    ExprNode condition;

    public ifStmtNode(position _pos,StmtNode _thenNode,StmtNode _elseNode,ExprNode _condition){
        super(_pos);
        thenStmt = _thenNode;
        if(_elseNode != null)elseStmt = _elseNode;
        condition = _condition;
    }

    public ifStmtNode(position _pos,StmtNode _thenNode,ExprNode _condition){
        super(_pos);
        thenStmt = _thenNode;
        elseStmt = null;
        condition = _condition;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
