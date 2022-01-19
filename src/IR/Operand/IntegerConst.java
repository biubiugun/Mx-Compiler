package IR.Operand;

import IR.TypeSystem.IntegerType;

public class IntegerConst extends Const{
    public int value;
    public int bitWidth;

    public IntegerConst(int _width, int _value){
        super(new IntegerType(_width));
        bitWidth = _width;
        value = _value;
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }
}
