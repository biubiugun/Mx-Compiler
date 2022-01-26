package IR.Instruction;

import IR.IRBasicBlock;
import IR.Operand.Operand;
import IR.TypeSystem.PointerType;

import java.util.ArrayList;

public class InstructionGetelementptr extends Instruction{
    public Operand destReg;
    public Operand pointer;
    public ArrayList<Operand> indexList;

    public InstructionGetelementptr(Operand _ptr, ArrayList<Operand> _indexList, Operand _destReg, IRBasicBlock _block){
        super(_block);
        pointer = _ptr;
        indexList = _indexList;
        destReg = _destReg;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder(destReg.toString());
        str.append(" = getelementptr inbounds ").append(((PointerType)pointer.typename).type.toString()).append(", ");
        str.append(pointer.typename.toString()).append(" ").append(pointer.toString());
        for(var i : indexList){
            str.append(", ").append(i.typename.toString()).append(" ").append(i.toString());
        }
        return str.toString();
    }

}
