package AST.ExprNode;

import AST.ASTVisitor;
import Util.position;

public class SuffixExprNode extends ExprNode{
    public enum s_op{SelfPlus,SelfMinus,ERROR}
    public s_op OP;
    ExprNode obj_name;

    public SuffixExprNode(position _pos,String _content,s_op _OP,ExprNode _obj_name){
        super(_pos,_content);
        OP = _OP;
        obj_name = _obj_name;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
