grammar Mxstar;
program: (declarationStmt | functionDef)*;
functionDef : returnType Identifier '(' functionParameterDef ')'suite;
functionParameterDef : (varType Identifier (',' varType Identifier)* )?;
expressionList : expression (',' expression)*;
suite : '{' statement* '}';
statement : suite #block
    | declarationStmt #varDefStmt
    | If '(' expression ')' trueStmt=statement (Else falseStmt=statement)? #ifStmt
    | Return expression? ';' #returnStmt
    | expression ';' #pureExprStmt | ';' #emptyStmt ;
declarationStmt : varDef ';';
expression : primary #atomExpr
    | expression '[' expression ']' #indexExpr
    | expression '(' expressionList? ')' #functionExpr
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
    | <assoc=right> expression '=' expression #assignExpr ;
varDef : varType varDeclaration (',' varDeclaration)*;
varDeclaration : Identifier ('=' expression)?;
returnType: Void | varType; varType : (builtinType | Identifier);
builtinType : Int | Bool; primary : '(' expression ')' | Identifier | literal ; literal : DecimalInteger | True | False ; Int : 'int'; Bool : 'bool'; Void : 'void'; True : 'true'; False : 'false'; If : 'if'; Else : 'else'; Return : 'return'; Dot : '.'; LeftParen : '('; RightParen : ')'; LeftBracket : '['; RightBracket : ']'; LeftBrace : '{'; RightBrace : '}'; Less : '<'; LessEqual : '<='; Greater : '>'; GreaterEqual : '>='; LeftShift : '<<'; RightShift : '>>'; Plus : '+'; SelfPlus : '++'; Minus : '-'; SelfMinus : '--'; Mul : '*'; Div : '/'; Mod : '%'; And : '&'; Or : '|'; AndAnd : '&&';
OrOr : '||'; Caret : '^'; Not : '!'; Tilde : '~'; Question : '?'; Colon : ':'; Semi : ';'; Comma : ','; Assign : '='; Equal : '=='; NotEqual : '!='; BackSlash : '\\\\'; DbQuotation : '\\"'; Identifier : [a-zA-Z] [a-zA-Z_0-9]* ; DecimalInteger : [1-9] [0-9]* | '0' ; Whitespace : [ \t]+ -> skip ; Newline : ( '\r' '\n'? | '\n' )-> skip ; BlockComment : '/*' .*? '*/' -> skip ; LineComment : '//' ~[\r\n]* -> skip ; 