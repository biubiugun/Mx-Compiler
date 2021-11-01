package AST.DefNode;

import AST.ASTVisitor;
import AST.ExprNode.ExprNode;
import AST.TypeNode.TypeNode;
import Util.position;

public class varDeclarationNode extends DefNode{
    public String name;
    public ExprNode init;
    public TypeNode type;

    public varDeclarationNode(position _pos,String _name,ExprNode _init,TypeNode _type){
        super(_pos);
        name = _name;
        if(_init != null)init = _init;
        type = _type;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
