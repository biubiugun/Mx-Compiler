package Frontend;
import AST.ExprNode.*;
import AST.RootNode.ProgramSectionNode;
import AST.RootNode.RootNode;
import AST.StmtNode.*;
import AST.TypeNode.ConstNode;
import AST.TypeNode.TypeNode;
import Util.*;
import AST.*;
import AST.DefNode.*;
import Util.error.SemanticError;

import java.util.ArrayList;

public class Initiator implements ASTVisitor{
    public GlobalScope initScope;

    public Initiator(GlobalScope _initScope){initScope = _initScope;}

    public GlobalScope initializeBuiltIn(){
        //builtInFunction
        ArrayList<varDeclarationNode>printParaList = new ArrayList<>();
        printParaList.add(new varDeclarationNode(null,"str",null,new TypeNode(null,"string")));
        FuncDefNode printFunc = new FuncDefNode(null,new TypeNode(null,"void"),"print",printParaList,null);
        initScope.setFunc("print",printFunc);

        ArrayList<varDeclarationNode>printlnParaList = new ArrayList<>();
        printlnParaList.add(new varDeclarationNode(null,"str",null,new TypeNode(null,"string")));
        FuncDefNode printInFunc = new FuncDefNode(null,new TypeNode(null,"void"),"printIn",printlnParaList,null);
        initScope.setFunc("println",printInFunc);

        ArrayList<varDeclarationNode>printIntParaList = new ArrayList<>();
        printIntParaList.add(new varDeclarationNode(null,"n",null,new TypeNode(null,"int")));
        FuncDefNode printIntFunc = new FuncDefNode(null,new TypeNode(null,"void"),"printInt",printIntParaList,null);
        initScope.setFunc("printInt",printIntFunc);

        ArrayList<varDeclarationNode>printlnIntParaList = new ArrayList<>();
        printlnIntParaList.add(new varDeclarationNode(null,"n",null,new TypeNode(null,"int")));
        FuncDefNode printlnIntFunc = new FuncDefNode(null,new TypeNode(null,"void"),"printlnInt",printlnIntParaList,null);
        initScope.setFunc("printlnInt",printlnIntFunc);

        FuncDefNode getStringFunc = new FuncDefNode(null,new TypeNode(null,"string"),"getString",null,null);
        initScope.setFunc("getString",getStringFunc);

        FuncDefNode getIntFunc = new FuncDefNode(null,new TypeNode(null,"int"),"getInt",null,null);
        initScope.setFunc("getInt",getIntFunc);

        ArrayList<varDeclarationNode>toStringParaList = new ArrayList<>();
        toStringParaList.add(new varDeclarationNode(null,"i",null,new TypeNode(null,"int")));
        FuncDefNode toStringFunc = new FuncDefNode(null,new TypeNode(null,"string"),"toString",toStringParaList,null);
        initScope.setFunc("toString",toStringFunc);

        //builtInClass
        initScope.setClass("int",new GlobalScope(initScope));
        initScope.setClass("bool",new GlobalScope(initScope));

        GlobalScope stringClassScope = new GlobalScope(initScope);
        ArrayList<varDeclarationNode> subStringPara = new ArrayList<>();
        subStringPara.add(new varDeclarationNode(null,"left",null,new TypeNode(null,"int")));
        subStringPara.add(new varDeclarationNode(null,"right",null,new TypeNode(null,"int")));
        stringClassScope.setFunc("substring",new FuncDefNode(null,new TypeNode(null,"string"),"substring",subStringPara,null));
        stringClassScope.setFunc("length",new FuncDefNode(null,new TypeNode(null,"int"),"length",null,null));
        stringClassScope.setFunc("parseInt",new FuncDefNode(null,new TypeNode(null,"int"),"parseInt",null,null));
        ArrayList<varDeclarationNode> ordPara = new ArrayList<>();
        ordPara.add(new varDeclarationNode(null,"pos",null,new TypeNode(null,"int")));
        stringClassScope.setFunc("ord",new FuncDefNode(null,new TypeNode(null,"int"),"ord",ordPara,null));
        initScope.setClass("string",stringClassScope);

        return initScope;
    }

    @Override
    public void visit(RootNode it) {
        if(it.NodeList != null)it.NodeList.forEach(node->node.accept(this));
        if(!initScope.containsFunc("main"))
            throw new SemanticError("main function lost!",it.pos);
        else {
            if (!initScope.getFunc("main").typename.typename.equals("int"))
                throw new SemanticError("wrong main function type!", it.pos);
            if(initScope.getFunc("main").paraList != null)
                throw new SemanticError("main function shouldn't have parameters!",it.pos);
        }
    }

    @Override
    public void visit(FuncDefNode it) {
        if(initScope.containsFunc(it.func_name))throw new SemanticError("class " + it.func_name + ":duplicate function definition!",it.pos);
        else{
            if(initScope.containsClass(it.func_name))throw new SemanticError("class " + it.func_name + ":duplicate function naming with class!",it.pos);
            else if(it.typename == null) {throw new SemanticError("Construct function without a class!",it.pos);}
            else initScope.setFunc(it.func_name,it);
        }
    }
    @Override
    public void visit(ClassDefNode it) {
        if(initScope.containsClass(it.class_name))throw new SemanticError("class " + it.class_name + ":duplicate class definition!",it.pos);
        else {
            if(initScope.containsFunc(it.class_name))throw new SemanticError("class " + it.class_name + ":duplicate class naming with function!",it.pos);
            else {
                GlobalScope classScope = new GlobalScope(initScope);
                for(var i : it.member){
                    for(var j : i.varList){

                        if(classScope.containsVariable(j.name))throw new SemanticError("class " + it.class_name + ":duplicate class variable name!",it.pos);
                        else classScope.SetVariable(j.name,j.type);
                    }
                }
                for(var i : it.memberFunc){
                    if(i.typename.typename == null && !i.func_name.equals(it.class_name))throw new SemanticError("class " + it.class_name + ":function return failed!",it.pos);
                    if(i.hasReturnStmt && i.func_name.equals(it.class_name))throw new SemanticError("class " + it.class_name + ":construct function shouldn't have return type",it.pos);
                    if(classScope.containsFunc(i.func_name))throw new SemanticError("class " + it.class_name + ":duplicate class function name!",it.pos);
                    else classScope.setFunc(i.func_name,i);
                }
                initScope.setClass(it.class_name,classScope);
            }
        }
    }

    @Override
    public void visit(ProgramSectionNode it) {}
    @Override
    public void visit(BlockStmtNode it) {}
    @Override
    public void visit(ifStmtNode it) {}
    @Override
    public void visit(ReturnStmtNode it) {}
    @Override
    public void visit(WhileStmtNode it) {}
    @Override
    public void visit(varDefNode it) {}
    @Override
    public void visit(ForStmtNode it) {}
    @Override
    public void visit(pureExprStmtNode it) {}
    @Override
    public void visit(varDefStmtNode it) {}
    @Override
    public void visit(ContinueStmtNode it) {}
    @Override
    public void visit(BreakStmtNode it) {}
    @Override
    public void visit(TypeNode it) {}
    @Override
    public void visit(MemberExprNode it) {}
    @Override
    public void visit(MemberFuncExprNode it) {}
    @Override
    public void visit(CreateExprNode it) {}
    @Override
    public void visit(IndexExprNode it) {}
    @Override
    public void visit(FunctionExprNode it) {}
    @Override
    public void visit(LambdaExprNode it) {}
    @Override
    public void visit(SuffixExprNode it) {}
    @Override
    public void visit(PrefixExprNode it) {}
    @Override
    public void visit(AssignExprNode it) {}
    @Override
    public void visit(BinaryExprNode it) {}
    @Override
    public void visit(varDeclarationNode it) {}
    @Override
    public void visit(ConstNode it) {}
    @Override
    public void visit(AtomExprNode it) {}
}
