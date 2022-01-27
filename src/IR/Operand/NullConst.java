package IR.Operand;

import IR.IRVisitor;
import IR.TypeSystem.PointerType;
import IR.TypeSystem.SingleValueType;
import IR.TypeSystem.VoidType;

public class NullConst extends Const{
    public NullConst(PointerType _type){
        super("null",_type);
    }

    public NullConst(){
        super("null",new PointerType(new VoidType(),1));
    }

    @Override
    public String toString(){throw new RuntimeException("[Debug] Why use toString in nullConstant, do you mean GetName ?");}

    @Override
    public String GetName(){return "null";}

    @Override
    public void accept(IRVisitor _visitor){
        _visitor.visit(this);
    }
}
