package Backend;

import Backend.Operand.BaseReg;
import Backend.Operand.Immediate;
import Backend.Operand.Register;

import java.util.ArrayList;

public class ASMFunction extends BaseReg {
    public ArrayList<ASMBasicBlock> blockList = new ArrayList<>();
    public ArrayList<Register> RegList = new ArrayList<>();
    public boolean isBuiltin;
    public int stack_offset;
    public int virtual_index;

    public ASMFunction(String _func_name){
        super(_func_name);
        isBuiltin = false;
        stack_offset = 0;
        virtual_index = 0;
    }

    public void addBlock(ASMBasicBlock _block){
        blockList.add(_block);
    }

    public void stack_alloc_update(){
        stack_offset += 4;
    }

    public Immediate stack_alloc_immediate(){
        return new Immediate(stack_offset);
    }

    public String print_ASM_string(){
        StringBuilder str = new StringBuilder();
        str.append('\t').append(".globl").append('\t').append(GetName()).append('\n');
        str.append('\t').append(".p2align").append('\t').append(1).append('\n');
        str.append('\t').append(".type").append('\t').append(GetName()).append(",@function\n");
        str.append(GetName()).append(':').append('\n');
        blockList.forEach(block -> str.append(block.print_ASM_string()).append('\n'));
        str.append('\t').append(".size").append('\t').append(GetName()).append(", .-").append(GetName()).append('\n');
        str.append("\t\t\t # -- End function");
        return str.toString();
    }
}
