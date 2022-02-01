package Backend.Instruction;

import Backend.ASMBasicBlock;
import Backend.Operand.BaseReg;

public class Call_Inst extends ASMInstruction{
    public Call_Inst(ASMBasicBlock _block){
        super(_block,"call");
    }

    @Override
    public ASMInstruction addBaseReg(BaseReg ... regs){
        assert regs.length == 1;
        rd = regs[0];
        return this;
    }

    @Override
    public String print_ASM_string(){
        return operator_name + "\t" + rd.GetName();
    }
}
