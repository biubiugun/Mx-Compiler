package Util;

import AST.DefNode.ClassDefNode;
import AST.DefNode.FuncDefNode;

import java.util.HashMap;
import java.util.Map;

public class GlobalScope extends Scope{

    public Map<String, FuncDefNode> functionTable;
    public Map<String, ClassDefNode> classTable;

    public GlobalScope(Scope _parent){
        super(_parent);
        functionTable = new HashMap<>();
        classTable = new HashMap<>();
    }

    public boolean containsFunc(String funcName){
        return functionTable.containsKey(funcName);
    }

    public FuncDefNode getFunc(String funcName){
        return functionTable.getOrDefault(funcName, null);
    }

    public void setFunc(String funcName,FuncDefNode func){
        functionTable.put(funcName,func);
    }

    public boolean containsClass(String className){
        return classTable.containsKey(className);
    }

    public void setClass(String className,ClassDefNode Class){
        classTable.put(className,Class);
    }

}
