package IR;

import IR.Operand.Operand;
import IR.Operand.Register;
import IR.TypeSystem.IRType;

import java.util.ArrayList;
import java.util.LinkedList;

public class IRFunction {
    public IRType returnType;
    public String FunctionName;
    public ArrayList<Register> formParaList;
    public LinkedList<IRBasicBlock> blockList;
    public boolean isBuiltin;

    public IRFunction(IRType _returnType, String _name, ArrayList<Register> _formParaList, LinkedList<IRBasicBlock> _blockList){
        returnType = _returnType;
        FunctionName = _name;
        formParaList = _formParaList;
        blockList = _blockList;
        isBuiltin = false;
    }

    public String toString(ArrayList<Operand> realParaList){
        StringBuilder str = new StringBuilder("(");
        if(realParaList != null){
            str.append(realParaList.get(0).typename.toString()).append(" ").append(realParaList.get(0).toString());
            for(int i = 1;i < realParaList.size(); ++i){
                str.append(",").append(realParaList.get(i).typename.toString()).append(" ").append(realParaList.get(i).toString());
            }
        }
        str.append(")");
        return returnType.toString() + " @" + FunctionName + str.toString();
    }

}
