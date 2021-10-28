package AST.StmtNode;

import AST.ASTNode;
import AST.ASTVisitor;
import AST.ExprNode.ExprNode;
import Util.position;

public class ForStmtNode extends StmtNode{
    StmtNode init;
    ExprNode cond,iter;

    public ForStmtNode(position _pos,StmtNode _init,ExprNode _cond,ExprNode _iter){
        super(_pos);
        if(_init != null)init = _init;
        if(_cond != null)cond = _cond;
        if(_iter != null)iter = _iter;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
