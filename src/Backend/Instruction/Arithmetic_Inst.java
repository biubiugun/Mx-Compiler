package Backend.Instruction;

import Backend.ASMBasicBlock;
import Backend.Operand.BaseReg;
import Backend.Operand.Immediate;

public class Arithmetic_Inst extends ASMInstruction{
    public boolean is_immediate_inst = false;

    public Arithmetic_Inst(ASMBasicBlock _block,String _op_name){
        super(_block,_op_name);
    }

    @Override
    public ASMInstruction addBaseReg(BaseReg ... regs){
        assert regs.length == 3;
        rd = regs[0];
        rs1 = regs[1];
        rs2 = regs[2];
        if(rs2 instanceof Immediate)is_immediate_inst = true;
        return this;
    }

    @Override
    public String print_ASM_string(){
        return is_immediate_inst ? (operator_name + "i\t" + rd.GetName() + ", " + rs1.GetName() + ", " + rs2.GetName())
                : (operator_name + "\t" + rd.GetName() + ", " + rs1.GetName() + ", " + rs2.GetName());
    }
}
