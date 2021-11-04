package AST.ExprNode;

import AST.ASTVisitor;
import Util.position;

public class MemberExprNode extends ExprNode{
    public ExprNode objExpr;
    public String member_name;

    public MemberExprNode(position _pos, String _content, String _member_name, ExprNode _objExpr){
        super(_pos,_content);
        member_name = _member_name;
        objExpr = _objExpr;
        isAssignable = true;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }

}
