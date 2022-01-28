package AST.DefNode;

import AST.ASTVisitor;
import AST.StmtNode.BlockStmtNode;
import AST.StmtNode.StmtNode;
import AST.TypeNode.TypeNode;
import Util.position;
import java.util.ArrayList;

public class FuncDefNode extends DefNode{
    public TypeNode typename;
    public String func_name;
    public ArrayList<varDeclarationNode> paraList;
    public BlockStmtNode stmts;
    public boolean hasReturnStmt = false;
    public boolean isBuiltin = false;

    public FuncDefNode(position _pos,TypeNode _typename,String _func_name,ArrayList<varDeclarationNode>_paraList,BlockStmtNode _stmts){
        super(_pos);
        if(_typename != null)typename = _typename;
        func_name = _func_name;
        if(_paraList != null)paraList = _paraList;
        stmts = _stmts;
    }

    public FuncDefNode(position _pos,TypeNode _typename,String _func_name,ArrayList<varDeclarationNode>_paraList,BlockStmtNode _stmts,boolean _builtinFlag){
        super(_pos);
        if(_typename != null)typename = _typename;
        func_name = _func_name;
        if(_paraList != null)paraList = _paraList;
        stmts = _stmts;
        isBuiltin = _builtinFlag;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }

}
