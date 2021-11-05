// Generated from /Users/libohan/Desktop/Amy/compiler/src/Parser/Mxstar.g4 by ANTLR 4.9.1
package Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxstarParser}.
 */
public interface MxstarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxstarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxstarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxstarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#programSection}.
	 * @param ctx the parse tree
	 */
	void enterProgramSection(MxstarParser.ProgramSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#programSection}.
	 * @param ctx the parse tree
	 */
	void exitProgramSection(MxstarParser.ProgramSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#classDef}.
	 * @param ctx the parse tree
	 */
	void enterClassDef(MxstarParser.ClassDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#classDef}.
	 * @param ctx the parse tree
	 */
	void exitClassDef(MxstarParser.ClassDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#functionDef}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDef(MxstarParser.FunctionDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#functionDef}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDef(MxstarParser.FunctionDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#functionParameterDef}.
	 * @param ctx the parse tree
	 */
	void enterFunctionParameterDef(MxstarParser.FunctionParameterDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#functionParameterDef}.
	 * @param ctx the parse tree
	 */
	void exitFunctionParameterDef(MxstarParser.FunctionParameterDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(MxstarParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(MxstarParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#suite}.
	 * @param ctx the parse tree
	 */
	void enterSuite(MxstarParser.SuiteContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#suite}.
	 * @param ctx the parse tree
	 */
	void exitSuite(MxstarParser.SuiteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code block}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MxstarParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code block}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MxstarParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDefStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVarDefStmt(MxstarParser.VarDefStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDefStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVarDefStmt(MxstarParser.VarDefStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(MxstarParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(MxstarParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(MxstarParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(MxstarParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(MxstarParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(MxstarParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(MxstarParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(MxstarParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(MxstarParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(MxstarParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(MxstarParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(MxstarParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pureExprStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterPureExprStmt(MxstarParser.PureExprStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pureExprStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitPureExprStmt(MxstarParser.PureExprStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStmt(MxstarParser.EmptyStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStmt(MxstarParser.EmptyStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayCreator}
	 * labeled alternative in {@link MxstarParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterArrayCreator(MxstarParser.ArrayCreatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayCreator}
	 * labeled alternative in {@link MxstarParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitArrayCreator(MxstarParser.ArrayCreatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectCreator}
	 * labeled alternative in {@link MxstarParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterObjectCreator(MxstarParser.ObjectCreatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectCreator}
	 * labeled alternative in {@link MxstarParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitObjectCreator(MxstarParser.ObjectCreatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code indexExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIndexExpr(MxstarParser.IndexExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code indexExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIndexExpr(MxstarParser.IndexExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code prefixExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrefixExpr(MxstarParser.PrefixExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code prefixExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrefixExpr(MxstarParser.PrefixExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code createExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCreateExpr(MxstarParser.CreateExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code createExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCreateExpr(MxstarParser.CreateExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lambdaExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLambdaExpr(MxstarParser.LambdaExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lambdaExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLambdaExpr(MxstarParser.LambdaExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMemberExpr(MxstarParser.MemberExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMemberExpr(MxstarParser.MemberExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code suffixExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSuffixExpr(MxstarParser.SuffixExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code suffixExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSuffixExpr(MxstarParser.SuffixExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAtomExpr(MxstarParser.AtomExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAtomExpr(MxstarParser.AtomExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpr(MxstarParser.BinaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpr(MxstarParser.BinaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpr(MxstarParser.AssignExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpr(MxstarParser.AssignExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionExpr(MxstarParser.FunctionExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionExpr(MxstarParser.FunctionExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(MxstarParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(MxstarParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(MxstarParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(MxstarParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnType(MxstarParser.ReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnType(MxstarParser.ReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#varType}.
	 * @param ctx the parse tree
	 */
	void enterVarType(MxstarParser.VarTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#varType}.
	 * @param ctx the parse tree
	 */
	void exitVarType(MxstarParser.VarTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(MxstarParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(MxstarParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(MxstarParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(MxstarParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(MxstarParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(MxstarParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#init}.
	 * @param ctx the parse tree
	 */
	void enterInit(MxstarParser.InitContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#init}.
	 * @param ctx the parse tree
	 */
	void exitInit(MxstarParser.InitContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond(MxstarParser.CondContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond(MxstarParser.CondContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#iter}.
	 * @param ctx the parse tree
	 */
	void enterIter(MxstarParser.IterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#iter}.
	 * @param ctx the parse tree
	 */
	void exitIter(MxstarParser.IterContext ctx);
}