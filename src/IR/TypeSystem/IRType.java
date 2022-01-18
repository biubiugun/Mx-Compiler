package IR.TypeSystem;

abstract public class IRType {
    public IRType(){}

    abstract public String toString();

    abstract public boolean equals(Object obj);

    abstract public int byteSize();
}
