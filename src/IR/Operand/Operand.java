package IR.Operand;

import IR.TypeSystem.SingleValueType;

public abstract class Operand {
    public SingleValueType typename;
    public String name;

    public Operand(SingleValueType _typename, String _name){
        typename = _typename;
        name = _name;
    }

    @Override
    public abstract String toString();

}
