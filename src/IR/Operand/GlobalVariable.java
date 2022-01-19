package IR.Operand;

import IR.TypeSystem.IRType;
import IR.TypeSystem.PointerType;

public class GlobalVariable extends Operand{
    public GlobalVariable(IRType _type, String _name){
        super(new PointerType(_type,1),_name);
    }

    @Override
    public String toString(){
        return "@" + this.name;
    }

    public String printString(){
        return "global:" + this.typename.toString() + " @" + this.name;
    }
}
