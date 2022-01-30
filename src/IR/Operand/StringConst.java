package IR.Operand;

import IR.IRVisitor;
import IR.TypeSystem.ArrayType;
import IR.TypeSystem.IntegerType;
import IR.TypeSystem.PointerType;

public class StringConst extends Const{
    public String value;

    // string after processing
    public StringConst(String _value){
        super("_str",new PointerType(new ArrayType(new IntegerType(8), _value.length()),1));
        this.value = _value;
    }

    @Override
    public String GetName() {
        return "@" + this.name;
    }

    @Override
    public String toString() {
        return GetName() + " = private unnamed_addr constant " + ((PointerType)type).type_base.toString() + " c\"" + processRaw(value) + "\", align 1";
    }

    private String processRaw(String raw){
        return raw
                .replace("\\", "\\5C")
                .replace("\n", "\\0A")
                .replace("\"", "\\22")
                .replace("\t", "\\09")
                .replace("\0","\\00");
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
