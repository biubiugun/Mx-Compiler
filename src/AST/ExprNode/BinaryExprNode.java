package AST.ExprNode;

import AST.ASTVisitor;
import Util.position;

public class BinaryExprNode extends ExprNode{
    public enum op{
        Mul,Div,Mod,
        And,Or,AndAnd,OrOr,Caret,
        Plus,Minus,
        LeftShift,RightShift,
        Less,Greater,LessEqual,GreaterEqual,
        Equal,NotEqual,
        ERROR
    }

    public op OP;
    public ExprNode lExpr,rExpr;

    public BinaryExprNode(position _pos,String _content,ExprNode _lExpr,op _OP,ExprNode _rExpr){
        super(_pos,_content);
        lExpr = _lExpr;
        rExpr = _rExpr;
        OP = _OP;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }

}
