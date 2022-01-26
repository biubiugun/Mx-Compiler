package IR.Instruction;

import IR.IRBasicBlock;
import IR.Operand.Operand;
import IR.TypeSystem.PointerType;

public class InstructionLoad extends Instruction{
    public Operand destReg;
    public Operand pointer;

    public InstructionLoad(Operand _destReg, IRBasicBlock _block, Operand _pointer, int _alignSize){
        super(_block);
        destReg = _destReg;
        pointer = _pointer;
    }

    @Override
    public String toString(){
        return destReg.toString() + " = load " + destReg.typename.toString() + ", "  + pointer.typename.toString() + " " + pointer.toString() + ", align " + destReg.typename.byteSize();
    }
}
