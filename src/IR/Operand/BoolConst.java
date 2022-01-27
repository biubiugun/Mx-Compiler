package IR.Operand;

import IR.IRVisitor;
import IR.TypeSystem.BoolType;

public class BoolConst extends Const{
    public boolean value;

    public BoolConst(boolean _value){
        super("_bool_const",new BoolType());
        value = _value;
    }

    @Override
    public String GetName(){
        return String.valueOf(value);
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }

    @Override
    public void accept(IRVisitor _visitor){
        _visitor.visit(this);
    }
}
