package AST;

import AST.RootNode.RootNode;

public interface ASTVisitor {
    void visit(RootNode it);
}
