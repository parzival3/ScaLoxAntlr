
parser grammar LoxParser;

options { tokenVocab=LoxLexer; }

compilationUnit
    : classDecl
    | funDecl
    | varDecl
    | statement
    ;

classDecl
    : CLASS IDENTIFIER (LESS IDENTIFIER)?
      LEFT_BRACE (function)* RIGHT_BRACE
    ;

funDecl
    : FUN function
    ;

varDecl
    : VAR IDENTIFIER ( EQUAL expression )? SEMICOLON
    ;

statement
    : exprStmt
    | forStmt
    | ifStmt
    | printStmt
    | returnStmt
    | whileStmt
    | block
    ;

exprStmt
    : expression SEMICOLON
    ;

forStmt
    : FOR LEFT_PAREN ( varDecl | exprStmt | SEMICOLON )
      expression? SEMICOLON expression? LEFT_PAREN statement
    ;

ifStmt
    : IF LEFT_PAREN expression RIGHT_PAREN statement ( ELSE statement )?
    ;

printStmt
    : PRINT expression SEMICOLON
    ;

returnStmt
    : RETURN expression? SEMICOLON
    ;

whileStmt
    : WHILE LEFT_PAREN expression RIGHT_PAREN statement
    ;

block
    : LEFT_BRACE compilationUnit* RIGHT_BRACE
    ;

expression
    : assignment
    ;

assignment
    : (call DOT)? IDENTIFIER EQUAL assignment
    | logic_or
    ;

logic_or
    : logic_and ( OR logic_and )*
    ;

logic_and
    : equality ( AND equality)*
    ;

equality
    : comparison ( (BANG_EQUAL | EQUAL_EQUAL) comparison )*
    ;

comparison
    : term ( ( LESS | LESS_EQUAL | GREATER_EQUAL | GREATER ) term )*
    ;

term
    : factor ( ( MINUS | PLUS ) factor )*
    ;

factor
    : unary ( ( SLASH | STAR ) unary )*
    ;

unary
    : ( BANG | MINUS ) unary | call
    ;

call
    : primary ( LEFT_PAREN arguments? RIGHT_PAREN | DOT IDENTIFIER )*
    ;

primary
    : BOOL_LITERAL
    | NIL_LITERAL
    | THIS
    | NUMBER
    | STRING_LITERAL
    | IDENTIFIER
    | LEFT_PAREN expression RIGHT_PAREN
    | SUPER DOT IDENTIFIER
    ;


function
    : IDENTIFIER LEFT_PAREN parameters? RIGHT_PAREN block
    ;

parameters
    : IDENTIFIER ( COMMA IDENTIFIER )*
    ;
arguments
    : expression ( COMMA expression )*
    ;
