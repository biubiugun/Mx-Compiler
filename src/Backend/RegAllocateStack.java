package Backend;

import Backend.Instruction.*;
import Backend.Operand.Immediate;
import Backend.Operand.PhysicalRegister;
import Backend.Operand.VirtualRegister;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;

public class RegAllocateStack {
    public ASMModule base_module;
    public HashMap<String, Integer> reg_name_order_table = new HashMap<>();

    public RegAllocateStack(ASMModule _module){
        base_module = _module;
    }

    private void addInst_load_(ListIterator<ASMInstruction>it, String name, Immediate offset){
        assert offset.value >= 0;
        it.previous();
        if(offset.value < 2048){
            ASMInstruction addInst =
                    new Load_Inst(null,"lw").addBaseReg(new PhysicalRegister(name),new PhysicalRegister("s0",offset.negative()));
            it.add(addInst);
        }else {
            it.add(new Li_Inst(null).addBaseReg(new PhysicalRegister("t3"),offset.negative()));
            it.add(new Arithmetic_Inst(null,"add").addBaseReg(new PhysicalRegister("t3"),new PhysicalRegister("s0"),new PhysicalRegister("t3")));
            it.add(new Load_Inst(null,"lw").addBaseReg(new PhysicalRegister(name),new PhysicalRegister("t3")));
        }
        it.next();
    }

    private void addInst_store_(ListIterator<ASMInstruction>it, String name, Immediate offset){
        assert offset.value >= 0;
        if(offset.value < 2048){
            ASMInstruction addInst =
                    new Store_Inst(null,"sw").addBaseReg(new PhysicalRegister(name),new PhysicalRegister("s0",offset.negative()));
            it.add(addInst);
        }else {
            it.add(new Li_Inst(null).addBaseReg(new PhysicalRegister("t3"),offset.negative()));
            it.add(new Arithmetic_Inst(null,"add").addBaseReg(new PhysicalRegister("t3"),new PhysicalRegister("s0"),new PhysicalRegister("t3")));
            it.add(new Store_Inst(null,"sw").addBaseReg(new PhysicalRegister(name),new PhysicalRegister("t3")));
        }
    }

    public void regAlloc(ASMFunction function){
        function.blockList.forEach(block -> {
            ListIterator<ASMInstruction> it = block.InstList.listIterator();
            while (it.hasNext()){
                ASMInstruction nextInst = it.next();
                if(nextInst.rs1 instanceof VirtualRegister){
                    VirtualRegister v_reg = (VirtualRegister) nextInst.rs1;
                    if(v_reg.colour != -1){
                        nextInst.rs1 = new PhysicalRegister(v_reg);
                    }else {
                        Integer offset_val = reg_name_order_table.get(v_reg.GetName());
                        assert offset_val != null;
                        Immediate offset = new Immediate(offset_val);
                        nextInst.rs1 = new PhysicalRegister(6,v_reg);
                        addInst_load_(it,"t1",offset);
                    }
                }
                if(nextInst.rs2 instanceof VirtualRegister){
                    VirtualRegister v_reg = (VirtualRegister) nextInst.rs2;
                    if(v_reg.colour != -1){
                        nextInst.rs2 = new PhysicalRegister(v_reg);
                    }else {
                        Integer offset_val = reg_name_order_table.get(v_reg.GetName());
                        assert offset_val != null;
                        Immediate offset = new Immediate(offset_val);
                        nextInst.rs2 = new PhysicalRegister(7,v_reg);
                        addInst_load_(it,"t2",offset);
                    }
                }
                if(nextInst.rd instanceof VirtualRegister){
                    VirtualRegister v_reg = (VirtualRegister) nextInst.rd;
                    if(v_reg.colour != -1){
                        nextInst.rd = new PhysicalRegister(v_reg);
                    }else {
                        Integer offset_val = reg_name_order_table.get(v_reg.GetName());
                        assert offset_val != null;
                        Immediate offset = new Immediate(offset_val);
                        nextInst.rd = new PhysicalRegister(5,v_reg);
                        addInst_store_(it,"t0",offset);
                    }
                }
            }
        });
    }

    private ASMBasicBlock entryBlock(ASMFunction function){
        return function.blockList.get(0);
    }

    private ASMBasicBlock exitBlock(ASMFunction function){
        if(function.blockList.size() == 1)return function.blockList.get(0);
        else return function.blockList.get(1);
    }

    public void run(){
        base_module.functionList.forEach(function -> {
            if(function.isBuiltin)return;
            reg_name_order_table.clear();
            function.blockList.forEach(block -> {
                block.InstList.forEach(inst ->{
                    if(inst.rd instanceof VirtualRegister){
                        VirtualRegister v_reg = (VirtualRegister) inst.rd;
                        if(v_reg.colour == -1){
                            if(!reg_name_order_table.containsKey(v_reg.GetName())){
                                function.stack_alloc_update();
                                reg_name_order_table.put(v_reg.GetName(), function.stack_alloc_immediate().value);
                            }
                        }
                    }
                    if(inst.rs1 instanceof VirtualRegister){
                        VirtualRegister v_reg = (VirtualRegister) inst.rs1;
                        if(v_reg.colour == -1){
                            if(!reg_name_order_table.containsKey(v_reg.GetName())){
                                function.stack_alloc_update();
                                reg_name_order_table.put(v_reg.GetName(), function.stack_alloc_immediate().value);
                            }
                        }
                    }
                    if(inst.rs2 instanceof VirtualRegister){
                        VirtualRegister v_reg = (VirtualRegister) inst.rs2;
                        if(v_reg.colour == -1){
                            if(!reg_name_order_table.containsKey(v_reg.GetName())){
                                function.stack_alloc_update();
                                reg_name_order_table.put(v_reg.GetName(), function.stack_alloc_immediate().value);
                            }
                        }
                    }
                });
            });

            int offset = function.stack_offset;
            if(offset < 2048){
                ASMInstruction newInst =
                        new Arithmetic_Inst(null,"add").addBaseReg(new PhysicalRegister("sp"),new PhysicalRegister("sp"),new Immediate(-offset));
                entryBlock(function).InstList.addFirst(newInst);
                newInst = new Arithmetic_Inst(null,"add").addBaseReg(new PhysicalRegister("sp"),new PhysicalRegister("sp"),new Immediate(offset));
                exitBlock(function).InstList.addLast(newInst);
                ListIterator<ASMInstruction> it = entryBlock(function).InstList.listIterator();
                it.next();
                it.next();
                it.add(new Arithmetic_Inst(null,"add").addBaseReg(new PhysicalRegister("s0"),new  PhysicalRegister("sp"),new Immediate(offset)));
            }else {
                entryBlock(function).InstList.addFirst(new Arithmetic_Inst(null,"add").addBaseReg(new PhysicalRegister("sp"),new PhysicalRegister("sp"),new PhysicalRegister("t4")));
                entryBlock(function).InstList.addFirst(new Li_Inst(null).addBaseReg(new PhysicalRegister("t4"),new Immediate(-offset)));
                exitBlock(function).InstList.addLast(new Li_Inst(null).addBaseReg(new PhysicalRegister("t4"),new Immediate(offset)));
                exitBlock(function).InstList.addLast(new Arithmetic_Inst(null,"add").addBaseReg(new PhysicalRegister("sp"),new PhysicalRegister("sp"),new PhysicalRegister("t4")));
                ListIterator<ASMInstruction> it = entryBlock(function).InstList.listIterator();
                it.next();
                it.next();
                it.next();
                it.add(new Arithmetic_Inst(null,"sub").addBaseReg(new PhysicalRegister("s0"),new PhysicalRegister("sp"),new PhysicalRegister("t4")));
            }
            exitBlock(function).InstList.addLast(new Ret_Inst(null));
            regAlloc(function);
        });
    }
}
