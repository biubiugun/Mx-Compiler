package IR.TypeSystem;

import java.util.ArrayList;
import java.util.HashMap;

public class StructType extends IRType{
    public HashMap<String, IRType> typeTable;
    public HashMap<String, Integer> indexTable;
    public ArrayList<IRType> typeList;
    public Integer count = 0;
    public String name;

    public StructType(String _name){
        typeTable = new HashMap<>();
        indexTable = new HashMap<>();
        typeList = new ArrayList<>();
        name = "class_" + _name;
    }

    public void addMember(String identifier, IRType ty){
        typeTable.put(identifier,ty);
        indexTable.put(identifier, count++);
        typeList.add(ty);
    }

    public int getOffset(int index){
        int ans = 0;
        for(int i = 0;i < index;++i){
            ans += typeList.get(i).byteSize();
        }
        return ans;
    }

    @Override
    public int byteSize() {
        int total = 0;
        for(IRType ty : typeTable.values()) total += ty.byteSize();
        return total;
    }

    @Override
    public String toString() {
        return "%" + name;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof StructType) && ((StructType)obj).name.equals(name);
    }

}
