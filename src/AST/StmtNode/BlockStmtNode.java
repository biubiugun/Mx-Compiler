package AST.StmtNode;

import AST.ASTVisitor;
import Util.position;

import java.util.LinkedList;

public class BlockStmtNode extends StmtNode{
    public LinkedList<StmtNode>stmts;

    public BlockStmtNode(LinkedList<StmtNode>_stmts,position _pos){
        super(_pos);
        stmts = _stmts;
    }

    public BlockStmtNode(StmtNode _stmt,position _pos){
        super(_pos);
        stmts = new LinkedList<StmtNode>();
        stmts.add(_stmt);
    }

    public void addStmt(StmtNode _stmt){
        stmts.add(_stmt);
    }

    public void addStmts(LinkedList<StmtNode>_newStmts){
        if(_newStmts != null)stmts.addAll(_newStmts);
    }

    @Override
    public void accept(ASTVisitor Visitor){
        Visitor.visit(this);
    }
}
