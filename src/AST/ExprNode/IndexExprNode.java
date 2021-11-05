package AST.ExprNode;

import AST.ASTVisitor;
import Util.position;

public class IndexExprNode extends ExprNode{
    public ExprNode objExpr,indexExpr;

    public IndexExprNode(position _pos,String _content,ExprNode _objExpr,ExprNode _indexExpr){
        super(_pos,_content);
        objExpr = _objExpr;
        indexExpr = _indexExpr;
        isAssignable = true;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
