package IR.Instruction;

import IR.IRBasicBlock;
import IR.IRVisitor;
import IR.Operand.Value;

public class InstructionLoad extends Instruction{
    public InstructionLoad(String _name, Value _address, IRBasicBlock _block) {
        super(_block,_name+"_load",_address.type.dePointed());
        this.addOperand(_address);
    }

    @Override
    public String toString() {
        return this.GetName() + " = load " + this.type.toString() + ", " + this.getOperand(0).printValueString() + ", align " + this.type.byteSize();
    }

    @Override
    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
