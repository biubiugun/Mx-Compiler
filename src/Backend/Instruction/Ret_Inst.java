package Backend.Instruction;

import Backend.ASMBasicBlock;
import Backend.Operand.BaseReg;

public class Ret_Inst extends ASMInstruction{
    public Ret_Inst(ASMBasicBlock _block){
        super(_block,"ret");
    }

    @Override
    public ASMInstruction addBaseReg(BaseReg ... regs){
        assert regs.length == 0;
        return this;
    }

    @Override
    public String print_ASM_string(){
        return "ret\t";// jalr x0, rs, 0
    }

}
