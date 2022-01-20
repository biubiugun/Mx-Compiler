package IR.Instruction;

import IR.IRBasicBlock;

public abstract class Instruction {
    public IRBasicBlock block_belongs_to;

    public Instruction(IRBasicBlock _block){
        block_belongs_to = _block;
    }

    @Override
    public abstract String toString();
}
