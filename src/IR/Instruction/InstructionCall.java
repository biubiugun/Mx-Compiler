package IR.Instruction;

import IR.IRBasicBlock;
import IR.Operand.Operand;

import java.util.ArrayList;

public class InstructionCall extends Instruction{
    Operand destReg;
    ArrayList<Operand> paraList;

    public InstructionCall(IRBasicBlock _block){
        super(_block);
    }

    @Override
    public String toString(){return " ";}
}
