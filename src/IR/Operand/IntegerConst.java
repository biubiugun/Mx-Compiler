package IR.Operand;

import IR.IRVisitor;
import IR.TypeSystem.IntegerType;

public class IntegerConst extends Const{
    public int value;

    public IntegerConst(int _value){
        super("_int_const",new IntegerType(32));
        value = _value;
    }

    @Override
    public String toString(){
        throw new RuntimeException("[Debug] Why use toString in integerConst, do you mean GetName ?");
    }

    @Override
    public String GetName(){
        return String.valueOf(value);
    }

    @Override
    public void accept(IRVisitor _visitor){
        _visitor.visit(this);
    }
}
