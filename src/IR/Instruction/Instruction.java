package IR.Instruction;

import IR.IRBasicBlock;
import IR.Operand.User;
import IR.TypeSystem.IRType;

public abstract class Instruction extends User {
    public IRBasicBlock block_belongs_to;

    public Instruction(IRBasicBlock _block, String _name, IRType _type){
        super(_name,_type);
        block_belongs_to = _block;
    }

    @Override
    public abstract String toString();
}
