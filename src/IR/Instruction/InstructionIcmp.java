package IR.Instruction;

import IR.IRBasicBlock;
import IR.IRVisitor;
import IR.Operand.Value;
import IR.TypeSystem.IntegerType;

public class InstructionIcmp extends Instruction{

    public static enum cmp_method{
        eq,ne,ugt,uge,ult,ule,sgt,sge,slt,sle
    }

    public cmp_method method;

    public InstructionIcmp(IRBasicBlock _block, Value _op1, Value _op2, cmp_method _method){
        super(_block,_method.toString(),new IntegerType(1));
        assert _op1.type.equals(_op2.type);
        method = _method;
        addOperand(_op1);
        addOperand(_op2);
    }

    @Override
    public String toString(){
        return GetName() + " = icmp " + method.toString() + " " + getOperand(0).printValueString() + ", " + getOperand(1).GetName();
    }

    @Override
    public void accept(IRVisitor _visitor){
        _visitor.visit(this);
    }
}
