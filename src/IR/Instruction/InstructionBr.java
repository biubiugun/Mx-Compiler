package IR.Instruction;

import IR.IRBasicBlock;
import IR.Operand.Operand;

public class InstructionBr extends Instruction{
    public IRBasicBlock TrueBlock;
    public IRBasicBlock FalseBlock;
    public Operand cond;

    public InstructionBr(IRBasicBlock _block, IRBasicBlock _true_block, IRBasicBlock _false_block, Operand _cond){
        super(_block);
        TrueBlock = _true_block;
        FalseBlock = _false_block;
        cond = _cond;
    }

    public InstructionBr(IRBasicBlock _block, IRBasicBlock _next_block){
        super(_block);
        TrueBlock = _next_block;
        FalseBlock = null;
        cond = null;
    }

    @Override
    public String toString(){
        if(cond == null){
            return "br label" + TrueBlock.toString();
        }else return "br " + cond.typename.toString() + " " + cond.toString() + ", label " + TrueBlock.toString() + ", label " + FalseBlock.toString();
    }

}
