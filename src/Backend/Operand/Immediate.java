package Backend.Operand;

public class Immediate extends BaseReg{
    public int value;

    public Immediate(int _value){
        super(String.valueOf(_value));
        value = _value;
    }

    public Immediate negative(){
        return new Immediate(-value);
    }

    public String toString(){
        return name;
    }
}
