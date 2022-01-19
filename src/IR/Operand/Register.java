package IR.Operand;

import IR.TypeSystem.SingleValueType;

public  class Register extends Operand{
    public Operand RegValue;

    public Register(SingleValueType _type,String _name, Operand _value){
        super(_type,_name);
        RegValue = _value;
    }

    @Override
    public String toString(){return "%" + this.name;}

    public int byteSize(){return this.typename.byteSize();}
}
