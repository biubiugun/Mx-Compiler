package IR;

import IR.Instruction.Instruction;
import IR.Instruction.InstructionBr;
import IR.Instruction.InstructionRet;
import IR.Operand.Value;
import IR.TypeSystem.IRType;
import IR.TypeSystem.LabelType;

import java.util.LinkedList;

public class IRBasicBlock extends Value {
    public Instruction terminalInst;
    public LinkedList<Instruction> InstList;
    public IRFunction function_belongs_to;

    public IRBasicBlock(String _name, IRFunction _parent_func){
        super(_name,new LabelType());
        InstList = new LinkedList<>();
        terminalInst = null;
        function_belongs_to = _parent_func;
    }

    public void push_back_inst(Instruction Inst){
        if(Inst instanceof InstructionBr || Inst instanceof InstructionRet){
            if(terminalInst == null){
                terminalInst = Inst;
            }else{
                throw new RuntimeException("[Debug]: set terminator twice!");
            }
        }
        else InstList.add(Inst);
    }

//    public void setTerminalInst(Instruction Inst){
//        if(terminalInst == null){
//            terminalInst = Inst;
//        }
//    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder(name + ":");
        if(!userList.isEmpty()){//pred
            str.append("\t\t\t\t\t ;preds = ");
            userList.forEach(user -> str.append(((InstructionBr)user).block_belongs_to.GetName()).append(", "));
            str.delete(str.length() - 2,str.length());
        }
        str.append("\n");
        InstList.forEach(inst -> str.append("\t").append(inst.toString()).append("\n"));
        str.append("\t").append(terminalInst.toString()).append("\n");
        return str.toString();
    }

    public void accept(IRVisitor _visitor){_visitor.visit(this);}
}
