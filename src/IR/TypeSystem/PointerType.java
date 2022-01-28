package IR.TypeSystem;

public class PointerType extends SingleValueType{
    public IRType type_base;
    public int level;

    public PointerType(IRType _type, int _level){
        super();
        if(_type instanceof PointerType){
            type_base = ((PointerType) _type).type_base;
            level = ((PointerType) _type).level + _level;
        }else {
            type_base = _type;
            level = _level;
        }
    }

    @Override
    public String toString(){
        return type_base.toString() + ("*").repeat(level);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof PointerType){
            return type_base.equals(((PointerType) obj).type_base) && (level == ((PointerType) obj).level);
        }else return false;
    }

    @Override
    public int byteSize(){return 8;}
}
