package IR;


import IR.Operand.User;
import IR.Operand.Value;
import IR.TypeSystem.FunctionType;
import IR.TypeSystem.IRType;

import java.util.ArrayList;

public class IRFunction extends User {
    public ArrayList<IRBasicBlock> blockList;
    public boolean isBuiltin;
    public boolean beenUsed;

    public Value returnAddress = null;

    public IRFunction(String _name, IRType _type){
        super(_name,_type);
        isBuiltin = false;
        beenUsed = false;
        blockList = new ArrayList<>();
    }

    public void addBlock(IRBasicBlock _block){blockList.add(_block);}

    public void addPara(Value _para){operandList.add(_para);}

    @Override
    public String GetName(){return "@" + name;}

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        if(!isBuiltin){
            str.append("define ").append(type.toString()).append(" ").append(GetName()).append("(");
            if(!this.operandList.isEmpty()){
                operandList.forEach(op -> str.append(op.printValueString()).append(", "));
                str.delete(str.length() - 2,str.length());
            }
            str.append(")\t{\n");
            blockList.forEach(bk -> str.append(bk.toString()));
            str.append("}\n");
        }else if(beenUsed){
            str.append("declare ").append(type.toString()).append(" ").append(GetName()).append("(");
            if(!((FunctionType)type).paraTypeList.isEmpty()){
                ((FunctionType)type).paraTypeList.forEach(para -> str.append(para.toString()).append(", "));
                str.delete(str.length() - 2,str.length());
            }
            str.append(")\n");
        }
        return str.toString();
    }

    @Override
    public void accept(IRVisitor _visitor){_visitor.visit(this);}
}
