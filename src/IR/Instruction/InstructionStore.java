package IR.Instruction;

import IR.IRBasicBlock;
import IR.IRVisitor;
import IR.Operand.Value;
import IR.TypeSystem.VoidType;

public class InstructionStore extends Instruction{
    public InstructionStore(Value _value, Value _address, IRBasicBlock _block) {
        super(_block,"_store", new VoidType());
        this.addOperand(_value); // 0 for value
        this.addOperand(_address); // 1 for address
    }

    @Override
    public String GetName() {
        throw new RuntimeException("[Debug] Why get name in Store ? ");
    }

    @Override
    public String toString() {
        return "store " + this.getOperand(0).printValueString() + ", " + this.getOperand(1).printValueString() + ", align " + this.getOperand(0).type.byteSize();
    }

    @Override
    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
