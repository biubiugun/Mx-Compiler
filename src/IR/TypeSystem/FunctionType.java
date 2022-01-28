package IR.TypeSystem;

import java.util.ArrayList;

public class FunctionType extends IRType{
    public IRType returnType;
    public ArrayList<IRType> paraTypeList;
    public ArrayList<String> paraNameList;

    public FunctionType(IRType _returnType){
        super();
        returnType = _returnType;
        paraTypeList = new ArrayList<>();
        paraNameList = new ArrayList<>();
    }

    public void addPara(IRType _type, String _name){
        paraNameList.add(_name);
        paraTypeList.add(_type);
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
