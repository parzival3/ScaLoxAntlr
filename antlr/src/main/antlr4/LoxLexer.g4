lexer grammar LoxLexer;

// Keywords

CLASS:              'class';
ELSE:               'else';
FOR:                'for';
IF:                 'if';
RETURN:             'return';
SUPER:              'super';
THIS:               'this';
WHILE:              'while';
VAR:                'var';
PRINT:              'print';
FUN:                'fun';
// Literals

NUMBER:      Digits '.'? Digits?;

BOOL_LITERAL:       'true'
            |       'false'
            ;

CHAR_LITERAL:       '\'' (~['\\\r\n]) '\'';

STRING_LITERAL:     '"' (~["\\\r\n])* '"';

NIL_LITERAL:       'nil';

// Separators

LEFT_PAREN:             '(';
RIGHT_PAREN:             ')';
LEFT_BRACE:             '{';
RIGHT_BRACE:             '}';
SEMICOLON:               ';';
COMMA:                  ',';
DOT:                    '.';

// Operators

EQUAL:             '=';
GREATER:                 '>';
LESS:                 '<';
BANG:               '!';
COLON:              ':';
EQUAL_EQUAL:              '==';
LESS_EQUAL:                 '<=';
GREATER_EQUAL:                 '>=';
BANG_EQUAL:           '!=';
AND:                'and';
OR:                 'or';
PLUS:                '+';
MINUS:                '-';
STAR:                '*';
SLASH:                '/';


// Whitespace and comments

WS:                 [ \t\r\n\u000C]+ -> channel(HIDDEN);
COMMENT:            '/*' .*? '*/'    -> channel(HIDDEN);
LINE_COMMENT:       '//' ~[\r\n]*    -> channel(HIDDEN);

// Identifiers

IDENTIFIER:         Letter LetterOrDigit*;

// TODO: remove this
// fragment EscapeSequence
//     : '\\' [btnfr"'\\]
//     | '\\' ([0-3]? [0-7])? [0-7]
//     | '\\' 'u'+ HexDigit HexDigit HexDigit HexDigit
//     ;


fragment Digits
    : ([0-9])+
    ;

fragment LetterOrDigit
    : Letter
    | [0-9]
    ;

fragment Letter
    : [a-zA-Z$_] // these are the "java letters" below 0x7F
    | ~[\u0000-\u007F\uD800-\uDBFF] // covers all characters above 0x7F which are not a surrogate
    | [\uD800-\uDBFF] [\uDC00-\uDFFF] // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
    ;
