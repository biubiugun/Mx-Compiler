package IR.Instruction;

import IR.IRBasicBlock;
import IR.IRVisitor;
import IR.Operand.Value;
import IR.TypeSystem.IRType;

public class InstructionZext extends Instruction{
    public InstructionZext(Value _origin, IRType targetTy, IRBasicBlock _block) {
        super(_block,"zext_", targetTy);
        this.addOperand(_origin);
    }

    @Override
    public String toString() {
        return this.GetName() + " = zext " + this.getOperand(0).printValueString() + " to " + this.type.toString();
    }

    @Override
    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
