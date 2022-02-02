package IR.Instruction;

import IR.IRBasicBlock;
import IR.IRVisitor;
import IR.Operand.Operand;
import IR.Operand.Value;
import IR.TypeSystem.PointerType;

public class InstructionBinary extends Instruction{

    public static enum Operation{
        add,sub,mul,sdiv,srem,shl,ashr,and,or,xor
    }
    public Operation operation;

    public InstructionBinary(IRBasicBlock _block, Value op1, Value op2, Operation _operation){
        super(_block,_operation.toString(),op1.type);
        assert op1.type.equals(op2.type);
        operation = _operation;
        addOperand(op1);addOperand(op2);
    }

    public Value getOp1(){return getOperand(0);}

    public Value getOp2(){return getOperand(1);}

    @Override
    public String toString(){
        return GetName() + " = " + operation.toString() + " " + getOp1().printValueString() + ", " + getOp2().toString();
    }

    @Override
    public void accept(IRVisitor _visitor){_visitor.visit(this);}
}
