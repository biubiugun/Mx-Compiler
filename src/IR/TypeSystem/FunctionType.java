package IR.TypeSystem;

import java.util.ArrayList;

public class FunctionType extends IRType{
    public IRType returnType;
    public ArrayList<IRType> paraTypeList;
    public String funcName;

    public FunctionType(IRType _returnType, ArrayList<IRType> _paraTypeList){
        super();
        returnType = _returnType;
        paraTypeList = _paraTypeList;
    }

    @Override
    public String toString(){
        return returnType.toString();
    }

    @Override
    public boolean equals(Object obj){
        throw new RuntimeException("[Error]:How a function equals another?");
    }

    @Override
    public int byteSize(){
        throw new RuntimeException("[Error]:Why let a function show its size?");
    }
}
