package AST;

import IR.Operand.Value;
import Util.position;

abstract public class ASTNode {
    public position pos;
    public Value IROperand = null;

    public ASTNode(position _pos){
        pos = _pos;
    }

    public position getPos(){return pos;}

    abstract public void accept(ASTVisitor Visitor);
}
