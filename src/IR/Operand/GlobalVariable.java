package IR.Operand;

import IR.TypeSystem.IRType;
import IR.TypeSystem.PointerType;

public class GlobalVariable extends Operand{
    public String name;
    public String value;

    public GlobalVariable(IRType _type, String _name, String _value){
        super(new PointerType(_type,1),_name);
        name = _name;
        value = _value;
    }

    @Override
    public String toString(){
        return "@" + this.name;
    }

    public String printString(){
        return "@" + this.name + " = global " + ((PointerType)typename).type.toString() + " " + value + ", align " + typename.byteSize();
    }
}
