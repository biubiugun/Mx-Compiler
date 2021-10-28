package AST.TypeNode;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.position;

public class TypeNode extends ASTNode {
    String typename;
    public int dim;

    public TypeNode(position _pos,String _typename){
        super(_pos);
        typename = _typename;
    }

    public String  getType(){
        return typename;
    }

    public int getDim(){
        return dim;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
