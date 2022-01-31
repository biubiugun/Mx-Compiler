package Backend.Operand;

public class VirtualRegister extends Register{
    public VirtualRegister(int _v_reg_index){
        super("v" + String.valueOf(_v_reg_index));
        offset = new Immediate(0);
    }


}
