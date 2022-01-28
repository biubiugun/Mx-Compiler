package IR.Instruction;

import IR.IRBasicBlock;
import IR.IRVisitor;
import IR.Operand.Operand;
import IR.Operand.Value;
import IR.TypeSystem.IRType;

public class InstructionBitcast extends Instruction{

    public InstructionBitcast(Value _operand, IRType _cast_type,IRBasicBlock _block){
        super(_block,_operand.name + "_bitcast",_cast_type);
        addOperand(_operand);
    }

    public Value getCastOperand(){return getOperand(0);}

    @Override
    public  String toString(){
        return GetName() + " = bitcast " + getCastOperand().printValueString() + " to " + type.toString();
    }

    @Override
    public void accept(IRVisitor _visitor){_visitor.visit(this);}
}
