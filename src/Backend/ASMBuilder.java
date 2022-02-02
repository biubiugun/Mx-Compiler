package Backend;

import Backend.Instruction.*;
import Backend.Operand.*;
import Backend.Operand.GlobalVariable;
import Backend.Operand.Register;
import IR.*;
import IR.Instruction.*;
import IR.Operand.*;
import IR.TypeSystem.*;

public class ASMBuilder implements IRVisitor {
    public ASMModule ASM_build_module = new ASMModule();
    public ASMFunction nowFunction;
    public ASMBasicBlock nowBlock;


    private void recursively_visit(Value it){
        if(it instanceof IRBasicBlock){
            if(it.ASMOp == null){
                ((IRBasicBlock)it).accept(this);
            }
        }else {
            assert it instanceof User;
            if(it.ASMOp == null){
                ((User)it).accept(this);
            }
        }
    }

    private void arithmetic_inst_format(Register destReg, BaseReg base1, BaseReg base2, String op){
        if(base1 instanceof Immediate){
            Register immReg = new VirtualRegister(nowFunction.virtual_index++);
            new Li_Inst(nowBlock).addBaseReg(immReg,base1);
            base1 = immReg;
        }
        if(base2 instanceof Immediate){
            boolean is_imm_inst_flag;
            switch (op){
                case "sub","mul","div","rem" -> is_imm_inst_flag = false;
                default -> is_imm_inst_flag = (((((Immediate)base2).value) < 2048 && (((Immediate)base2).value) >= -2048));
            }
            if(is_imm_inst_flag){
                new Arithmetic_Inst(nowBlock,op).addBaseReg(destReg,base1,base2);
            }else {
                Register immReg = new VirtualRegister(nowFunction.virtual_index++);
                new Li_Inst(nowBlock).addBaseReg(immReg,base2);
                new Arithmetic_Inst(nowBlock,op).addBaseReg(destReg,base1,immReg);
            }
        }else {
            new Arithmetic_Inst(nowBlock,op).addBaseReg(destReg,base1,base2);
        }
    }

    @Override
    public void visit(IRModule it) {
        it.stringList.forEach(str -> {
            str.ASMOp = new GlobalVariable(str.name,str.value);
            ASM_build_module.addGlobalVar((GlobalVariable) str.ASMOp);
        });
        it.globalVariableList.forEach(var -> {
            var.ASMOp = new GlobalVariable(var.name,null);
            ASM_build_module.addGlobalVar((GlobalVariable) var.ASMOp);
        });
        it.functionList.forEach(func -> {
            func.ASMOp = new ASMFunction(func.name);
            ((ASMFunction)func.ASMOp).isBuiltin = func.isBuiltin;
            int parameter_number = ((FunctionType)func.type).paraNameList.size();
            for(int i = 0;i <= Math.min(parameter_number - 1,7); ++i){
                ((ASMFunction)func.ASMOp).RegList.add(new VirtualRegister(((ASMFunction)func.ASMOp).virtual_index++,10 + i));
            }
            for(int i = 8;i < parameter_number; ++i){
                ((ASMFunction)func.ASMOp).stack_alloc_update();
                Register reg = new VirtualRegister(((ASMFunction)func.ASMOp).stack_alloc_immediate().negative(),((ASMFunction)func.ASMOp).virtual_index++,8);
                reg.in_memory = true;
                ((ASMFunction)func.ASMOp).RegList.add(reg);
            }
            for(int i = 0;i < func.operandList.size(); ++i){
                func.operandList.get(i).ASMOp = ((ASMFunction)func.ASMOp).RegList.get(i);
            }
            for(int i = 0;i < func.blockList.size(); ++i){
                func.blockList.get(i).ASMOp = new ASMBasicBlock(func.blockList.get(i).name,(ASMFunction) func.ASMOp);
            }
            ASM_build_module.addFunction((ASMFunction) func.ASMOp);
        });
        it.InitList.forEach(init_func -> {
            init_func.ASMOp = new ASMFunction(init_func.name);
            init_func.blockList.forEach(block -> {
                block.ASMOp = new ASMBasicBlock(block.name,(ASMFunction) init_func.ASMOp);
            });
            ASM_build_module.addFunction((ASMFunction) init_func.ASMOp);
            init_func.accept(this);
        });
        IRFunction _GLOBAL_ptr = null;
        if(!it.InitList.isEmpty()){
            for (var i : it.InitList){
                if(i.name.equals("_GLOBAL_"))_GLOBAL_ptr = i;
            }
            assert _GLOBAL_ptr != null;
            for (var i : it.functionList){
                if(i.name.equals("main"))i.blockList.get(0).InstList.addFirst(new InstructionCall(_GLOBAL_ptr,null));
            }
        }
        it.functionList.forEach(func -> func.accept(this));
    }

    @Override
    public void visit(BoolConst it) {
        it.ASMOp = new Immediate(it.value ? 1 : 0);
    }

    @Override
    public void visit(IntegerConst it) {
        it.ASMOp = new Immediate(it.value);
    }

    @Override
    public void visit(NullConst it) {
        it.ASMOp = new Immediate(0);
    }

    @Override
    public void visit(StringConst it) {
        it.ASMOp = new GlobalVariable(it.name,it.value);
    }

    @Override
    public void visit(IRFunction it) {
        if(it.isBuiltin)return;
        nowFunction = (ASMFunction) it.ASMOp;
        nowBlock = ((ASMFunction) it.ASMOp).blockList.get(0);
        Register tmp_reg = new VirtualRegister(nowFunction.virtual_index++);
        new Move_Inst(nowBlock).addBaseReg(new VirtualRegister(nowFunction.virtual_index++,9),new VirtualRegister(nowFunction.virtual_index++,8));
        new Move_Inst(nowBlock).addBaseReg(tmp_reg,new VirtualRegister(nowFunction.virtual_index++,9));
        it.blockList.forEach(block -> block.accept(this));
        if(nowFunction.blockList.size() == 1){
            nowBlock = nowFunction.blockList.get(0);
        }else nowBlock = nowFunction.blockList.get(1);
        new Move_Inst(nowBlock).addBaseReg(new VirtualRegister(nowFunction.virtual_index++,8),tmp_reg);//swap
    }

    @Override
    public void visit(IRBasicBlock it) {
        nowBlock = (ASMBasicBlock) it.ASMOp;
        it.InstList.forEach(inst -> inst.accept(this));
        assert it.terminalInst != null;
        it.terminalInst.accept(this);

    }

    @Override
    public void visit(InstructionAlloc it) {
        nowFunction.stack_alloc_update();
        it.ASMOp = new VirtualRegister(nowFunction.stack_alloc_immediate().negative(), nowFunction.virtual_index++, 8);//s0
    }

    @Override
    public void visit(InstructionBinary it) {
        it.operandList.forEach(this::recursively_visit);
        String riscv_operator;
        switch (it.operation){
            case sdiv -> riscv_operator = "div";
            case srem -> riscv_operator = "rem";
            case shl -> riscv_operator = "sll";
            case ashr -> riscv_operator = "sra";
            default -> riscv_operator = it.operation.toString();
        }
        Register newReg = new VirtualRegister(nowFunction.virtual_index++);
        arithmetic_inst_format(newReg,it.getOp1().ASMOp,it.getOp2().ASMOp,riscv_operator);
        it.ASMOp = newReg;
    }

    @Override
    public void visit(InstructionBitcast it) {
        recursively_visit(it.getCastOperand());
        it.ASMOp = it.getCastOperand().ASMOp;
    }

    @Override
    public void visit(InstructionBr it) {
        it.ASMOp = null;
        it.operandList.forEach(this::recursively_visit);
        if(it.operandList.size() == 1){
            new Jump_Inst(nowBlock).addBaseReg(it.getOperand(0).ASMOp);
        }else {
            //use bne
            BaseReg br_flag = it.getOperand(0).ASMOp;
            if(br_flag instanceof Immediate){
                br_flag = new VirtualRegister(nowFunction.virtual_index++);
                new Li_Inst(nowBlock).addBaseReg(br_flag,br_flag);//maybe bug
            }
            new Branch_Inst(nowBlock,"bne").addBaseReg(it.getOperand(1).ASMOp,br_flag,PhysicalRegister.getPhysicalReg(0));
            new Jump_Inst(nowBlock).addBaseReg(it.getOperand(2).ASMOp);
        }
    }

    @Override
    public void visit(InstructionCall it) {
        ASMFunction called_func = (ASMFunction) it.getOperand(0).ASMOp;
        it.operandList.forEach(this::recursively_visit);
        for(int i = 0;i <= Math.min(7,it.operandList.size() - 2); ++i){
            Register destReg = called_func.RegList.get(i);
            BaseReg source = it.getOperand(i + 1).ASMOp;
            if(source instanceof Immediate){
                new Li_Inst(nowBlock).addBaseReg(destReg,source);
            }else {
                new Move_Inst(nowBlock).addBaseReg(destReg,source);
            }
        }
        for(int i = 8;i < it.operandList.size() - 1; ++i){
            Register arg;
            BaseReg base = it.getOperand(i + 1).ASMOp;
            if(base instanceof Immediate){
                arg = new VirtualRegister(nowFunction.virtual_index);
                new Li_Inst(nowBlock).addBaseReg(arg,base);
            }else {
                assert base instanceof Register;
                arg = (Register) base;
            }
            new Store_Inst(nowBlock,"sw").addBaseReg(arg,new VirtualRegister(called_func.RegList.get(i).offset.negative(), nowFunction.virtual_index++, 2));
        }
        Register tmpReg = new VirtualRegister(nowFunction.virtual_index++);
        new Move_Inst(nowBlock).addBaseReg(tmpReg,new VirtualRegister(nowFunction.virtual_index++,1));
        new Call_Inst(nowBlock).addBaseReg(called_func);
        new Move_Inst(nowBlock).addBaseReg(new VirtualRegister(nowFunction.virtual_index++,1),tmpReg);
        if(!(it.type instanceof VoidType)){
            Register returnValue = new VirtualRegister(nowFunction.virtual_index++);
            new Move_Inst(nowBlock).addBaseReg(returnValue,new VirtualRegister(10, nowFunction.virtual_index++));
            it.ASMOp = returnValue;
        }
    }

    @Override
    public void visit(InstructionGetelementptr it) {
        it.operandList.forEach(this::recursively_visit);
        BaseReg ptr_reg = it.getOperand(0).ASMOp;
        IRType baseType = it.getOperand(0).type.dePointed();
        BaseReg newOperand;
        if(baseType instanceof StructType){
            assert ptr_reg instanceof Register;
            assert it.getOperand(2) instanceof IntegerConst;
            ptr_reg = new VirtualRegister((VirtualRegister) ptr_reg);
            newOperand = ptr_reg;
            ((Register)newOperand).offset
                    = new Immediate(((StructType) baseType).getOffset(((IntegerConst)it.getOperand(2)).value));
        }else if(baseType instanceof ArrayType){
            //string
            assert ptr_reg instanceof GlobalVariable;
            newOperand = new VirtualRegister(nowFunction.virtual_index++);
            new La_Inst(nowBlock).addBaseReg(newOperand,ptr_reg);
        }else {
            //array
            assert ptr_reg instanceof Register;
            ptr_reg = new VirtualRegister((VirtualRegister) ptr_reg);
            Value index = it.getOperand(1);
            if(index instanceof IntegerConst){
                newOperand = new VirtualRegister(nowFunction.virtual_index++);
                Immediate offset = new Immediate(((IntegerConst)index).value * baseType.byteSize());
                arithmetic_inst_format((Register) newOperand,ptr_reg,offset,"add");
            }else {
                assert index.ASMOp instanceof Register;
                newOperand = new VirtualRegister(nowFunction.virtual_index++);
                Register offset = new VirtualRegister(nowFunction.virtual_index++);
                arithmetic_inst_format(offset,index.ASMOp,new Immediate(baseType.byteSize()),"mul");
                arithmetic_inst_format((Register) newOperand,ptr_reg,offset,"add");
                //*
            }
        }
        it.ASMOp = newOperand;
    }

    @Override
    public void visit(InstructionGlobal it) {
        it.ASMOp = new GlobalVariable(it.name,null);
    }

    private void load_immediate_to_reg(BaseReg imm){
        if(imm instanceof Immediate tmp_imm){
            imm = new VirtualRegister(nowFunction.virtual_index++);
            new Li_Inst(nowBlock).addBaseReg(imm,tmp_imm);
        }

    }

    @Override
    public void visit(InstructionIcmp it) {
        Register newOperand = new VirtualRegister(nowFunction.virtual_index++);
        it.operandList.forEach(this::recursively_visit);
        BaseReg rs1 = it.getOperand(0).ASMOp;
        BaseReg rs2 = it.getOperand(1).ASMOp;
        load_immediate_to_reg(rs1);
        load_immediate_to_reg(rs2);
        switch (it.method){
            case eq -> {
                new Arithmetic_Inst(nowBlock,"xor").addBaseReg(newOperand,rs1,rs2);
                new Seqz_Inst(nowBlock).addBaseReg(newOperand,newOperand);
            }
            case ne -> {
                new Arithmetic_Inst(nowBlock,"xor").addBaseReg(newOperand,rs1,rs2);
                new Snez_Inst(nowBlock).addBaseReg(newOperand,newOperand);
            }
            case sgt -> {
                new Arithmetic_Inst(nowBlock,"slt").addBaseReg(newOperand,rs2,rs1);
            }
            //need SSA
            case sge -> {
                new Arithmetic_Inst(nowBlock,"slt").addBaseReg(newOperand,rs1,rs2);
                new Arithmetic_Inst(nowBlock,"xor").addBaseReg(newOperand,newOperand,new Immediate(1));
            }
            case slt -> {
                new Arithmetic_Inst(nowBlock,"slt").addBaseReg(newOperand,rs1,rs2);
            }
            case sle -> {
                new Arithmetic_Inst(nowBlock,"slt").addBaseReg(newOperand,rs2,rs1);
                new Arithmetic_Inst(nowBlock,"xor").addBaseReg(newOperand,newOperand,new Immediate(1));
            }
            default -> throw new RuntimeException("unexpected operator in Icmp");
        }
        it.ASMOp = newOperand;
    }

    @Override
    public void visit(InstructionLoad it) {
        Register newOp = new VirtualRegister(nowFunction.virtual_index++);
        recursively_visit(it.operandList.get(0));
        BaseReg ptr_reg = it.getOperand(0).ASMOp;
        if(ptr_reg instanceof GlobalVariable){
            Register address = new VirtualRegister(nowFunction.virtual_index++);
            new La_Inst(nowBlock).addBaseReg(address,ptr_reg);
            address.offset = new Immediate(0);
            new Load_Inst(nowBlock,"lw").addBaseReg(newOp,address);
        }else {
            assert ptr_reg instanceof Register;
            new Load_Inst(nowBlock,"lw").addBaseReg(newOp,ptr_reg);
        }
        it.ASMOp = newOp;
    }

    @Override
    public void visit(InstructionRet it) {
        if(it.type instanceof VoidType)return;
        recursively_visit(it.getOperand(0));
        BaseReg returnValue = it.getOperand(0).ASMOp;
        assert returnValue instanceof Register;
        new Move_Inst(nowBlock).addBaseReg(new VirtualRegister(nowFunction.virtual_index++),returnValue);
        it.ASMOp = null;
    }

    @Override
    public void visit(InstructionStore it) {
        for(int i = 0;i < 2; ++i){
            recursively_visit(it.getOperand(i));
        }
        BaseReg val = it.getOperand(0).ASMOp;
        BaseReg ptr = it.getOperand(1).ASMOp;
        BaseReg value = val;
        if(val instanceof Register){
            if(((Register)val).in_memory){
                Register tmp_reg = new VirtualRegister(nowFunction.virtual_index++);
                new Load_Inst(nowBlock,"lw").addBaseReg(tmp_reg,val);
                value = tmp_reg;
            }
        }else if(val instanceof Immediate){
            value = new VirtualRegister(nowFunction.virtual_index++);
            new Li_Inst(nowBlock).addBaseReg(value,val);
        }else throw new RuntimeException("...a");
        if(ptr instanceof GlobalVariable){
            Register address = new VirtualRegister(nowFunction.virtual_index++);
            new La_Inst(nowBlock).addBaseReg(address,ptr);
            address.offset = new Immediate(0);
            new Store_Inst(nowBlock,"sw").addBaseReg(value,address);
        }else if(ptr instanceof Register){
            new Store_Inst(nowBlock,"sw").addBaseReg(value,ptr);
        }else throw new RuntimeException("...b");
    }

    @Override
    public void visit(InstructionTrunc it) {
        recursively_visit(it.getOperand(0));
        it.ASMOp = it.getOperand(0).ASMOp;
    }

    @Override
    public void visit(InstructionZext it) {
        recursively_visit(it.getOperand(0));
        it.ASMOp = it.getOperand(0).ASMOp;
    }
}
