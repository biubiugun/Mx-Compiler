package IR.TypeSystem;

public class PointerType extends SingleValueType{
    public IRType type;
    public int level;

    public PointerType(IRType _type, int _level){
        super();
        if(_type instanceof PointerType){
            type = ((PointerType) _type).type;
            level = ((PointerType) _type).level + _level;
        }else {
            type = _type;
            level = _level;
        }
    }

    @Override
    public String toString(){
        return type.toString() + ("*").repeat(level);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof PointerType){
            return type.equals(((PointerType) obj).type) && (level == ((PointerType) obj).level);
        }else return false;
    }

    @Override
    public int byteSize(){return 8;}
}
