package Backend.Instruction;

import Backend.ASMBasicBlock;
import Backend.Operand.BaseReg;

public abstract class ASMInstruction {
    public BaseReg rs1,rs2,rd;
    public String operator_name;

    public ASMInstruction(ASMBasicBlock _block, String _op_name){
        if(_block != null)_block.addInst(this);
        operator_name = _op_name;
        rs1 = rs2 = rd = null;
    }

    public abstract ASMInstruction addBaseReg(BaseReg ... regs);

    public abstract String print_ASM_string();
}
