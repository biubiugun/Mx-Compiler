package Backend.Instruction;

import Backend.ASMBasicBlock;
import Backend.Operand.BaseReg;

public class La_Inst extends ASMInstruction{
    public La_Inst(ASMBasicBlock _block){
        super(_block,"la");
    }

    @Override
    public ASMInstruction addBaseReg(BaseReg ... regs){
        assert regs.length == 2;
        rd = regs[0];
        rs1 = regs[1];
        return this;
    }

    @Override
    public String print_ASM_string(){
        return "la\t" + rd.GetName() + ", " + rs1.GetName();
    }
}
