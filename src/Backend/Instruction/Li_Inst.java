package Backend.Instruction;

import Backend.ASMBasicBlock;
import Backend.Operand.BaseReg;
import Backend.Operand.Immediate;

public class Li_Inst extends ASMInstruction{
    public Li_Inst(ASMBasicBlock _block){
        super(_block,"li");
    }

    @Override
    public ASMInstruction addBaseReg(BaseReg ... regs){
        assert regs.length == 2;
        rd = regs[0];
        rs1 = regs[1];
        assert rs1 instanceof Immediate;
        return this;
    }

    @Override
    public String print_ASM_string(){
        return "li\t" + rd.GetName() + ", " + rs1.GetName();
    }
}
