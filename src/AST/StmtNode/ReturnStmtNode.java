package AST.StmtNode;

import AST.ASTVisitor;
import AST.ExprNode.ExprNode;
import Util.position;

public class ReturnStmtNode extends StmtNode{
    public ExprNode expr;

    public ReturnStmtNode(position _pos,ExprNode _expr){
        super(_pos);
        if(_expr != null)expr = _expr;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
