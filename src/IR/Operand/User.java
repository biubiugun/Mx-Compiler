package IR.Operand;

import IR.IRVisitor;
import IR.TypeSystem.IRType;

import java.util.ArrayList;

public abstract class User extends Value{
    public ArrayList<Value> operandList;

    public User(String _name, IRType _type){
        super(_name,_type);
        operandList = new ArrayList<>();
    }

    public void addOperand(Value _value){
        operandList.add(_value);
        _value.addUser(this);
    }

    public Value getOperand(int i){
        return operandList.get(i);
    }

    public abstract void accept(IRVisitor _visitor);
}
