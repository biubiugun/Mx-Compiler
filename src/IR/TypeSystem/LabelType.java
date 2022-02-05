package IR.TypeSystem;

public class LabelType extends IRType{
    public LabelType(){
        super();
    }

    @Override
    public String toString(){
        return "label";
    }

    @Override
    public boolean equals(Object obj){
        return obj instanceof LabelType;
    }

    @Override
    public int byteSize(){throw new RuntimeException("[Error]:Label type byte size?");}

}
