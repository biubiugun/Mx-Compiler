package AST.ExprNode;

import AST.ASTVisitor;
import Util.position;

public class PrefixExprNode extends ExprNode{
    public enum p_op{SelfPlus,SelfMinus,Not,Tilde,Plus,Minus,ERROR}
    public p_op OP;
    ExprNode obj_name;

    public PrefixExprNode(position _pos,String _content,p_op _OP,ExprNode _obj_name){
        super(_pos,_content);
        OP = _OP;
        obj_name = _obj_name;
        isAssignable = true;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
