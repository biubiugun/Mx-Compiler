package Backend.Operand;

public abstract class Operand {
    public String name;

    public Operand(String _name){
        name = _name;
    }

    public String GetName(){
        return name;
    }
}
