package IR.Instruction;

import IR.IRBasicBlock;
import IR.IRFunction;
import IR.IRVisitor;
import IR.Operand.Value;
import IR.TypeSystem.FunctionType;
import IR.TypeSystem.VoidType;


public class InstructionCall extends Instruction{
    public InstructionCall(IRFunction _func, IRBasicBlock _block) {
        super(_block,"_call"+_func.name, ((FunctionType)_func.type).returnType);
        this.addOperand(_func);
    }

    // 0：function 1-: arguments
    public InstructionCall addArg(Value _arg){
        this.addOperand(_arg);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if(!(this.type instanceof VoidType)){
            str.append(this.GetName()).append(" = ");
        }
        str.append("call ").append(this.getOperand(0).printValueString()).append('(');
        if(operandList.size() > 1) {
            for (int i = 1; i < operandList.size(); ++i) {
                str.append(this.getOperand(i).printValueString()).append(", ");
            }
            str.delete(str.length()-2,str.length());
        }
        str.append(')');
        return str.toString();
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

}
