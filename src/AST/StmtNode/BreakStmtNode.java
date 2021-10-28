package AST.StmtNode;

import AST.ASTVisitor;
import Util.position;

public class BreakStmtNode extends StmtNode{
    public BreakStmtNode(position _pos){
        super(_pos);
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
