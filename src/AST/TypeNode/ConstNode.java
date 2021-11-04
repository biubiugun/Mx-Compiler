package AST.TypeNode;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.position;

public class ConstNode extends ASTNode {
    public String name;
    public enum constType{
        DecimalInteger,
        True,
        False,
        STRING,
        This,
        NULL,
        Identifier
    }
    public constType type;

    public ConstNode(position _pos,String _name){
        super(_pos);
        name = _name;
    }

    public boolean isAssignable(){
        return type == constType.This || type == constType.Identifier;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
