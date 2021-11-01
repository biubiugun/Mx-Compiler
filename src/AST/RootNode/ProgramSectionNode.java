package AST.RootNode;

import AST.ASTNode;
import AST.ASTVisitor;
import AST.ExprNode.PrefixExprNode;
import Util.position;
import java.util.ArrayList;
import AST.StmtNode.varDefStmtNode;
import AST.DefNode.ClassDefNode;
import AST.DefNode.FuncDefNode;

public class ProgramSectionNode extends ASTNode {

    public ProgramSectionNode(position _pos){
        super(_pos);
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
