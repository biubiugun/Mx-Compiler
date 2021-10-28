package AST.StmtNode;

import AST.ASTVisitor;
import Util.position;

public class ContinueStmtNode extends StmtNode{
    public ContinueStmtNode(position _pos){
        super(_pos);
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
