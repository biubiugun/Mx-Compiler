package IR.Operand;

import IR.TypeSystem.BoolType;

public class BoolConst extends Const{
    public boolean value;

    public BoolConst(boolean _value){
        super(new BoolType());
        value = _value;
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }
}
