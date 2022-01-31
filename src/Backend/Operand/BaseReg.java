package Backend.Operand;

public abstract class BaseReg {
    public String name;

    public BaseReg(String _name){
        name = _name;
    }

    public String GetName(){
        return name;
    }
}
