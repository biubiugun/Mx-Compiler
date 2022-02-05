package IR.Instruction;

import IR.IRBasicBlock;
import IR.IRVisitor;
import IR.Operand.Value;
import IR.TypeSystem.VoidType;

public class InstructionRet extends Instruction{
    public InstructionRet(Value _returnValue, IRBasicBlock _block) {
        super(_block,"_ret",_returnValue.type);
        this.addOperand(_returnValue);
    }

    @Override
    public String GetName() {
        throw new RuntimeException("[Debug] Why get name in Ret ? ");
    }

    @Override
    public String toString() {
        return "ret " + ((this.type instanceof VoidType) ? this.type.toString() : this.getOperand(0).printValueString());
    }

    @Override
    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
