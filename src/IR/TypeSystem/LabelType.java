package IR.TypeSystem;

public class LabelType extends IRType{
    public String name;
    public LabelType(String _name){
        super();
        name = _name;
    }

    @Override
    public String toString(){
        return "Label:" + name;
    }

    @Override
    public boolean equals(Object obj){
        return obj instanceof LabelType;
    }

    @Override
    public int byteSize(){throw new RuntimeException("[Error]:Label type byte size?");}

}
