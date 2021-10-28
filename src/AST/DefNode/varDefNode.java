package AST.DefNode;

import AST.ASTVisitor;
import AST.ExprNode.ExprNode;
import Util.position;

public class varDefNode extends DefNode{
    public String name,type;
    public ExprNode init;

    public varDefNode(position _pos,String _name,String _type,ExprNode _init){
        super(_pos);
        name = _name;
        type = _type;
        if(_init != null)init = _init;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
