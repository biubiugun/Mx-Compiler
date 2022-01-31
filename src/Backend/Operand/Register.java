package Backend.Operand;

public class Register extends BaseReg{
    public int colour; // -1 for null, 0~31 for physical reg
    public Immediate offset;
    public boolean in_memory;

    public Register(String _name){
        super(_name);
        in_memory = false;
        colour = -1;
        offset = new Immediate(0);
    }

    public void setColour(int _colour){
        assert colour == -1;
        colour = _colour;
    }

}
