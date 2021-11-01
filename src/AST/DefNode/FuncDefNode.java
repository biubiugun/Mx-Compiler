package AST.DefNode;

import AST.ASTVisitor;
import AST.StmtNode.BlockStmtNode;
import AST.StmtNode.StmtNode;
import AST.TypeNode.TypeNode;
import Util.position;
import java.util.ArrayList;

public class FuncDefNode extends DefNode{
    TypeNode typename;
    String func_name;
    ArrayList<varDeclarationNode> paraList;
    BlockStmtNode stmts;

    public FuncDefNode(position _pos,TypeNode _typename,String _func_name,ArrayList<varDeclarationNode>_paraList,BlockStmtNode _stmts){
        super(_pos);
        if(_typename != null)typename = _typename;
        func_name = _func_name;
        if(_paraList != null)paraList = _paraList;
        stmts = _stmts;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }

}
