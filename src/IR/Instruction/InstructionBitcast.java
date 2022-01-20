package IR.Instruction;

import IR.IRBasicBlock;
import IR.Operand.Operand;
import IR.TypeSystem.IRType;

public class InstructionBitcast extends Instruction{
    public Operand destReg;
    public Operand operand;
    public IRType cast_type;

    public InstructionBitcast(IRBasicBlock _block, Operand _destReg, Operand _operand){
        super(_block);
        destReg = _destReg;
        operand = _operand;
    }

    @Override
    public  String toString(){
        return destReg.toString() + " = bitcast " + operand.typename + " " + operand.toString() + " to " + destReg.typename;
    }

}
