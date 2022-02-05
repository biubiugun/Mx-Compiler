package IR.Operand;

import Backend.Operand.BaseReg;
import IR.TypeSystem.IRType;
import IR.TypeSystem.IntegerType;

import java.util.ArrayList;
import java.util.HashMap;

public class Value {
    public BaseReg ASMOp;
    public String name;
    public IRType type;
    public ArrayList<User> userList;
    public static HashMap<String, Integer> name_table = new HashMap<>();

    public Value(String _name, IRType _type){
        name = renaming(_name);
        type = _type;
        userList = new ArrayList<>();
    }

    public String renaming(String id){
        if(id.equals("_f_main"))return "main";
        String new_name;
        if(name_table.containsKey(id)){
            new_name = id + name_table.get(id);
            Integer cnt = name_table.get(id) + 1;
            name_table.put(id,cnt);
        }else {
            new_name = id;
            name_table.put(id,1);
        }
        return new_name;
    }

    public void addUser(User _new_user){
        userList.add(_new_user);
    }

    public String GetName(){return "%" + name;}

    public String GetType(){return type.toString();}

    public String printValueString(){
        return GetType() + " " + GetName();
    }

    public String toString(){throw new RuntimeException("[Debug] Why use base value's toString");}
}
