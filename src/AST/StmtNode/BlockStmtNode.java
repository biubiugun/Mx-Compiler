package AST.StmtNode;

import AST.ASTVisitor;
import Util.position;

import java.util.ArrayList;

public class BlockStmtNode extends StmtNode{
    public ArrayList<StmtNode>stmts;

    public BlockStmtNode(ArrayList<StmtNode>_stmts,position _pos){
        super(_pos);
        stmts = _stmts;
    }


    @Override
    public void accept(ASTVisitor Visitor){
        Visitor.visit(this);
    }
}
