package IR.Instruction;

import IR.IRBasicBlock;
import IR.IRVisitor;
import IR.TypeSystem.IRType;
import IR.TypeSystem.PointerType;

public class InstructionAlloc extends Instruction{

    public InstructionAlloc(String _name,IRType _type,IRBasicBlock _block){
        super(_block,_name + "_alloc",new PointerType(_type,1));
    }

    @Override
    public String toString(){
        return GetName() + " = alloca " + type.dePointed().toString() + ", align " + type.dePointed().byteSize();
    }

    @Override
    public void accept(IRVisitor _visitor){
        _visitor.visit(this);
    }
}
