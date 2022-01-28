package IR.Instruction;

import IR.IRBasicBlock;
import IR.IRVisitor;
import IR.Operand.Value;
import IR.TypeSystem.IRType;

public class InstructionGetelementptr extends Instruction{
    // 0 for basePointer ; 1 -> n for index
    public InstructionGetelementptr(IRType targetType, Value calculatedPointer, IRBasicBlock _block) {
        super( _block,"_getelementptr", targetType);
        this.addOperand(calculatedPointer);
    }

    public InstructionGetelementptr addIndex(Value _value){
        this.addOperand(_value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(this.GetName()).append(" = getelementptr inbounds ").append(getOperand(0).type.dePointed().toString())
                .append(", ").append(getOperand(0).printValueString());
        assert this.operandList.size() > 1;
        for(int i = 1;i < operandList.size(); ++i) {
            str.append(", ").append(getOperand(i).printValueString());
        }
        return str.toString();
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

}
