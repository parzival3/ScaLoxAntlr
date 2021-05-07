
parser grammar LoxParser;

options { tokenVocab=LoxLexer; }
declaration
    : compilationUnit*
    ;

compilationUnit
    : statement #cuStatement
    | VAR IDENTIFIER ( EQUAL expression )? SEMICOLON #cuVarDeclaration
    | FUN function #cuFunctionDeclaration
    | CLASS IDENTIFIER (LESS sup=IDENTIFIER)? LEFT_BRACE (function)* RIGHT_BRACE #cuClassDeclaration
    ;

varDecl
    : VAR IDENTIFIER ( EQUAL expression )? SEMICOLON #variableDeclaration
    ;

statement
    : LEFT_BRACE compilationUnit* RIGHT_BRACE #statementBlock
    | IF LEFT_PAREN expression RIGHT_PAREN statement ( ELSE statement )? #ifStatement
    | FOR LEFT_PAREN ( varDecl | expression SEMICOLON | SEMICOLON ) expression? SEMICOLON expression? LEFT_PAREN statement #forStatement
    | WHILE LEFT_PAREN expression RIGHT_PAREN statement #whileStatement
    | RETURN expression? SEMICOLON #returnStatement
    | PRINT expression SEMICOLON #printStatement
    | expression SEMICOLON #expressionStatement
    ;

expression
    : primary #primaryE
    | call #callE
    | prefix=(BANG | MINUS) expression #unary
    | expression bop=(STAR | SLASH) expression #binary
    | expression bop=(PLUS | MINUS) expression #binary
    | expression bop=(EQUAL_EQUAL | BANG_EQUAL) expression #binary
    | expression op=AND expression #logic
    | expression op=OR expression #logic
    | <assoc=right> (call DOT)? IDENTIFIER EQUAL expression #assignment
    ;

call
    : primary ( LEFT_PAREN (expression ( COMMA expression )*)? RIGHT_PAREN | DOT IDENTIFIER )*
    ;

primary
    : LEFT_PAREN expression RIGHT_PAREN      #parenExpression                               
    | BOOL_LITERAL #boolExpression
    | NIL_LITERAL #nilExpression
    | THIS #thisExpression
    | NUMBER #numberExpression
    | STRING_LITERAL #stringExpression
    | IDENTIFIER #indentifierExpression
    | SUPER DOT IDENTIFIER #superExpression
    ;


function
    : name=IDENTIFIER LEFT_PAREN (IDENTIFIER ( COMMA IDENTIFIER )*)? RIGHT_PAREN LEFT_BRACE compilationUnit* RIGHT_BRACE
    ;
