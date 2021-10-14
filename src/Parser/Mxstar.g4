grammar Mxstar;
program: (declarationStmt | functionDef)*;
functionDef : returnType Identifier '(' functionParameterDef ')'suite;
functionParameterDef : (varType Identifier (',' varType Identifier)* )?;
expressionList : expression (',' expression)*;
suite : '{' statement* '}';
statement
    : suite #block
    | declarationStmt #varDefStmt
    | If '(' expression ')' trueStmt=statement
      (Else falseStmt=statement)? #ifStmt
    | Return expression? ';' #returnStmt
    | While '(' expression ')' statement #whileStmt
    | For '(' init? ';' cond? ';' iter? ')' statement #forStmt
    | Continue ';' #continueStmt
    | Break ';' #breakStmt
    | expression ';' #pureExprStmt
    | ';' #emptyStmt
    ;
declarationStmt : varDef ';';
creator
    : returnType ('[' expression ']')+ ('[' ']')* #arrayCreator
    | returnType ('('')')? #objectCreator
    ;
expression
    : primary #atomExpr
    | expression '.' Identifier ('(' expressionList? ')')? #memberExpr
    | <assoc=right> New creator #createExpr
    | expression '[' expression ']' #indexExpr
    | expression '(' expressionList? ')' #functionExpr
    | '[' '&' ']' ('(' functionParameterDef ')') '-' '>' suite '(' expressionList ')' #lambdaExpr
    | expression suffix = ('++' | '--') #suffixExpr
    | <assoc=right> prefix = ('++' | '--' | '!' | '~' | '-' | '+') expression #prefixExpr
    | expression op = ('*' | '/' | '%') expression #binaryExpr
    | expression op = ('+' | '-') expression #binaryExpr
    | expression op = ('<<' | '>>') expression #binaryExpr
    | expression op = ('<' | '<=' | '>' | '>=') expression #binaryExpr
    | expression op = ('==' | '!=') expression #binaryExpr
    | expression op = '&' expression #binaryExpr
    | expression op = '^' expression #binaryExpr
    | expression op = '|' expression #binaryExpr
    | expression op = '&&' expression #binaryExpr
    | expression op = '||' expression #binaryExpr
    | <assoc=right> expression '=' expression #assignExpr
    ;
varDef : varType varDeclaration (',' varDeclaration)*;
varDeclaration : Identifier ('=' expression)?;
returnType: Void | varType ;
varType : (builtinType | Identifier | arrayType);
builtinType : Int | Bool | String;
arrayType : (builtinType | Identifier)('[' ']')+;
primary : '(' expression ')' | Identifier | literal | This | NULL;
//字符串处理
fragment ESC :
    DbQuotation | '\\n' ;
STRING : '"' (ESC|.)*? '"';
literal
    : DecimalInteger
    | True
    | False
    | STRING
    ;

init : expression | (returnType varDeclaration);
cond : expression;
iter : expression;

Int : 'int';
Bool : 'bool';
Void : 'void';
True : 'true';
False : 'false';
String : 'string';
NULL : 'null';
For : 'for';
While : 'while';
Break : 'break';
Continue : 'continue';
New : 'new';
Class : 'class';
This : 'this';
If : 'if';
Else : 'else';
Return : 'return';

Dot : '.';
LeftParen : '(';
RightParen : ')';
LeftBracket : '[';
RightBracket : ']';
LeftBrace : '{';
RightBrace : '}';
Less : '<';
LessEqual : '<=';
Greater : '>';
GreaterEqual : '>=';
LeftShift : '<<';
RightShift : '>>';

Plus : '+';
SelfPlus : '++';
Minus : '-';
SelfMinus : '--';
Mul : '*';
Div : '/';
Mod : '%';
And : '&';
Or : '|';
AndAnd : '&&';
OrOr : '||';
Caret : '^';
Not : '!';
Tilde : '~';
Question : '?';

Colon : ':';
Semi : ';';
Comma : ',';
Assign : '=';
Equal : '==';
NotEqual : '!=';
BackSlash : '\\\\';
DbQuotation : '\\"';

Identifier : [a-zA-Z] [a-zA-Z_0-9]* ;
DecimalInteger : [1-9] [0-9]* | '0' ;
Whitespace : [ \t]+ -> skip ;
Newline : ( '\r' '\n'? | '\n' )-> skip ;
BlockComment : '/*' .*? '*/' -> skip ;
LineComment : '//' ~[\r\n]* -> skip ;