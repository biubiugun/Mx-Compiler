package IR;

import IR.Instruction.Instruction;
import IR.TypeSystem.LabelType;

import java.util.LinkedList;

public class IRBasicBlock {
    public LabelType BlockLabel;
    public Instruction terminalInst;
    public LinkedList<Instruction> InstList;
    public int loopDepth;

    public IRBasicBlock(String label_name){
        InstList = new LinkedList<>();
        terminalInst = null;
        loopDepth = 0;
        BlockLabel = new LabelType(label_name);
    }

    public void push_back(Instruction Inst){
        InstList.add(Inst);
    }

    public void setTerminalInst(Instruction Inst){
        if(terminalInst == null){
            terminalInst = Inst;
        }
    }

    public String toString(){
        return "%" + BlockLabel.name;
    }

    public String getBlockLabel(){return BlockLabel.name;}

}
