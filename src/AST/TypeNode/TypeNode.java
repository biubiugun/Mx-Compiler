package AST.TypeNode;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.position;

public class TypeNode extends ASTNode {
    public String typename;
    public int dim = 0;

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

    public boolean Equals(TypeNode other){
        return typename.equals(other.typename) && dim == other.dim ||
                (typename.equals(other.typename) && typename.equals("null"));
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
