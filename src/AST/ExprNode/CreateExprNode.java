package AST.ExprNode;

import AST.ASTVisitor;
import Util.position;
import java.util.ArrayList;

public class CreateExprNode extends ExprNode{
    public String typename;
    public ArrayList<ExprNode> objList;
    public int dim;

    public CreateExprNode(position _pos, String _content, String _typename, ArrayList<ExprNode> _objList,int _dim){
        super(_pos, _content);
        typename = _typename;
        objList = _objList;
        dim = _dim;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
