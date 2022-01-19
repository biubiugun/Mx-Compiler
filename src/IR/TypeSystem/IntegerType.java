package IR.TypeSystem;

public class IntegerType extends SingleValueType{
    public int bitWidth;

    public IntegerType(int _bitWidth){
        super();
        bitWidth = _bitWidth;
    }

    @Override
    public String toString(){
        return "i" + bitWidth;
    }

    @Override
    public boolean equals(Object obj){
        return (obj instanceof IntegerType) && bitWidth == ((IntegerType) obj).bitWidth;
    }

    @Override
    public int byteSize(){
        return bitWidth/8;
    }

}
