package AST.StmtNode;

import AST.ASTNode;
import AST.DefNode.varDeclarationNode;
import AST.ASTVisitor;
import AST.ExprNode.ExprNode;
import Util.position;

public class ForStmtNode extends StmtNode{
    varDeclarationNode init1;
    ExprNode cond,iter,init2;

    public ForStmtNode(position _pos,varDeclarationNode _init,ExprNode _cond,ExprNode _iter){
        super(_pos);
        if(_init != null)init1 = _init;
        if(_cond != null)cond = _cond;
        if(_iter != null)iter = _iter;
        init2 = null;
    }

    public ForStmtNode(position _pos,ExprNode _init,ExprNode _cond,ExprNode _iter){
        super(_pos);
        if(_init != null)init2 = _init;
        if(_cond != null)cond = _cond;
        if(_iter != null)iter = _iter;
        init1 = null;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
