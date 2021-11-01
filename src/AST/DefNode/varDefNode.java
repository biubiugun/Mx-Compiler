package AST.DefNode;

import AST.ASTVisitor;
import AST.ExprNode.ExprNode;
import AST.TypeNode.TypeNode;
import java.util.ArrayList;
import Util.position;

public class varDefNode extends DefNode{
    public ArrayList<varDeclarationNode> varList;

    public varDefNode(position _pos,ArrayList<varDeclarationNode> _varList){
        super(_pos);
        varList = _varList;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
