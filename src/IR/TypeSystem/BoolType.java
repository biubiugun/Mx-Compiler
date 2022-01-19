package IR.TypeSystem;

public class BoolType extends SingleValueType{
    public BoolType(){
        super();
    }

    @Override
    public String toString(){
        return "i8";
    }

    @Override
    public boolean equals(Object obj){
        return (obj instanceof BoolType);
    }

    @Override
    public int byteSize(){return 1;}
}
