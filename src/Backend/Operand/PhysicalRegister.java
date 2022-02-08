package Backend.Operand;

import java.util.ArrayList;

public class PhysicalRegister extends Register{
    public static final ArrayList<String> physical_register_nameList = new ArrayList<>(){
        {
            add("zero");add("ra");add("sp");add("gp");add("tp");
            add("t0");add("t1");add("t2");add("s0");add("s1");
            add("a0");add("a1");add("a2");add("a3");add("a4");add("a5");add("a6");add("a7");
            add("s2");add("s3");add("s4");add("s5");add("s6");add("s7");add("s8");add("s9");add("s10");add("s11");
            add("t3");add("t4");add("t5");add("t6");
        }
    };

    public static PhysicalRegister getPhysicalReg(int i){
        return new PhysicalRegister(physical_register_nameList.get(i));
    }

    public PhysicalRegister(String _name){super(_name);}

    public PhysicalRegister(VirtualRegister _vReg){
        super(physical_register_nameList.get(_vReg.colour));
        colour = _vReg.colour;
        offset = _vReg.offset;
    }

    public PhysicalRegister(int _colour,VirtualRegister _vReg){
        super(physical_register_nameList.get(_colour));
        colour = _colour;
        offset = new Immediate(_vReg.offset.value);
    }

    public PhysicalRegister(String _name,Immediate _offset){
        super(_name);
        offset = _offset;
    }

}
