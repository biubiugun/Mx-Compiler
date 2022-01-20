package IR.Instruction;

import IR.IRBasicBlock;
import IR.Operand.Operand;
import IR.TypeSystem.IRType;

public class InstructionAlloc extends Instruction{
    public IRType type;
    public Operand destReg;

    public InstructionAlloc(IRType _type, Operand _destReg, IRBasicBlock _block){
        super(_block);
        type = _type;
        destReg = _destReg;
    }

    @Override
    public String toString(){
        return destReg.toString() + " = alloca " + type.toString();
    }
}
