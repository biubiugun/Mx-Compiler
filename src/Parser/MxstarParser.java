// Generated from /Users/libohan/Desktop/Amy/compiler/src/Parser/Mxstar.g4 by ANTLR 4.9.1
package Parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxstarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STRING=1, Int=2, Bool=3, Void=4, True=5, False=6, String=7, NULL=8, For=9, 
		While=10, Break=11, Continue=12, New=13, Class=14, This=15, If=16, Else=17, 
		Return=18, Dot=19, LeftParen=20, RightParen=21, LeftBracket=22, RightBracket=23, 
		LeftBrace=24, RightBrace=25, Less=26, LessEqual=27, Greater=28, GreaterEqual=29, 
		LeftShift=30, RightShift=31, Plus=32, SelfPlus=33, Minus=34, SelfMinus=35, 
		Mul=36, Div=37, Mod=38, And=39, Or=40, AndAnd=41, OrOr=42, Caret=43, Not=44, 
		Tilde=45, Question=46, Colon=47, Semi=48, Comma=49, Assign=50, Equal=51, 
		NotEqual=52, BackSlash=53, DbQuotation=54, Identifier=55, DecimalInteger=56, 
		Whitespace=57, Newline=58, BlockComment=59, LineComment=60;
	public static final int
		RULE_program = 0, RULE_functionDef = 1, RULE_functionParameterDef = 2, 
		RULE_expressionList = 3, RULE_suite = 4, RULE_statement = 5, RULE_declarationStmt = 6, 
		RULE_creator = 7, RULE_expression = 8, RULE_varDef = 9, RULE_varDeclaration = 10, 
		RULE_returnType = 11, RULE_varType = 12, RULE_builtinType = 13, RULE_arrayType = 14, 
		RULE_primary = 15, RULE_literal = 16, RULE_init = 17, RULE_cond = 18, 
		RULE_iter = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "functionDef", "functionParameterDef", "expressionList", "suite", 
			"statement", "declarationStmt", "creator", "expression", "varDef", "varDeclaration", 
			"returnType", "varType", "builtinType", "arrayType", "primary", "literal", 
			"init", "cond", "iter"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'int'", "'bool'", "'void'", "'true'", "'false'", "'string'", 
			"'null'", "'for'", "'while'", "'break'", "'continue'", "'new'", "'class'", 
			"'this'", "'if'", "'else'", "'return'", "'.'", "'('", "')'", "'['", "']'", 
			"'{'", "'}'", "'<'", "'<='", "'>'", "'>='", "'<<'", "'>>'", "'+'", "'++'", 
			"'-'", "'--'", "'*'", "'/'", "'%'", "'&'", "'|'", "'&&'", "'||'", "'^'", 
			"'!'", "'~'", "'?'", "':'", "';'", "','", "'='", "'=='", "'!='", "'\\\\'", 
			"'\\\"'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "STRING", "Int", "Bool", "Void", "True", "False", "String", "NULL", 
			"For", "While", "Break", "Continue", "New", "Class", "This", "If", "Else", 
			"Return", "Dot", "LeftParen", "RightParen", "LeftBracket", "RightBracket", 
			"LeftBrace", "RightBrace", "Less", "LessEqual", "Greater", "GreaterEqual", 
			"LeftShift", "RightShift", "Plus", "SelfPlus", "Minus", "SelfMinus", 
			"Mul", "Div", "Mod", "And", "Or", "AndAnd", "OrOr", "Caret", "Not", "Tilde", 
			"Question", "Colon", "Semi", "Comma", "Assign", "Equal", "NotEqual", 
			"BackSlash", "DbQuotation", "Identifier", "DecimalInteger", "Whitespace", 
			"Newline", "BlockComment", "LineComment"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Mxstar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxstarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public List<DeclarationStmtContext> declarationStmt() {
			return getRuleContexts(DeclarationStmtContext.class);
		}
		public DeclarationStmtContext declarationStmt(int i) {
			return getRuleContext(DeclarationStmtContext.class,i);
		}
		public List<FunctionDefContext> functionDef() {
			return getRuleContexts(FunctionDefContext.class);
		}
		public FunctionDefContext functionDef(int i) {
			return getRuleContext(FunctionDefContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << Void) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(42);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(40);
					declarationStmt();
					}
					break;
				case 2:
					{
					setState(41);
					functionDef();
					}
					break;
				}
				}
				setState(46);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDefContext extends ParserRuleContext {
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public TerminalNode LeftParen() { return getToken(MxstarParser.LeftParen, 0); }
		public FunctionParameterDefContext functionParameterDef() {
			return getRuleContext(FunctionParameterDefContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxstarParser.RightParen, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public FunctionDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterFunctionDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitFunctionDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitFunctionDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDefContext functionDef() throws RecognitionException {
		FunctionDefContext _localctx = new FunctionDefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_functionDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			returnType();
			setState(48);
			match(Identifier);
			setState(49);
			match(LeftParen);
			setState(50);
			functionParameterDef();
			setState(51);
			match(RightParen);
			setState(52);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionParameterDefContext extends ParserRuleContext {
		public List<VarTypeContext> varType() {
			return getRuleContexts(VarTypeContext.class);
		}
		public VarTypeContext varType(int i) {
			return getRuleContext(VarTypeContext.class,i);
		}
		public List<TerminalNode> Identifier() { return getTokens(MxstarParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(MxstarParser.Identifier, i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxstarParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxstarParser.Comma, i);
		}
		public FunctionParameterDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionParameterDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterFunctionParameterDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitFunctionParameterDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitFunctionParameterDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionParameterDefContext functionParameterDef() throws RecognitionException {
		FunctionParameterDefContext _localctx = new FunctionParameterDefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_functionParameterDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(54);
				varType();
				setState(55);
				match(Identifier);
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(56);
					match(Comma);
					setState(57);
					varType();
					setState(58);
					match(Identifier);
					}
					}
					setState(64);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxstarParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxstarParser.Comma, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			expression(0);
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(68);
				match(Comma);
				setState(69);
				expression(0);
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuiteContext extends ParserRuleContext {
		public TerminalNode LeftBrace() { return getToken(MxstarParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxstarParser.RightBrace, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public SuiteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterSuite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitSuite(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitSuite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_suite);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(LeftBrace);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << Int) | (1L << Bool) | (1L << True) | (1L << False) | (1L << String) | (1L << NULL) | (1L << For) | (1L << While) | (1L << Break) | (1L << Continue) | (1L << New) | (1L << This) | (1L << If) | (1L << Return) | (1L << LeftParen) | (1L << LeftBracket) | (1L << LeftBrace) | (1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde) | (1L << Semi) | (1L << Identifier) | (1L << DecimalInteger))) != 0)) {
				{
				{
				setState(76);
				statement();
				}
				}
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(82);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VarDefStmtContext extends StatementContext {
		public DeclarationStmtContext declarationStmt() {
			return getRuleContext(DeclarationStmtContext.class,0);
		}
		public VarDefStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterVarDefStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitVarDefStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitVarDefStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForStmtContext extends StatementContext {
		public TerminalNode For() { return getToken(MxstarParser.For, 0); }
		public TerminalNode LeftParen() { return getToken(MxstarParser.LeftParen, 0); }
		public List<TerminalNode> Semi() { return getTokens(MxstarParser.Semi); }
		public TerminalNode Semi(int i) {
			return getToken(MxstarParser.Semi, i);
		}
		public TerminalNode RightParen() { return getToken(MxstarParser.RightParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public InitContext init() {
			return getRuleContext(InitContext.class,0);
		}
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public IterContext iter() {
			return getRuleContext(IterContext.class,0);
		}
		public ForStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitForStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitForStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileStmtContext extends StatementContext {
		public TerminalNode While() { return getToken(MxstarParser.While, 0); }
		public TerminalNode LeftParen() { return getToken(MxstarParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxstarParser.RightParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterWhileStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitWhileStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitWhileStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfStmtContext extends StatementContext {
		public StatementContext trueStmt;
		public StatementContext falseStmt;
		public TerminalNode If() { return getToken(MxstarParser.If, 0); }
		public TerminalNode LeftParen() { return getToken(MxstarParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxstarParser.RightParen, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(MxstarParser.Else, 0); }
		public IfStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitIfStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BreakStmtContext extends StatementContext {
		public TerminalNode Break() { return getToken(MxstarParser.Break, 0); }
		public TerminalNode Semi() { return getToken(MxstarParser.Semi, 0); }
		public BreakStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterBreakStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitBreakStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitBreakStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmptyStmtContext extends StatementContext {
		public TerminalNode Semi() { return getToken(MxstarParser.Semi, 0); }
		public EmptyStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterEmptyStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitEmptyStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitEmptyStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockContext extends StatementContext {
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public BlockContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnStmtContext extends StatementContext {
		public TerminalNode Return() { return getToken(MxstarParser.Return, 0); }
		public TerminalNode Semi() { return getToken(MxstarParser.Semi, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitReturnStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ContinueStmtContext extends StatementContext {
		public TerminalNode Continue() { return getToken(MxstarParser.Continue, 0); }
		public TerminalNode Semi() { return getToken(MxstarParser.Semi, 0); }
		public ContinueStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterContinueStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitContinueStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitContinueStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PureExprStmtContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Semi() { return getToken(MxstarParser.Semi, 0); }
		public PureExprStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterPureExprStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitPureExprStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitPureExprStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_statement);
		int _la;
		try {
			setState(129);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				_localctx = new BlockContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(84);
				suite();
				}
				break;
			case 2:
				_localctx = new VarDefStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				declarationStmt();
				}
				break;
			case 3:
				_localctx = new IfStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(86);
				match(If);
				setState(87);
				match(LeftParen);
				setState(88);
				expression(0);
				setState(89);
				match(RightParen);
				setState(90);
				((IfStmtContext)_localctx).trueStmt = statement();
				setState(93);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(91);
					match(Else);
					setState(92);
					((IfStmtContext)_localctx).falseStmt = statement();
					}
					break;
				}
				}
				break;
			case 4:
				_localctx = new ReturnStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(95);
				match(Return);
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << True) | (1L << False) | (1L << NULL) | (1L << New) | (1L << This) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde) | (1L << Identifier) | (1L << DecimalInteger))) != 0)) {
					{
					setState(96);
					expression(0);
					}
				}

				setState(99);
				match(Semi);
				}
				break;
			case 5:
				_localctx = new WhileStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(100);
				match(While);
				setState(101);
				match(LeftParen);
				setState(102);
				expression(0);
				setState(103);
				match(RightParen);
				setState(104);
				statement();
				}
				break;
			case 6:
				_localctx = new ForStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(106);
				match(For);
				setState(107);
				match(LeftParen);
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << Int) | (1L << Bool) | (1L << Void) | (1L << True) | (1L << False) | (1L << String) | (1L << NULL) | (1L << New) | (1L << This) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde) | (1L << Identifier) | (1L << DecimalInteger))) != 0)) {
					{
					setState(108);
					init();
					}
				}

				setState(111);
				match(Semi);
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << True) | (1L << False) | (1L << NULL) | (1L << New) | (1L << This) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde) | (1L << Identifier) | (1L << DecimalInteger))) != 0)) {
					{
					setState(112);
					cond();
					}
				}

				setState(115);
				match(Semi);
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << True) | (1L << False) | (1L << NULL) | (1L << New) | (1L << This) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde) | (1L << Identifier) | (1L << DecimalInteger))) != 0)) {
					{
					setState(116);
					iter();
					}
				}

				setState(119);
				match(RightParen);
				setState(120);
				statement();
				}
				break;
			case 7:
				_localctx = new ContinueStmtContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(121);
				match(Continue);
				setState(122);
				match(Semi);
				}
				break;
			case 8:
				_localctx = new BreakStmtContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(123);
				match(Break);
				setState(124);
				match(Semi);
				}
				break;
			case 9:
				_localctx = new PureExprStmtContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(125);
				expression(0);
				setState(126);
				match(Semi);
				}
				break;
			case 10:
				_localctx = new EmptyStmtContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(128);
				match(Semi);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationStmtContext extends ParserRuleContext {
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public TerminalNode Semi() { return getToken(MxstarParser.Semi, 0); }
		public DeclarationStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterDeclarationStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitDeclarationStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitDeclarationStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationStmtContext declarationStmt() throws RecognitionException {
		DeclarationStmtContext _localctx = new DeclarationStmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_declarationStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			varDef();
			setState(132);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreatorContext extends ParserRuleContext {
		public CreatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_creator; }
	 
		public CreatorContext() { }
		public void copyFrom(CreatorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrayCreatorContext extends CreatorContext {
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(MxstarParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxstarParser.LeftBracket, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxstarParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxstarParser.RightBracket, i);
		}
		public ArrayCreatorContext(CreatorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterArrayCreator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitArrayCreator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitArrayCreator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ObjectCreatorContext extends CreatorContext {
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(MxstarParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxstarParser.RightParen, 0); }
		public ObjectCreatorContext(CreatorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterObjectCreator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitObjectCreator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitObjectCreator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreatorContext creator() throws RecognitionException {
		CreatorContext _localctx = new CreatorContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_creator);
		try {
			int _alt;
			setState(155);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				_localctx = new ArrayCreatorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				returnType();
				setState(139); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(135);
						match(LeftBracket);
						setState(136);
						expression(0);
						setState(137);
						match(RightBracket);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(141); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(147);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(143);
						match(LeftBracket);
						setState(144);
						match(RightBracket);
						}
						} 
					}
					setState(149);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				}
				}
				break;
			case 2:
				_localctx = new ObjectCreatorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(150);
				returnType();
				setState(153);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(151);
					match(LeftParen);
					setState(152);
					match(RightParen);
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IndexExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LeftBracket() { return getToken(MxstarParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(MxstarParser.RightBracket, 0); }
		public IndexExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterIndexExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitIndexExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitIndexExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrefixExprContext extends ExpressionContext {
		public Token prefix;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SelfPlus() { return getToken(MxstarParser.SelfPlus, 0); }
		public TerminalNode SelfMinus() { return getToken(MxstarParser.SelfMinus, 0); }
		public TerminalNode Not() { return getToken(MxstarParser.Not, 0); }
		public TerminalNode Tilde() { return getToken(MxstarParser.Tilde, 0); }
		public TerminalNode Minus() { return getToken(MxstarParser.Minus, 0); }
		public TerminalNode Plus() { return getToken(MxstarParser.Plus, 0); }
		public PrefixExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterPrefixExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitPrefixExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitPrefixExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CreateExprContext extends ExpressionContext {
		public TerminalNode New() { return getToken(MxstarParser.New, 0); }
		public CreatorContext creator() {
			return getRuleContext(CreatorContext.class,0);
		}
		public CreateExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterCreateExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitCreateExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitCreateExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LambdaExprContext extends ExpressionContext {
		public TerminalNode LeftBracket() { return getToken(MxstarParser.LeftBracket, 0); }
		public TerminalNode And() { return getToken(MxstarParser.And, 0); }
		public TerminalNode RightBracket() { return getToken(MxstarParser.RightBracket, 0); }
		public TerminalNode Minus() { return getToken(MxstarParser.Minus, 0); }
		public TerminalNode Greater() { return getToken(MxstarParser.Greater, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public List<TerminalNode> LeftParen() { return getTokens(MxstarParser.LeftParen); }
		public TerminalNode LeftParen(int i) {
			return getToken(MxstarParser.LeftParen, i);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public List<TerminalNode> RightParen() { return getTokens(MxstarParser.RightParen); }
		public TerminalNode RightParen(int i) {
			return getToken(MxstarParser.RightParen, i);
		}
		public FunctionParameterDefContext functionParameterDef() {
			return getRuleContext(FunctionParameterDefContext.class,0);
		}
		public LambdaExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterLambdaExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitLambdaExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitLambdaExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Dot() { return getToken(MxstarParser.Dot, 0); }
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public TerminalNode LeftParen() { return getToken(MxstarParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxstarParser.RightParen, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public MemberExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterMemberExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitMemberExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitMemberExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SuffixExprContext extends ExpressionContext {
		public Token suffix;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SelfPlus() { return getToken(MxstarParser.SelfPlus, 0); }
		public TerminalNode SelfMinus() { return getToken(MxstarParser.SelfMinus, 0); }
		public SuffixExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterSuffixExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitSuffixExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitSuffixExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AtomExprContext extends ExpressionContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public AtomExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterAtomExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitAtomExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitAtomExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Mul() { return getToken(MxstarParser.Mul, 0); }
		public TerminalNode Div() { return getToken(MxstarParser.Div, 0); }
		public TerminalNode Mod() { return getToken(MxstarParser.Mod, 0); }
		public TerminalNode Plus() { return getToken(MxstarParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(MxstarParser.Minus, 0); }
		public TerminalNode LeftShift() { return getToken(MxstarParser.LeftShift, 0); }
		public TerminalNode RightShift() { return getToken(MxstarParser.RightShift, 0); }
		public TerminalNode Less() { return getToken(MxstarParser.Less, 0); }
		public TerminalNode LessEqual() { return getToken(MxstarParser.LessEqual, 0); }
		public TerminalNode Greater() { return getToken(MxstarParser.Greater, 0); }
		public TerminalNode GreaterEqual() { return getToken(MxstarParser.GreaterEqual, 0); }
		public TerminalNode Equal() { return getToken(MxstarParser.Equal, 0); }
		public TerminalNode NotEqual() { return getToken(MxstarParser.NotEqual, 0); }
		public TerminalNode And() { return getToken(MxstarParser.And, 0); }
		public TerminalNode Caret() { return getToken(MxstarParser.Caret, 0); }
		public TerminalNode Or() { return getToken(MxstarParser.Or, 0); }
		public TerminalNode AndAnd() { return getToken(MxstarParser.AndAnd, 0); }
		public TerminalNode OrOr() { return getToken(MxstarParser.OrOr, 0); }
		public BinaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterBinaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitBinaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitBinaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Assign() { return getToken(MxstarParser.Assign, 0); }
		public AssignExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterAssignExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitAssignExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitAssignExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(MxstarParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxstarParser.RightParen, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public FunctionExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterFunctionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitFunctionExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitFunctionExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
			case True:
			case False:
			case NULL:
			case This:
			case LeftParen:
			case Identifier:
			case DecimalInteger:
				{
				_localctx = new AtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(158);
				primary();
				}
				break;
			case New:
				{
				_localctx = new CreateExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(159);
				match(New);
				setState(160);
				creator();
				}
				break;
			case LeftBracket:
				{
				_localctx = new LambdaExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(161);
				match(LeftBracket);
				setState(162);
				match(And);
				setState(163);
				match(RightBracket);
				{
				setState(164);
				match(LeftParen);
				setState(165);
				functionParameterDef();
				setState(166);
				match(RightParen);
				}
				setState(168);
				match(Minus);
				setState(169);
				match(Greater);
				setState(170);
				suite();
				setState(171);
				match(LeftParen);
				setState(172);
				expressionList();
				setState(173);
				match(RightParen);
				}
				break;
			case Plus:
			case SelfPlus:
			case Minus:
			case SelfMinus:
			case Not:
			case Tilde:
				{
				_localctx = new PrefixExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(175);
				((PrefixExprContext)_localctx).prefix = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde))) != 0)) ) {
					((PrefixExprContext)_localctx).prefix = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(176);
				expression(12);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(237);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(235);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(179);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(180);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Mul) | (1L << Div) | (1L << Mod))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(181);
						expression(12);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(182);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(183);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Plus || _la==Minus) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(184);
						expression(11);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(185);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(186);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==LeftShift || _la==RightShift) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(187);
						expression(10);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(188);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(189);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Less) | (1L << LessEqual) | (1L << Greater) | (1L << GreaterEqual))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(190);
						expression(9);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(191);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(192);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Equal || _la==NotEqual) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(193);
						expression(8);
						}
						break;
					case 6:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(194);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(195);
						((BinaryExprContext)_localctx).op = match(And);
						setState(196);
						expression(7);
						}
						break;
					case 7:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(197);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(198);
						((BinaryExprContext)_localctx).op = match(Caret);
						setState(199);
						expression(6);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(200);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(201);
						((BinaryExprContext)_localctx).op = match(Or);
						setState(202);
						expression(5);
						}
						break;
					case 9:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(203);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(204);
						((BinaryExprContext)_localctx).op = match(AndAnd);
						setState(205);
						expression(4);
						}
						break;
					case 10:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(206);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(207);
						((BinaryExprContext)_localctx).op = match(OrOr);
						setState(208);
						expression(3);
						}
						break;
					case 11:
						{
						_localctx = new AssignExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(209);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(210);
						match(Assign);
						setState(211);
						expression(1);
						}
						break;
					case 12:
						{
						_localctx = new MemberExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(212);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(213);
						match(Dot);
						setState(214);
						match(Identifier);
						setState(220);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
						case 1:
							{
							setState(215);
							match(LeftParen);
							setState(217);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << True) | (1L << False) | (1L << NULL) | (1L << New) | (1L << This) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde) | (1L << Identifier) | (1L << DecimalInteger))) != 0)) {
								{
								setState(216);
								expressionList();
								}
							}

							setState(219);
							match(RightParen);
							}
							break;
						}
						}
						break;
					case 13:
						{
						_localctx = new IndexExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(222);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(223);
						match(LeftBracket);
						setState(224);
						expression(0);
						setState(225);
						match(RightBracket);
						}
						break;
					case 14:
						{
						_localctx = new FunctionExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(227);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(228);
						match(LeftParen);
						setState(230);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << True) | (1L << False) | (1L << NULL) | (1L << New) | (1L << This) | (1L << LeftParen) | (1L << LeftBracket) | (1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde) | (1L << Identifier) | (1L << DecimalInteger))) != 0)) {
							{
							setState(229);
							expressionList();
							}
						}

						setState(232);
						match(RightParen);
						}
						break;
					case 15:
						{
						_localctx = new SuffixExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(233);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(234);
						((SuffixExprContext)_localctx).suffix = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==SelfPlus || _la==SelfMinus) ) {
							((SuffixExprContext)_localctx).suffix = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(239);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class VarDefContext extends ParserRuleContext {
		public VarTypeContext varType() {
			return getRuleContext(VarTypeContext.class,0);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxstarParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxstarParser.Comma, i);
		}
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitVarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_varDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			varType();
			setState(241);
			varDeclaration();
			setState(246);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(242);
				match(Comma);
				setState(243);
				varDeclaration();
				}
				}
				setState(248);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclarationContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public TerminalNode Assign() { return getToken(MxstarParser.Assign, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitVarDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitVarDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclarationContext varDeclaration() throws RecognitionException {
		VarDeclarationContext _localctx = new VarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_varDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			match(Identifier);
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(250);
				match(Assign);
				setState(251);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnTypeContext extends ParserRuleContext {
		public TerminalNode Void() { return getToken(MxstarParser.Void, 0); }
		public VarTypeContext varType() {
			return getRuleContext(VarTypeContext.class,0);
		}
		public ReturnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterReturnType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitReturnType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitReturnType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_returnType);
		try {
			setState(256);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Void:
				enterOuterAlt(_localctx, 1);
				{
				setState(254);
				match(Void);
				}
				break;
			case Int:
			case Bool:
			case String:
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(255);
				varType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarTypeContext extends ParserRuleContext {
		public BuiltinTypeContext builtinType() {
			return getRuleContext(BuiltinTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public VarTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterVarType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitVarType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitVarType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarTypeContext varType() throws RecognitionException {
		VarTypeContext _localctx = new VarTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_varType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(258);
				builtinType();
				}
				break;
			case 2:
				{
				setState(259);
				match(Identifier);
				}
				break;
			case 3:
				{
				setState(260);
				arrayType();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BuiltinTypeContext extends ParserRuleContext {
		public TerminalNode Int() { return getToken(MxstarParser.Int, 0); }
		public TerminalNode Bool() { return getToken(MxstarParser.Bool, 0); }
		public TerminalNode String() { return getToken(MxstarParser.String, 0); }
		public BuiltinTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_builtinType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterBuiltinType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitBuiltinType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitBuiltinType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BuiltinTypeContext builtinType() throws RecognitionException {
		BuiltinTypeContext _localctx = new BuiltinTypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_builtinType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayTypeContext extends ParserRuleContext {
		public BuiltinTypeContext builtinType() {
			return getRuleContext(BuiltinTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public List<TerminalNode> LeftBracket() { return getTokens(MxstarParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxstarParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxstarParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxstarParser.RightBracket, i);
		}
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitArrayType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_arrayType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Int:
			case Bool:
			case String:
				{
				setState(265);
				builtinType();
				}
				break;
			case Identifier:
				{
				setState(266);
				match(Identifier);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(271); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(269);
					match(LeftBracket);
					setState(270);
					match(RightBracket);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(273); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public TerminalNode LeftParen() { return getToken(MxstarParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxstarParser.RightParen, 0); }
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode This() { return getToken(MxstarParser.This, 0); }
		public TerminalNode NULL() { return getToken(MxstarParser.NULL, 0); }
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_primary);
		try {
			setState(283);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LeftParen:
				enterOuterAlt(_localctx, 1);
				{
				setState(275);
				match(LeftParen);
				setState(276);
				expression(0);
				setState(277);
				match(RightParen);
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(279);
				match(Identifier);
				}
				break;
			case STRING:
			case True:
			case False:
			case DecimalInteger:
				enterOuterAlt(_localctx, 3);
				{
				setState(280);
				literal();
				}
				break;
			case This:
				enterOuterAlt(_localctx, 4);
				{
				setState(281);
				match(This);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 5);
				{
				setState(282);
				match(NULL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode DecimalInteger() { return getToken(MxstarParser.DecimalInteger, 0); }
		public TerminalNode True() { return getToken(MxstarParser.True, 0); }
		public TerminalNode False() { return getToken(MxstarParser.False, 0); }
		public TerminalNode STRING() { return getToken(MxstarParser.STRING, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << True) | (1L << False) | (1L << DecimalInteger))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public VarDeclarationContext varDeclaration() {
			return getRuleContext(VarDeclarationContext.class,0);
		}
		public InitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_init; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitContext init() throws RecognitionException {
		InitContext _localctx = new InitContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_init);
		try {
			setState(291);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(287);
				expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(288);
				returnType();
				setState(289);
				varDeclaration();
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CondContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitCond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitCond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondContext cond() throws RecognitionException {
		CondContext _localctx = new CondContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_cond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IterContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterIter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitIter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitIter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IterContext iter() throws RecognitionException {
		IterContext _localctx = new IterContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_iter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 8:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 11);
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 8);
		case 4:
			return precpred(_ctx, 7);
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		case 7:
			return precpred(_ctx, 4);
		case 8:
			return precpred(_ctx, 3);
		case 9:
			return precpred(_ctx, 2);
		case 10:
			return precpred(_ctx, 1);
		case 11:
			return precpred(_ctx, 18);
		case 12:
			return precpred(_ctx, 16);
		case 13:
			return precpred(_ctx, 15);
		case 14:
			return precpred(_ctx, 13);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3>\u012c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\7\2-\n\2\f\2\16\2\60\13\2\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\7\4?\n\4\f\4\16\4B\13\4"+
		"\5\4D\n\4\3\5\3\5\3\5\7\5I\n\5\f\5\16\5L\13\5\3\6\3\6\7\6P\n\6\f\6\16"+
		"\6S\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7`\n\7\3\7\3\7"+
		"\5\7d\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7p\n\7\3\7\3\7\5\7"+
		"t\n\7\3\7\3\7\5\7x\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0084"+
		"\n\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\6\t\u008e\n\t\r\t\16\t\u008f\3\t"+
		"\3\t\7\t\u0094\n\t\f\t\16\t\u0097\13\t\3\t\3\t\3\t\5\t\u009c\n\t\5\t\u009e"+
		"\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\5\n\u00b4\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00dc\n\n\3\n\5\n\u00df\n"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00e9\n\n\3\n\3\n\3\n\7\n\u00ee"+
		"\n\n\f\n\16\n\u00f1\13\n\3\13\3\13\3\13\3\13\7\13\u00f7\n\13\f\13\16\13"+
		"\u00fa\13\13\3\f\3\f\3\f\5\f\u00ff\n\f\3\r\3\r\5\r\u0103\n\r\3\16\3\16"+
		"\3\16\5\16\u0108\n\16\3\17\3\17\3\20\3\20\5\20\u010e\n\20\3\20\3\20\6"+
		"\20\u0112\n\20\r\20\16\20\u0113\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\5\21\u011e\n\21\3\22\3\22\3\23\3\23\3\23\3\23\5\23\u0126\n\23\3\24\3"+
		"\24\3\25\3\25\3\25\2\3\22\26\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"$&(\2\13\4\2\"%./\3\2&(\4\2\"\"$$\3\2 !\3\2\34\37\3\2\65\66\4\2##%%\4"+
		"\2\4\5\t\t\5\2\3\3\7\b::\2\u0150\2.\3\2\2\2\4\61\3\2\2\2\6C\3\2\2\2\b"+
		"E\3\2\2\2\nM\3\2\2\2\f\u0083\3\2\2\2\16\u0085\3\2\2\2\20\u009d\3\2\2\2"+
		"\22\u00b3\3\2\2\2\24\u00f2\3\2\2\2\26\u00fb\3\2\2\2\30\u0102\3\2\2\2\32"+
		"\u0107\3\2\2\2\34\u0109\3\2\2\2\36\u010d\3\2\2\2 \u011d\3\2\2\2\"\u011f"+
		"\3\2\2\2$\u0125\3\2\2\2&\u0127\3\2\2\2(\u0129\3\2\2\2*-\5\16\b\2+-\5\4"+
		"\3\2,*\3\2\2\2,+\3\2\2\2-\60\3\2\2\2.,\3\2\2\2./\3\2\2\2/\3\3\2\2\2\60"+
		".\3\2\2\2\61\62\5\30\r\2\62\63\79\2\2\63\64\7\26\2\2\64\65\5\6\4\2\65"+
		"\66\7\27\2\2\66\67\5\n\6\2\67\5\3\2\2\289\5\32\16\29@\79\2\2:;\7\63\2"+
		"\2;<\5\32\16\2<=\79\2\2=?\3\2\2\2>:\3\2\2\2?B\3\2\2\2@>\3\2\2\2@A\3\2"+
		"\2\2AD\3\2\2\2B@\3\2\2\2C8\3\2\2\2CD\3\2\2\2D\7\3\2\2\2EJ\5\22\n\2FG\7"+
		"\63\2\2GI\5\22\n\2HF\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\t\3\2\2\2"+
		"LJ\3\2\2\2MQ\7\32\2\2NP\5\f\7\2ON\3\2\2\2PS\3\2\2\2QO\3\2\2\2QR\3\2\2"+
		"\2RT\3\2\2\2SQ\3\2\2\2TU\7\33\2\2U\13\3\2\2\2V\u0084\5\n\6\2W\u0084\5"+
		"\16\b\2XY\7\22\2\2YZ\7\26\2\2Z[\5\22\n\2[\\\7\27\2\2\\_\5\f\7\2]^\7\23"+
		"\2\2^`\5\f\7\2_]\3\2\2\2_`\3\2\2\2`\u0084\3\2\2\2ac\7\24\2\2bd\5\22\n"+
		"\2cb\3\2\2\2cd\3\2\2\2de\3\2\2\2e\u0084\7\62\2\2fg\7\f\2\2gh\7\26\2\2"+
		"hi\5\22\n\2ij\7\27\2\2jk\5\f\7\2k\u0084\3\2\2\2lm\7\13\2\2mo\7\26\2\2"+
		"np\5$\23\2on\3\2\2\2op\3\2\2\2pq\3\2\2\2qs\7\62\2\2rt\5&\24\2sr\3\2\2"+
		"\2st\3\2\2\2tu\3\2\2\2uw\7\62\2\2vx\5(\25\2wv\3\2\2\2wx\3\2\2\2xy\3\2"+
		"\2\2yz\7\27\2\2z\u0084\5\f\7\2{|\7\16\2\2|\u0084\7\62\2\2}~\7\r\2\2~\u0084"+
		"\7\62\2\2\177\u0080\5\22\n\2\u0080\u0081\7\62\2\2\u0081\u0084\3\2\2\2"+
		"\u0082\u0084\7\62\2\2\u0083V\3\2\2\2\u0083W\3\2\2\2\u0083X\3\2\2\2\u0083"+
		"a\3\2\2\2\u0083f\3\2\2\2\u0083l\3\2\2\2\u0083{\3\2\2\2\u0083}\3\2\2\2"+
		"\u0083\177\3\2\2\2\u0083\u0082\3\2\2\2\u0084\r\3\2\2\2\u0085\u0086\5\24"+
		"\13\2\u0086\u0087\7\62\2\2\u0087\17\3\2\2\2\u0088\u008d\5\30\r\2\u0089"+
		"\u008a\7\30\2\2\u008a\u008b\5\22\n\2\u008b\u008c\7\31\2\2\u008c\u008e"+
		"\3\2\2\2\u008d\u0089\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u008d\3\2\2\2\u008f"+
		"\u0090\3\2\2\2\u0090\u0095\3\2\2\2\u0091\u0092\7\30\2\2\u0092\u0094\7"+
		"\31\2\2\u0093\u0091\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0095"+
		"\u0096\3\2\2\2\u0096\u009e\3\2\2\2\u0097\u0095\3\2\2\2\u0098\u009b\5\30"+
		"\r\2\u0099\u009a\7\26\2\2\u009a\u009c\7\27\2\2\u009b\u0099\3\2\2\2\u009b"+
		"\u009c\3\2\2\2\u009c\u009e\3\2\2\2\u009d\u0088\3\2\2\2\u009d\u0098\3\2"+
		"\2\2\u009e\21\3\2\2\2\u009f\u00a0\b\n\1\2\u00a0\u00b4\5 \21\2\u00a1\u00a2"+
		"\7\17\2\2\u00a2\u00b4\5\20\t\2\u00a3\u00a4\7\30\2\2\u00a4\u00a5\7)\2\2"+
		"\u00a5\u00a6\7\31\2\2\u00a6\u00a7\7\26\2\2\u00a7\u00a8\5\6\4\2\u00a8\u00a9"+
		"\7\27\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\7$\2\2\u00ab\u00ac\7\36\2\2"+
		"\u00ac\u00ad\5\n\6\2\u00ad\u00ae\7\26\2\2\u00ae\u00af\5\b\5\2\u00af\u00b0"+
		"\7\27\2\2\u00b0\u00b4\3\2\2\2\u00b1\u00b2\t\2\2\2\u00b2\u00b4\5\22\n\16"+
		"\u00b3\u009f\3\2\2\2\u00b3\u00a1\3\2\2\2\u00b3\u00a3\3\2\2\2\u00b3\u00b1"+
		"\3\2\2\2\u00b4\u00ef\3\2\2\2\u00b5\u00b6\f\r\2\2\u00b6\u00b7\t\3\2\2\u00b7"+
		"\u00ee\5\22\n\16\u00b8\u00b9\f\f\2\2\u00b9\u00ba\t\4\2\2\u00ba\u00ee\5"+
		"\22\n\r\u00bb\u00bc\f\13\2\2\u00bc\u00bd\t\5\2\2\u00bd\u00ee\5\22\n\f"+
		"\u00be\u00bf\f\n\2\2\u00bf\u00c0\t\6\2\2\u00c0\u00ee\5\22\n\13\u00c1\u00c2"+
		"\f\t\2\2\u00c2\u00c3\t\7\2\2\u00c3\u00ee\5\22\n\n\u00c4\u00c5\f\b\2\2"+
		"\u00c5\u00c6\7)\2\2\u00c6\u00ee\5\22\n\t\u00c7\u00c8\f\7\2\2\u00c8\u00c9"+
		"\7-\2\2\u00c9\u00ee\5\22\n\b\u00ca\u00cb\f\6\2\2\u00cb\u00cc\7*\2\2\u00cc"+
		"\u00ee\5\22\n\7\u00cd\u00ce\f\5\2\2\u00ce\u00cf\7+\2\2\u00cf\u00ee\5\22"+
		"\n\6\u00d0\u00d1\f\4\2\2\u00d1\u00d2\7,\2\2\u00d2\u00ee\5\22\n\5\u00d3"+
		"\u00d4\f\3\2\2\u00d4\u00d5\7\64\2\2\u00d5\u00ee\5\22\n\3\u00d6\u00d7\f"+
		"\24\2\2\u00d7\u00d8\7\25\2\2\u00d8\u00de\79\2\2\u00d9\u00db\7\26\2\2\u00da"+
		"\u00dc\5\b\5\2\u00db\u00da\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\3\2"+
		"\2\2\u00dd\u00df\7\27\2\2\u00de\u00d9\3\2\2\2\u00de\u00df\3\2\2\2\u00df"+
		"\u00ee\3\2\2\2\u00e0\u00e1\f\22\2\2\u00e1\u00e2\7\30\2\2\u00e2\u00e3\5"+
		"\22\n\2\u00e3\u00e4\7\31\2\2\u00e4\u00ee\3\2\2\2\u00e5\u00e6\f\21\2\2"+
		"\u00e6\u00e8\7\26\2\2\u00e7\u00e9\5\b\5\2\u00e8\u00e7\3\2\2\2\u00e8\u00e9"+
		"\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00ee\7\27\2\2\u00eb\u00ec\f\17\2\2"+
		"\u00ec\u00ee\t\b\2\2\u00ed\u00b5\3\2\2\2\u00ed\u00b8\3\2\2\2\u00ed\u00bb"+
		"\3\2\2\2\u00ed\u00be\3\2\2\2\u00ed\u00c1\3\2\2\2\u00ed\u00c4\3\2\2\2\u00ed"+
		"\u00c7\3\2\2\2\u00ed\u00ca\3\2\2\2\u00ed\u00cd\3\2\2\2\u00ed\u00d0\3\2"+
		"\2\2\u00ed\u00d3\3\2\2\2\u00ed\u00d6\3\2\2\2\u00ed\u00e0\3\2\2\2\u00ed"+
		"\u00e5\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00ed\3\2"+
		"\2\2\u00ef\u00f0\3\2\2\2\u00f0\23\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f2\u00f3"+
		"\5\32\16\2\u00f3\u00f8\5\26\f\2\u00f4\u00f5\7\63\2\2\u00f5\u00f7\5\26"+
		"\f\2\u00f6\u00f4\3\2\2\2\u00f7\u00fa\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8"+
		"\u00f9\3\2\2\2\u00f9\25\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb\u00fe\79\2\2"+
		"\u00fc\u00fd\7\64\2\2\u00fd\u00ff\5\22\n\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff"+
		"\3\2\2\2\u00ff\27\3\2\2\2\u0100\u0103\7\6\2\2\u0101\u0103\5\32\16\2\u0102"+
		"\u0100\3\2\2\2\u0102\u0101\3\2\2\2\u0103\31\3\2\2\2\u0104\u0108\5\34\17"+
		"\2\u0105\u0108\79\2\2\u0106\u0108\5\36\20\2\u0107\u0104\3\2\2\2\u0107"+
		"\u0105\3\2\2\2\u0107\u0106\3\2\2\2\u0108\33\3\2\2\2\u0109\u010a\t\t\2"+
		"\2\u010a\35\3\2\2\2\u010b\u010e\5\34\17\2\u010c\u010e\79\2\2\u010d\u010b"+
		"\3\2\2\2\u010d\u010c\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u0110\7\30\2\2"+
		"\u0110\u0112\7\31\2\2\u0111\u010f\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0111"+
		"\3\2\2\2\u0113\u0114\3\2\2\2\u0114\37\3\2\2\2\u0115\u0116\7\26\2\2\u0116"+
		"\u0117\5\22\n\2\u0117\u0118\7\27\2\2\u0118\u011e\3\2\2\2\u0119\u011e\7"+
		"9\2\2\u011a\u011e\5\"\22\2\u011b\u011e\7\21\2\2\u011c\u011e\7\n\2\2\u011d"+
		"\u0115\3\2\2\2\u011d\u0119\3\2\2\2\u011d\u011a\3\2\2\2\u011d\u011b\3\2"+
		"\2\2\u011d\u011c\3\2\2\2\u011e!\3\2\2\2\u011f\u0120\t\n\2\2\u0120#\3\2"+
		"\2\2\u0121\u0126\5\22\n\2\u0122\u0123\5\30\r\2\u0123\u0124\5\26\f\2\u0124"+
		"\u0126\3\2\2\2\u0125\u0121\3\2\2\2\u0125\u0122\3\2\2\2\u0126%\3\2\2\2"+
		"\u0127\u0128\5\22\n\2\u0128\'\3\2\2\2\u0129\u012a\5\22\n\2\u012a)\3\2"+
		"\2\2 ,.@CJQ_cosw\u0083\u008f\u0095\u009b\u009d\u00b3\u00db\u00de\u00e8"+
		"\u00ed\u00ef\u00f8\u00fe\u0102\u0107\u010d\u0113\u011d\u0125";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}