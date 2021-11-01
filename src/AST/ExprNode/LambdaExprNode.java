package AST.ExprNode;

import AST.ASTVisitor;
import Util.position;
import java.util.ArrayList;
import AST.DefNode.varDeclarationNode;

public class LambdaExprNode extends ExprNode{
    public ArrayList<varDeclarationNode> paraList;
    public ArrayList<ExprNode> exprList;

    public LambdaExprNode(position _pos,String _content,ArrayList<varDeclarationNode> _paraList, ArrayList<ExprNode> _exprList){
        super(_pos,_content);
        paraList = _paraList;
        exprList = _exprList;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
