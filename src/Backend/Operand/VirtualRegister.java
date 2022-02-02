package Backend.Operand;

public class VirtualRegister extends Register{
    public VirtualRegister(int _v_reg_index){
        super("v" + String.valueOf(_v_reg_index));
        offset = new Immediate(0);
    }

    public VirtualRegister(int _v_reg_index,int _colour){
        super("v" + String.valueOf(_v_reg_index));
        offset = new Immediate(0);
        colour = _colour;
    }

    public VirtualRegister(VirtualRegister _other){
        super(_other.name);
        offset = new Immediate(_other.offset.value);
        colour = _other.colour;
    }

    public VirtualRegister(Immediate _offset,int _v_reg_index,int _colour){
        super("v" + String.valueOf(_v_reg_index));
        offset = _offset;
        colour = _colour;
    }

    @Override
    public String GetName(){
        return colour == -1 ? name : PhysicalRegister.physical_register_nameList.get(colour);
    }
}
