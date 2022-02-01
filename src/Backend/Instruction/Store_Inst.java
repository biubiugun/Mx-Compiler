package Backend.Instruction;

import Backend.ASMBasicBlock;
import Backend.Operand.BaseReg;
import Backend.Operand.Register;

public class Store_Inst extends ASMInstruction{
    public Store_Inst(ASMBasicBlock _block, String _op_name){
        super(_block,_op_name);
    }

    @Override
    public ASMInstruction addBaseReg(BaseReg ... regs){
        assert regs.length == 2;
        rs1 = regs[1];
        rs2 = regs[0];
        return this;
    }

    @Override
    public String print_ASM_string(){
        return operator_name + "\t" + rs2.GetName() + ", " + ((Register)rs1).offset + "(" + rs1.GetName() + ")";
    }
}
