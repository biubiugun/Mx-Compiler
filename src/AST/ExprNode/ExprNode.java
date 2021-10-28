package AST.ExprNode;

import AST.ASTNode;
import Util.position;

public abstract class ExprNode extends ASTNode {
    public String content;
    public String type;

    public ExprNode(position _pos,String _content){
        super(_pos);
        content = _content;
    }
}
