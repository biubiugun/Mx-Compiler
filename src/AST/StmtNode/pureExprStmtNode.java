package AST.StmtNode;

import AST.ASTVisitor;
import AST.ExprNode.ExprNode;
import Util.position;

public class pureExprStmtNode extends StmtNode{
    ExprNode expr;

    public pureExprStmtNode(position _pos,ExprNode _expr){
        super(_pos);
        expr = _expr;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
