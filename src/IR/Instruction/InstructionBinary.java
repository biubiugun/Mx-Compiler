package IR.Instruction;

import IR.IRBasicBlock;
import IR.Operand.Operand;
import IR.TypeSystem.PointerType;

public class InstructionBinary extends Instruction{
    Operand operand1,operand2;
    Operand destReg;

    enum Operation{
        add,sub,mul,sdiv,srem,shl,ashr,and,or,xor
    }
    Operation operation;

    public InstructionBinary(IRBasicBlock _block, Operand op1, Operand op2, Operation _operation){
        super(_block);
        if(op1.typename instanceof PointerType){
            assert op2.typename instanceof PointerType;
            assert ((PointerType) op1.typename).type.equals(((PointerType) op2.typename).type);
        }else{
            assert op1.typename.equals(op2.typename);
        }
        operand1 = op1;
        operand2 = op2;
        operation = _operation;
    }

    @Override
    public String toString(){
        return destReg.toString() + " = " + operation.toString() + " " + operand1.toString() + " " + operand2.toString();
    }
}
