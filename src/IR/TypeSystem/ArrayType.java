package IR.TypeSystem;

public class ArrayType extends IRType{
    public IRType type;
    public int dim;

    public ArrayType(IRType _type,int _dim){
        super();
        type = _type;
        dim = _dim;
    }

    @Override
    public String toString(){
        return "[" + dim + "x" + type.toString() + "]";
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof ArrayType){
            return type.equals(((ArrayType) obj).type) && dim == ((ArrayType) obj).dim;
        }else return false;
    }

    @Override
    public int byteSize(){
        throw new RuntimeException("[Debug] Why use byteSize in Array type ?");
    }
}
