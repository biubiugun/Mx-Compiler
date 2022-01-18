package IR.TypeSystem;

import java.util.ArrayList;

public class StructType extends IRType{
    public String StructName;
    public ArrayList<IRType> paraTypeList;

    public StructType(String _StructName, ArrayList<IRType> _paraTypeList){
        super();
        StructName = _StructName;
        paraTypeList = _paraTypeList;
    }

    @Override
    public String toString(){return "%" + StructName;}

    @Override
    public boolean equals(Object obj){
        return (obj instanceof StructType) && ((StructType) obj).StructName.equals(StructName);
    }

    @Override
    public int byteSize(){
        int total_byteSize = 0;
        for(var it : paraTypeList){
            total_byteSize += it.byteSize();
        }
        return total_byteSize;
    }

}
