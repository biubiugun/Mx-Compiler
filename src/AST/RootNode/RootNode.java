package AST.RootNode;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.position;

import java.util.ArrayList;

public class RootNode extends ASTNode{
    public ArrayList<ASTNode> NodeList;

    public RootNode(position pos){
        super(pos);
        NodeList = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
