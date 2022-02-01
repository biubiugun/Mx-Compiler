package Backend.Instruction;

import Backend.ASMBasicBlock;
import Backend.Operand.BaseReg;

public class Jump_Inst extends ASMInstruction{
    public Jump_Inst(ASMBasicBlock _block){
        super(_block,"j");
    }

    @Override
    public ASMInstruction addBaseReg(BaseReg ... regs){
        assert regs.length == 1;
        rd = regs[0];
        return this;
    }

    @Override
    public String print_ASM_string(){
        return "j\t" + rd.GetName();
    }
}
