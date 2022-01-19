package IR.Operand;

import IR.TypeSystem.SingleValueType;

public abstract class Const extends Operand{
    public Const(SingleValueType _type){
        super(_type,null);
    }

    @Override
    public abstract String toString();
}
