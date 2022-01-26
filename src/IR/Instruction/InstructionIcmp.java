package IR.Instruction;

import IR.IRBasicBlock;
import IR.Operand.Operand;

public class InstructionIcmp extends Instruction{
    public Operand destReg;
    public Operand op1;
    public Operand op2;

    public enum cmp_method{
        eq,ne,ugt,uge,ult,ule,sgt,sge,slt,sle
    }

    public cmp_method method;

    public InstructionIcmp(Operand _destReg, IRBasicBlock _block, Operand _op1, Operand _op2, cmp_method _method){
        super(_block);
        destReg = _destReg;
        op1 = _op1;
        op2 = _op2;
        assert op1.typename.equals(op2.typename);
        method = _method;
    }

    @Override
    public String toString(){
        return destReg.toString() + " = icmp " + method.toString() + " " + op1.typename.toString() + " " + op1.toString() + ", " + op2.toString();
    }
}
