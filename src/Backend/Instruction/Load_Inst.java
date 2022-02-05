package Backend.Instruction;

import Backend.ASMBasicBlock;
import Backend.Operand.BaseReg;
import Backend.Operand.Register;

public class Load_Inst extends ASMInstruction{
    public Load_Inst(ASMBasicBlock _block, String _op_name){
        super(_block,_op_name);
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
        return operator_name + "\t" + rd.GetName() + ", " + ((Register)rs1).offset.value + "(" + rs1.GetName() + ")";
    }
}
