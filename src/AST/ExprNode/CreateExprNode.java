package AST.ExprNode;

import AST.ASTVisitor;
import AST.TypeNode.TypeNode;
import Util.position;
import java.util.ArrayList;

public class CreateExprNode extends ExprNode{
    public TypeNode typename;
    public ArrayList<ExprNode> exprList;
    public int dim;

    public CreateExprNode(position _pos, String _content, TypeNode _typename, ArrayList<ExprNode> _exprList,int _dim){
        super(_pos, _content);
        typename = _typename;
        if(_exprList != null)exprList =_exprList;
        dim = _dim;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
