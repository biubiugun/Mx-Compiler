package Util;

import AST.TypeNode.TypeNode;

import java.util.HashMap;
import java.util.Map;

public class Scope {

    public Map<String, TypeNode> VarTable;
    public Scope parent;

    public Scope(Scope _parent){
        parent = _parent;
        VarTable = new HashMap<>();
    }

    public TypeNode getVarType(String ID){
        if(VarTable.containsKey(ID))return VarTable.get(ID);
        else if(this.parent != null)return parent.getVarType(ID);
        else return null;
    }

    public boolean containsVariable(String ID){
        return VarTable.containsKey(ID);
    }

    public void SetVariable(String ID,TypeNode type){
        VarTable.put(ID,type);
    }
}
