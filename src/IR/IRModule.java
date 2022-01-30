package IR;

import IR.Instruction.InstructionGlobal;
import IR.Operand.GlobalVariable;
import IR.Operand.StringConst;
import IR.TypeSystem.StructType;

import java.util.ArrayList;

public class IRModule {
    public ArrayList<IRFunction> functionList;
    public ArrayList<StringConst> stringList;
    public ArrayList<InstructionGlobal> globalVariableList;
    public ArrayList<StructType> structList;
    public ArrayList<IRFunction> InitList;

    public IRModule(){
        functionList = new ArrayList<>();
        stringList = new ArrayList<>();
        globalVariableList = new ArrayList<>();
        structList = new ArrayList<>();
        InitList = new ArrayList<>();
    }

    public void addFunc(IRFunction _func){
        functionList.add(_func);
    }

    public void addStr(StringConst _str){
        stringList.add(_str);
    }

    public void addVar(InstructionGlobal _globalVar){
        globalVariableList.add(_globalVar);
    }

    public void addClass(StructType _class){
        structList.add(_class);
    }

    public void addInitFunc(IRFunction _global_init){
        InitList.add(_global_init);
    }

    public String toString(){
        StringBuilder raw = new StringBuilder();
        if(structList.size() != 0) structList.forEach(tmp->{
            raw.append(tmp.toString()).append(" = type { ");
            tmp.typeTable.forEach((tmpName,tmpTy)->raw.append(tmpTy.toString()).append(", "));
            raw.delete(raw.length() - 2, raw.length()).append(" }\n");
        });
        if(stringList.size() != 0) stringList.forEach(tmp->raw.append(tmp.toString()).append("\n"));
        if(globalVariableList.size() != 0) globalVariableList.forEach(tmp->raw.append(tmp.toString()).append("\n"));
        if(InitList.size() != 0) {
            raw.append("@llvm.global_ctors = appending global [1 x { i32, void ()*, i8* }] [{ i32, void ()*, i8* } { i32 65535, void ()* @_GLOBAL_, i8* null }]").append("\n");
            InitList.forEach(tmp->raw.append(tmp.toString()).append("\n"));
        }
        functionList.forEach(tmp->raw.append(tmp.toString()));
        return raw.toString();
    }
}
