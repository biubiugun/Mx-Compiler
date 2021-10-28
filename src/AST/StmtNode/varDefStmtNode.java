package AST.StmtNode;

import AST.ASTVisitor;
import Util.position;
import AST.DefNode.varDefNode;

public class varDefStmtNode extends StmtNode{
    public varDefNode varDef;

    public varDefStmtNode(position _pos,varDefNode _varDef){
        super(_pos);
        varDef = _varDef;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
