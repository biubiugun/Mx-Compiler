package IR.TypeSystem;

public abstract class SingleValueType extends IRType{
    public SingleValueType(){super();}

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int byteSize();
}
