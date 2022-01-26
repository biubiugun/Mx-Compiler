package IR.Instruction;

import IR.IRBasicBlock;
import IR.Operand.Operand;
import IR.TypeSystem.PointerType;

public class InstructionStore extends Instruction{
    public Operand storeValue;
    public Operand pointer;

    public InstructionStore(IRBasicBlock _block, Operand _value, Operand _pointer){
        super(_block);
        storeValue = _value;
        pointer = _pointer;
    }

    @Override
    public String toString(){
        return "store " + storeValue.typename.toString() + " " + storeValue.toString() + ", " + ((PointerType)pointer.typename).toString() + " " + pointer.toString() + ", align " + storeValue.typename.byteSize();
    }
}
