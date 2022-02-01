package Backend.Instruction;

import Backend.ASMBasicBlock;
import Backend.Operand.BaseReg;

public class Branch_Inst extends ASMInstruction{
    public Branch_Inst(ASMBasicBlock _block, String _op_name){
        super(_block,_op_name);
    }

    @Override
    public ASMInstruction addBaseReg(BaseReg ... regs){
        assert regs.length == 3;
        rd = regs[0];
        rs1 = regs[1];
        rs2 = regs[2];
        return this;
    }

    @Override
    public String print_ASM_string(){
        return operator_name + "\t" + rd.GetName() + ", " + rs1.GetName() + ", " + rs2.GetName();
    }
}
