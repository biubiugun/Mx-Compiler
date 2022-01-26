package IR.Instruction;

import IR.IRBasicBlock;
import IR.IRFunction;
import IR.Operand.Operand;

import java.util.ArrayList;

public class InstructionCall extends Instruction{
    public Operand destReg;
    public ArrayList<Operand> paraList;
    public IRFunction called_function;

    public InstructionCall(IRBasicBlock _block, Operand _destReg, ArrayList<Operand> _paraList, IRFunction _function){
        super(_block);
        destReg = _destReg;
        paraList = _paraList;
        called_function = _function;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("");
        if(destReg != null){
            str.append(destReg.toString()).append(" = ");
        }
        str.append("call ");
        return str.toString() + called_function.toString(paraList);
    }
}
