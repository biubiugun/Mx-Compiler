package IR.Instruction;

import IR.IRVisitor;
import IR.TypeSystem.IRType;
import IR.TypeSystem.PointerType;

public class InstructionGlobal extends Instruction{
    public InstructionGlobal(String _name, IRType _type) {
        super(null,_name+"_glo", new PointerType(_type,1));
    }

    @Override
    public String GetName() {
        return "@" + this.name;
    }

    // toString for instruction is to print llvm
    @Override
    public String toString() {
        return this.GetName() + " = global " + this.type.dePointed().toString() + " zeroinitializer, align " + this.type.dePointed().byteSize();
    }

    @Override
    public void accept(IRVisitor _visitor) {
        _visitor.visit(this);
    }
}
