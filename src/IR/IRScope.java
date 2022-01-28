package IR;

import IR.Operand.Value;

import java.util.HashMap;

public class IRScope {
    public IRScope fatherScope;
    public HashMap<String,Value> ValueNameTable;
    boolean validity;

    public enum scope_type{
        Global,Flow,Function,Common,Class
    }

    public scope_type type;

    public IRScope(IRScope _father, scope_type _type){
        fatherScope = _father;
        type = _type;
        ValueNameTable = new HashMap<>();
        if(_father != null){
            validity = _father.validity;
        }else validity = true;
    }

    public Value getValue(String id){
        if(ValueNameTable.containsKey(id)){
            return ValueNameTable.get(id);
        }else return fatherScope.getValue(id);
    }

    public boolean isClassMember(String id){
        if(ValueNameTable.containsKey(id)){
            return type == scope_type.Class;
        }else return fatherScope.isClassMember(id);
    }

    public void setVariable(String id,Value operand){
        ValueNameTable.put(id,operand);
    }

    public void setInvalidity(){
        if(type == scope_type.Common || type == scope_type.Function){
            validity = false;
        }
    }

    //upRoot

}
