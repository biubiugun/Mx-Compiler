package AST.ExprNode;

import AST.ASTVisitor;
import AST.TypeNode.ConstNode;
import Util.position;

public class AtomExprNode extends ExprNode{
    public ConstNode constNode;

    public AtomExprNode(position _pos,String _content,ConstNode _constNode){
        super(_pos,_content);
        constNode = _constNode;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
