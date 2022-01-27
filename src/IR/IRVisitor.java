package IR;

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
}
