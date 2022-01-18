package IR.TypeSystem;

public class VoidType extends IRType{
    public VoidType(){
        super();
    }

    @Override
    public String toString(){
        return "void";
    }

    @Override
    public boolean equals(Object obj){
        return obj instanceof VoidType;
    }

    @Override
    public int byteSize(){throw new RuntimeException("[Error]:Void type byte size?");}
}
