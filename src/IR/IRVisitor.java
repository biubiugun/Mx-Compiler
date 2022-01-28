package IR;

import IR.Instruction.*;
import IR.Operand.BoolConst;
import IR.Operand.IntegerConst;
import IR.Operand.NullConst;
import IR.Operand.StringConst;

public interface IRVisitor {

    void visit(BoolConst it);

    void visit(IntegerConst it);

    void visit(NullConst it);

    void visit(StringConst it);

    void visit(IRFunction it);

    void visit(IRBasicBlock it);

    void visit(InstructionAlloc it);

    void visit(InstructionBinary it);

    void visit(InstructionBitcast it);

    void visit(InstructionBr it);

    void visit(InstructionCall it);

    void visit(InstructionGetelementptr it);

    void visit(InstructionIcmp it);

    void visit(InstructionLoad it);

    void visit(InstructionRet it);

    void visit(InstructionStore it);
}
