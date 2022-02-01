package Backend;

import Backend.Instruction.ASMInstruction;
import Backend.Operand.BaseReg;

import java.util.LinkedList;

public class ASMBasicBlock extends BaseReg {
    public LinkedList<ASMInstruction> InstList = new LinkedList<>();

    public ASMBasicBlock(String _name,ASMFunction _func){
        super(_name);
        _func.addBlock(this);
    }

    public void addInst(ASMInstruction _inst){
        InstList.add(_inst);
    }

    public String print_ASM_string(){
        StringBuilder str = new StringBuilder();
        str.append(GetName()).append("\n");
        InstList.forEach(inst -> {
            str.append("\t").append(inst.print_ASM_string()).append("\n");
        });
        return str.toString();
    }
}
