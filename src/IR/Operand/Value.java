package IR.Operand;

import IR.TypeSystem.IRType;

import java.util.ArrayList;

public class Value {
    public Operand ASMOp;
    public String name;
    public IRType type;
    public ArrayList<User> userList;

    public Value(String _name, IRType _type){
        name = _name;
        type = _type;
        userList = new ArrayList<>();
    }

    public void addUser(User _new_user){
        userList.add(_new_user);
    }

    public String GetName(){return "%" + name;}

    public String GetType(){return type.toString();}

    public String printValueString(){
        return GetName() + " " + GetType();
    }

    public String toString(){throw new RuntimeException("[Debug] Why use base value's toString");}
}
