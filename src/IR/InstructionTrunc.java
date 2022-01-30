package IR;

import IR.Instruction.Instruction;
import IR.Operand.Value;
import IR.TypeSystem.IRType;

public class InstructionTrunc extends Instruction {
    public InstructionTrunc(Value trunc_value, IRType _target_type, IRBasicBlock _block){
        super(_block,"_trunc", _target_type);
        addOperand(trunc_value);
    }

    public Value getTruncValue(){
        return getOperand(0);
    }

    @Override
    public String toString(){
        return GetName() + " = trunc " + getTruncValue().printValueString() + " to " + type.toString();
    }

    @Override
    public void accept(IRVisitor _visitor){
        _visitor.visit(this);
    }
}
