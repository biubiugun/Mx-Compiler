package IR.Instruction;

import IR.IRBasicBlock;
import IR.IRVisitor;
import IR.Operand.Operand;
import IR.Operand.Value;
import IR.TypeSystem.IntegerType;
import IR.TypeSystem.VoidType;

public class InstructionBr extends Instruction{

    public InstructionBr(IRBasicBlock _block, IRBasicBlock _true_block, IRBasicBlock _false_block, Value _cond){
        super(_block, "_br", new VoidType());
        assert _cond.type.equals(new IntegerType(1));
        addOperand(_cond);
        addOperand(_true_block);
        addOperand(_false_block);
    }

    public InstructionBr(IRBasicBlock _block, IRBasicBlock _next_block){
        super(_block, "_br", new VoidType());
        addOperand(_next_block);
    }

    @Override
    public String toString(){
        if(operandList.size() == 1){
            return "br " + getOperand(0).printValueString();
        }else {
            return "br " + getOperand(0).printValueString() + ", " + getOperand(1).printValueString() + ", " + getOperand(2).printValueString();
        }
    }

    @Override
    public void accept(IRVisitor _visitor){_visitor.visit(this);}

}
