package Backend;

import Backend.Operand.GlobalVariable;

import java.util.ArrayList;

public class ASMModule {
    public ArrayList<GlobalVariable> globalVarList = new ArrayList<>();
    public ArrayList<ASMFunction> functionList = new ArrayList<>();

    public void addGlobalVar(GlobalVariable _global_var){
        globalVarList.add(_global_var);
    }

    public void addFunction(ASMFunction _func){
        functionList.add(_func);
    }

    public String print_ASM_string(){
        StringBuilder str = new StringBuilder();
        str.append("\t").append(".text").append("\n");
        if(!functionList.isEmpty()){
            for(var i : functionList){
                if(i.isBuiltin)continue;
                str.append(i.print_ASM_string());
                str.append("\n");
            }
        }
        if(!globalVarList.isEmpty()){
            for(var i : globalVarList){
                str.append(i.print_ASM_string());
                str.append("\n");
            }
        }
        return str.toString();
    }
}
