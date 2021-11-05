package AST.ExprNode;

import AST.ASTVisitor;
import Util.position;

public class AssignExprNode extends ExprNode{
    public ExprNode lExpr,rExpr;

    public AssignExprNode(position _pos, String _content,ExprNode _lExpr, ExprNode _rExpr){
        super(_pos,_content);
        lExpr = _lExpr;
        rExpr = _rExpr;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
