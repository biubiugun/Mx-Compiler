package AST.ExprNode;

import AST.ASTNode;
import AST.TypeNode.TypeNode;
import Util.position;

public abstract class ExprNode extends ASTNode {
    public String content;
    public TypeNode type;
    public boolean isAssignable = false;

    public ExprNode(position _pos,String _content){
        super(_pos);
        content = _content;
        type = new TypeNode(null,"");
    }
}
