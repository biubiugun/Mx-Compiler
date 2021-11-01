package AST.DefNode;

import AST.ASTVisitor;
import Util.position;
import java.util.ArrayList;


public class ClassDefNode extends DefNode{
    public String class_name;
    public ArrayList<varDefNode> member;
    public ArrayList<FuncDefNode> memberFunc;


    public ClassDefNode(position _pos,String _class_name,ArrayList<varDefNode>_member,ArrayList<FuncDefNode> _memberFunc){
        super(_pos);
        if(_member != null)member = _member;
        if(_memberFunc != null)memberFunc = _memberFunc;
        class_name = _class_name;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
