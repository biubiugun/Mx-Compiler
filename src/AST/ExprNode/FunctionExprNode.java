package AST.ExprNode;

import AST.ASTVisitor;
import Util.position;
import java.util.ArrayList;

public class FunctionExprNode extends ExprNode{
    ExprNode func_name;
    ArrayList<ExprNode> paraList;

    public FunctionExprNode(position _pos,String _content,ExprNode _func_name,ArrayList<ExprNode>_paraList){
        super(_pos,_content);
        func_name = _func_name;
        if(_paraList != null)paraList = _paraList;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
