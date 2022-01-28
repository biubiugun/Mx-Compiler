package IR.TypeSystem;

abstract public class IRType {
    public IRType(){}

    abstract public String toString();

    abstract public boolean equals(Object obj);

    abstract public int byteSize();

    public IRType dePointed(){
        if(this instanceof PointerType){
            if(((PointerType) this).level == 1) {
                return ((PointerType) this).type_base;
            } else {
                return new PointerType(((PointerType) this).type_base, ((PointerType) this).level - 1);
            }
        }else throw new RuntimeException("[Debug] address not PointerType");
    }
}
