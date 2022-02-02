package Backend.Instruction;

import Backend.ASMBasicBlock;
import Backend.Operand.BaseReg;

public class Seqz_Inst extends ASMInstruction{
    public Seqz_Inst(ASMBasicBlock _block){
        super(_block,"seqz");
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
        return "seqz\t" + rd.GetName() + ", " + rs1.GetName();
    }
}
