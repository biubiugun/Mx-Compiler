package IR.Operand;

import IR.TypeSystem.IRType;
import IR.TypeSystem.SingleValueType;

public abstract class Const extends User{
    public Const(String _name,IRType _type){
        super(_name,_type);
    }

    @Override
    public abstract String toString();
}
