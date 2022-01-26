package IR.Instruction;

import IR.IRBasicBlock;
import IR.Operand.Operand;

public class InstructionRet extends Instruction{
    public Operand returnValue;

    public InstructionRet(IRBasicBlock _block, Operand _value){
        super(_block);
        returnValue = _value;
    }

    @Override
    public String toString(){
        return "ret " + returnValue.typename.toString() + " " + returnValue.toString();
    }
}
