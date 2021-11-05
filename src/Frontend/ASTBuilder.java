package Frontend;

import AST.*;
import AST.RootNode.ProgramSectionNode;
import AST.RootNode.RootNode;
import AST.DefNode.*;
import AST.ExprNode.*;
import AST.StmtNode.*;
import AST.TypeNode.ConstNode;
import AST.TypeNode.TypeNode;
import Parser.MxstarBaseVisitor;
import Parser.MxstarParser;
import Util.error.SyntaxError;
import Util.position;

import java.util.ArrayList;
import java.util.LinkedList;

public class ASTBuilder extends MxstarBaseVisitor<ASTNode>{

    @Override
    public ASTNode visitProgram(MxstarParser.ProgramContext ctx){
        RootNode root = new RootNode(new position(ctx.getStart()));
        root.NodeList = new ArrayList<>();
        for(var it : ctx.programSection()){
            root.NodeList.add(visit(it));
        }
        return root;
    }

    @Override
    public ASTNode visitProgramSection(MxstarParser.ProgramSectionContext ctx){
        if(ctx.classDef() != null){
            return (ClassDefNode)visit(ctx.classDef());
        }else if(ctx.functionDef() != null){
            return (FuncDefNode)visit(ctx.functionDef());
        }else{
            return (varDefStmtNode)visit(ctx.declarationStmt());
        }
    }

    @Override
    public ASTNode visitClassDef(MxstarParser.ClassDefContext ctx){
        String class_name = ctx.Identifier().getText();
        ArrayList<varDefNode> varMembers = new ArrayList<varDefNode>();
        ArrayList<FuncDefNode> funMembers = new ArrayList<FuncDefNode>();
        if(ctx.varDef() != null){
            for(var it : ctx.varDef()){
                varMembers.add((varDefNode) visit(it));
            }
        }
        if(ctx.functionDef() != null){
            for(var it : ctx.functionDef()){
                funMembers.add((FuncDefNode) visit(it));
            }
        }
        return new ClassDefNode(new position(ctx.getStart()),class_name,varMembers,funMembers);
    }

    @Override
    public ASTNode visitVarDef(MxstarParser.VarDefContext ctx){
        TypeNode varType = (TypeNode) visit(ctx.varType());
        ArrayList<varDeclarationNode> varList = new ArrayList<varDeclarationNode>();
        for(var it : ctx.varDeclaration()){
            if(it.expression() != null)
                varList.add(new varDeclarationNode(new position(it.getStart()),it.Identifier().getText(),(ExprNode)visit(it.expression()),varType));
            else varList.add(new varDeclarationNode(new position(it.getStart()),it.Identifier().getText(),null,varType));
        }
        return new varDefNode(new position(ctx.getStart()),varList);
    }

    @Override
    public ASTNode visitVarType(MxstarParser.VarTypeContext ctx){
        if(ctx.arrayType() != null){
            return (TypeNode) visit(ctx.arrayType());
        }else{
            return new TypeNode(new position(ctx.getStart()),ctx.getText());
        }
    }

    @Override
    public ASTNode visitArrayType(MxstarParser.ArrayTypeContext ctx){
        int dim = 0;
        for(var it : ctx.children){
            if(it.getText().equals("["))dim++;
        }
        TypeNode arrayType = new TypeNode(new position(ctx.getStart()),ctx.getText());
        arrayType.dim = dim;
        return arrayType;
    }

    @Override
    public ASTNode visitFunctionDef(MxstarParser.FunctionDefContext ctx){
        ArrayList<varDeclarationNode> paraList;
        BlockStmtNode suite;
        suite = (BlockStmtNode) visit(ctx.suite());
        TypeNode type;
        if(ctx.returnType() != null){
            type = (TypeNode) visit(ctx.returnType());
        }else{
            type = new TypeNode(new position(ctx.getStart()),ctx.Identifier().getText());
        }
        if(ctx.functionParameterDef() != null){
            paraList = new ArrayList<varDeclarationNode>();
            for(int i = 0; i < ctx.functionParameterDef().Identifier().size(); ++i){
                paraList.add(new varDeclarationNode(new position(ctx.functionParameterDef().varType(i).getStart()),ctx.functionParameterDef().Identifier(i).getText(),null,(TypeNode) visit(ctx.functionParameterDef().varType(i))));
            }

            return new FuncDefNode(new position(ctx.getStart()),type,ctx.Identifier().getText(),paraList,suite);
        }
        else{
            return new FuncDefNode(new position(ctx.getStart()),type,ctx.Identifier().getText(),null,suite);
        }
    }

    @Override
    public ASTNode visitBlock(MxstarParser.BlockContext ctx){
        return visit(ctx.suite());
    }

    @Override
    public ASTNode visitSuite(MxstarParser.SuiteContext ctx){
        LinkedList<StmtNode>stmts = new LinkedList<StmtNode>();
        if(ctx.statement() != null){
            for(var it : ctx.statement()){
                stmts.add((StmtNode) visit(it));
            }
        }
        return new BlockStmtNode(stmts,new position(ctx.getStart()));
    }

    @Override
    public ASTNode visitVarDefStmt(MxstarParser.VarDefStmtContext ctx){
        return (varDefStmtNode)visit(ctx.declarationStmt().varDef());
    }

    @Override
    public ASTNode visitIfStmt(MxstarParser.IfStmtContext ctx){
        ExprNode condition;
        StmtNode thenStmt,elseStmt;
        condition = (ExprNode) visit(ctx.expression());
        thenStmt = (StmtNode) visit(ctx.trueStmt);
        if(ctx.falseStmt != null) {
            elseStmt = (StmtNode) visit(ctx.falseStmt);
            return new ifStmtNode(new position(ctx.getStart()), thenStmt, elseStmt, condition);
        }else return new ifStmtNode(new position(ctx.getStart()), thenStmt, null, condition);
    }

    @Override
    public ASTNode visitReturnStmt(MxstarParser.ReturnStmtContext ctx){
        ExprNode returnContext;
        if(ctx.expression() != null){
            returnContext = (ExprNode) visit(ctx.expression());
            return new ReturnStmtNode(new position(ctx.getStart()),returnContext);
        }else return new ReturnStmtNode(new position(ctx.getStart()),null);
    }

    @Override
    public ASTNode visitWhileStmt(MxstarParser.WhileStmtContext ctx){
        return new WhileStmtNode(new position(ctx.getStart()),(ExprNode) visit(ctx.expression()),(StmtNode) visit(ctx.statement()));
    }

    @Override
    public ASTNode visitForStmt(MxstarParser.ForStmtContext ctx){
        ExprNode cond,iter;
        StmtNode thenStmt = (StmtNode) visit(ctx.statement());
        if(ctx.init() == null){
            if(ctx.cond() != null){
                cond = (ExprNode) visit(ctx.cond().expression());
                if(ctx.init() != null) {
                    iter = (ExprNode) visit(ctx.iter().expression());
                    return new ForStmtNode(new position(ctx.getStart()), (ExprNode) null,cond,iter,thenStmt);
                }
                else return new ForStmtNode(new position(ctx.getStart()), (ExprNode) null,cond,null,thenStmt);
            }else return new ForStmtNode(new position(ctx.getStart()), (ExprNode) null,null,null,thenStmt);
        }
        if(ctx.init().varType() != null){
            varDeclarationNode init;
            if(ctx.init().varDeclaration().expression() != null) {
                init = new varDeclarationNode(new position(ctx.init().getStart()), ctx.init().varDeclaration().Identifier().getText(), (ExprNode) visit(ctx.init().varDeclaration().expression()), (TypeNode) visit(ctx.init().varType()));
            }
            else init = new varDeclarationNode(new position(ctx.init().getStart()),ctx.init().varDeclaration().Identifier().getText(),null,(TypeNode) visit(ctx.init().varType()));
            if(ctx.cond() != null){
                cond = (ExprNode) visit(ctx.cond().expression());
                if(ctx.init() != null) {
                    iter = (ExprNode) visit(ctx.iter().expression());
                    return new ForStmtNode(new position(ctx.getStart()),init,cond,iter,thenStmt);
                }
                else return new ForStmtNode(new position(ctx.getStart()), init,cond,null,thenStmt);
            }else return new ForStmtNode(new position(ctx.getStart()), init,null,null,thenStmt);
        }else{
            ExprNode init = (ExprNode) visit(ctx.init().expression());
            if(ctx.cond() != null){
                cond = (ExprNode) visit(ctx.cond().expression());
                if(ctx.init() != null) {
                    iter = (ExprNode) visit(ctx.iter().expression());
                    return new ForStmtNode(new position(ctx.getStart()), init,cond,iter,thenStmt);
                }
                else return new ForStmtNode(new position(ctx.getStart()), init,cond,null,thenStmt);
            }else return new ForStmtNode(new position(ctx.getStart()), init,null,null,thenStmt);
        }
    }

    @Override
    public ASTNode visitContinueStmt(MxstarParser.ContinueStmtContext ctx){
        return new ContinueStmtNode(new position(ctx.getStart()));
    }

    @Override
    public ASTNode visitBreakStmt(MxstarParser.BreakStmtContext ctx){
        return new BreakStmtNode(new position(ctx.getStart()));
    }

    @Override
    public ASTNode visitPureExprStmt(MxstarParser.PureExprStmtContext ctx){
        return new pureExprStmtNode(new position(ctx.getStart()),(ExprNode) visit(ctx.expression()));
    }

    @Override
    public ASTNode visitAtomExpr(MxstarParser.AtomExprContext ctx){
        if(ctx.primary().expression() != null){
            return (ExprNode) visit(ctx.primary().expression());
        }
        AtomExprNode context = new AtomExprNode(new position(ctx.getStart()),ctx.getText(),new ConstNode(new position(ctx.getStart()),ctx.getText()));
        if(ctx.primary().literal() != null){
            if(ctx.primary().literal().DecimalInteger() != null)context.constNode.type = ConstNode.constType.DecimalInteger;
            else if(ctx.primary().literal().True() != null)context.constNode.type = ConstNode.constType.True;
            else if(ctx.primary().literal().False() != null)context.constNode.type = ConstNode.constType.False;
            else context.constNode.type = ConstNode.constType.STRING;
        }else{
            if(ctx.primary().Identifier() != null)context.constNode.type = ConstNode.constType.Identifier;
            else if(ctx.primary().This() != null)context.constNode.type = ConstNode.constType.This;
            else context.constNode.type = ConstNode.constType.NULL;
        }
        return context;
    }

    @Override
    public ASTNode visitMemberExpr(MxstarParser.MemberExprContext ctx){
        if(ctx.LeftParen() != null){
            return new MemberExprNode(new position(ctx.getStart()),ctx.getText(),ctx.Identifier().getText(),(ExprNode) visit(ctx.expression()));
        }else{
            ArrayList<ExprNode> exprList = new ArrayList<ExprNode>();
            if(ctx.expressionList() != null){
                for (var it : ctx.expressionList().expression()) {
                    exprList.add((ExprNode) visit(it));
                }
            }else exprList = null;
            return new MemberFuncExprNode(new position(ctx.getStart()),ctx.getText(),(ExprNode) visit(ctx.expression()),ctx.Identifier().getText(),exprList);
        }
    }

    @Override
    public ASTNode visitCreateExpr(MxstarParser.CreateExprContext ctx){
        return visit(ctx.creator());
    }

    @Override
    public ASTNode visitArrayCreator(MxstarParser.ArrayCreatorContext ctx){
        ArrayList<ExprNode> exprList = new ArrayList<ExprNode>();
        for(var it : ctx.expression()){
            exprList.add((ExprNode) visit(it));
        }
        return new CreateExprNode(new position(ctx.getStart()),"New " + ctx.getText(),(TypeNode) visit(ctx.returnType()),exprList,exprList.size());
    }

    @Override
    public ASTNode visitObjectCreator(MxstarParser.ObjectCreatorContext ctx){
        return new CreateExprNode(new position(ctx.getStart()),ctx.getText(),(TypeNode) visit(ctx.returnType()),null,0);
    }

    @Override
    public ASTNode visitIndexExpr(MxstarParser.IndexExprContext ctx){
        return new IndexExprNode(new position(ctx.getStart()),ctx.getText(),(ExprNode) visit(ctx.expression(0)),(ExprNode) visit(ctx.expression(1)));
    }

    @Override
    public ASTNode visitFunctionExpr(MxstarParser.FunctionExprContext ctx){
        if(ctx.expressionList() == null)return new FunctionExprNode(new position(ctx.getStart()),ctx.getText(),(ExprNode) visit(ctx.expression()),null);
        else {
            ArrayList<ExprNode> exprList = new ArrayList<>();
            for(var it : ctx.expressionList().expression()){
                exprList.add((ExprNode) visit(it));
            }
            return new FunctionExprNode(new position(ctx.getStart()),ctx.getText(),(ExprNode) visit(ctx.expression()),exprList);
        }
    }

    @Override
    public ASTNode visitLambdaExpr(MxstarParser.LambdaExprContext ctx){
        ArrayList<varDeclarationNode>varList = new ArrayList<>();
        if(ctx.functionParameterDef() != null){
            for (int i = 0; i < ctx.functionParameterDef().varType().size(); ++i) {
                varList.add(new varDeclarationNode(new position(ctx.functionParameterDef().varType(i).getStart()), ctx.functionParameterDef().Identifier(i).getText(), null, (TypeNode) visit(ctx.functionParameterDef().varType(i))));
            }
        }else varList = null;
        ArrayList<ExprNode> exprList = new ArrayList<>();
        if(ctx.expressionList() != null){
            for (var it : ctx.expressionList().expression()) {
                exprList.add((ExprNode) visit(it));
            }
        }else exprList = null;
        if(!(ctx.statement() instanceof MxstarParser.BlockContext))throw new SyntaxError("wrong lambda expression!",new position(ctx.getStart()));
        return new LambdaExprNode(new position(ctx.getStart()),ctx.getText(),varList,exprList,(StmtNode) visit(ctx.statement()));
    }

    @Override
    public ASTNode visitSuffixExpr(MxstarParser.SuffixExprContext ctx){
        SuffixExprNode.s_op _op = SuffixExprNode.s_op.ERROR;
        switch (ctx.suffix.getText()) {
            case "++" -> {
                _op = SuffixExprNode.s_op.SelfPlus;
            }
            case "--" -> {
                _op = SuffixExprNode.s_op.SelfMinus;
            }
            default -> throw new RuntimeException("[error]suffix expression's op missed!");
        }
        return new SuffixExprNode(new position(ctx.getStart()),ctx.getText(),_op,(ExprNode) visit(ctx.expression()));
    }

    @Override
    public ASTNode visitPrefixExpr(MxstarParser.PrefixExprContext ctx){
        PrefixExprNode.p_op _op = PrefixExprNode.p_op.ERROR;
        switch (ctx.prefix.getText()){
            case "++" -> _op = PrefixExprNode.p_op.SelfPlus;
            case "--" -> _op = PrefixExprNode.p_op.SelfMinus;
            case "!" -> _op = PrefixExprNode.p_op.Not;
            case "~" -> _op = PrefixExprNode.p_op.Tilde;
            case "-" -> _op = PrefixExprNode.p_op.Minus;
            case "+" -> _op = PrefixExprNode.p_op.Plus;
            default -> throw new RuntimeException("[error]prefix expression's op missed!");
        }
        return new PrefixExprNode(new position(ctx.getStart()),ctx.getText(),_op,(ExprNode) visit(ctx.expression()));
    }

    @Override
    public ASTNode visitBinaryExpr(MxstarParser.BinaryExprContext ctx){
        BinaryExprNode.op _op = BinaryExprNode.op.ERROR;
        switch (ctx.op.getText()){
            case "*" -> _op = BinaryExprNode.op.Mul;
            case "/" -> _op = BinaryExprNode.op.Div;
            case "%" -> _op = BinaryExprNode.op.Mod;
            case "+" -> _op = BinaryExprNode.op.Plus;
            case "-" -> _op = BinaryExprNode.op.Minus;
            case "<<" -> _op = BinaryExprNode.op.LeftShift;
            case ">>" -> _op = BinaryExprNode.op.RightShift;
            case "<" -> _op = BinaryExprNode.op.Less;
            case "<=" -> _op = BinaryExprNode.op.LessEqual;
            case ">" -> _op = BinaryExprNode.op.Greater;
            case ">=" -> _op = BinaryExprNode.op.GreaterEqual;
            case "==" -> _op = BinaryExprNode.op.Equal;
            case "!=" -> _op = BinaryExprNode.op.NotEqual;
            case "&" -> _op = BinaryExprNode.op.And;
            case "^" -> _op = BinaryExprNode.op.Caret;
            case "|" -> _op = BinaryExprNode.op.Or;
            case "&&" -> _op = BinaryExprNode.op.AndAnd;
            case "||" -> _op = BinaryExprNode.op.OrOr;
            default -> {throw new RuntimeException("[error]binary expression's op missed!");}
        }
        return new BinaryExprNode(new position(ctx.getStart()),ctx.getText(),(ExprNode) visit(ctx.expression(0)),_op,(ExprNode) visit(ctx.expression(1)));
    }

    @Override
    public ASTNode visitAssignExpr(MxstarParser.AssignExprContext ctx){
        return new AssignExprNode(new position(ctx.getStart()),ctx.getText(),(ExprNode) visit(ctx.expression(0)),(ExprNode) visit(ctx.expression(1)));
    }

}
