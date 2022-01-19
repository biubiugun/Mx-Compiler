package IR.Operand;

import IR.TypeSystem.PointerType;
import IR.TypeSystem.SingleValueType;

public class NullConst extends Const{
    public NullConst(SingleValueType _type){
        super(_type);
    }

    @Override
    public String toString(){return "null";}
}
