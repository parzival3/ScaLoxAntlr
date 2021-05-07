package io.github.parzival3.scaloxantlr.ast

// This is necessary to use the enum in Java
// https://dotty.epfl.ch/docs/reference/enums/enums.html#compatibility-with-java-enums
enum TokenType(lexeme: String) extends Enum[TokenType]:
  // Single-character tokens.
  case LEFT_PAREN extends TokenType("(")
  case RIGHT_PAREN extends TokenType(")")
  case LEFT_BRACE extends TokenType("{")
  case RIGHT_BRACE extends TokenType("}")
  case COMMA extends TokenType(",")
  case DOT extends TokenType(".")
  case MINUS extends TokenType("-")
  case PLUS extends TokenType("+")
  case SEMICOLON extends TokenType(";")
  case SLASH extends TokenType("/")
  case STAR extends TokenType("*")

  // One or two character tokens.
  case BANG extends TokenType("!")
  case BANG_EQUAL extends TokenType("!=")
  case EQUAL extends TokenType("=")
  case EQUAL_EQUAL extends TokenType("==")
  case GREATER extends TokenType(">")
  case GREATER_EQUAL extends TokenType(">=")
  case LESS extends TokenType("<")
  case LESS_EQUAL extends TokenType("<=")

  // Literals.
  case IDENTIFIER extends TokenType("IDENTIFIER")
  case STRING extends TokenType("STRING")
  case NUMBER extends TokenType("NUMBER")

  // Keywords.
  case AND extends TokenType("and")
  case CLASS extends TokenType("class")
  case ELSE extends TokenType("else")
  case FALSE extends TokenType("false")
  case FUN extends TokenType("fun")
  case FOR extends TokenType("for")
  case IF extends TokenType("if")
  case NIL extends TokenType("nil")
  case OR extends TokenType("or")
  case PRINT extends TokenType("print")
  case RETURN extends TokenType("return")
  case SUPER extends TokenType("super")
  case THIS extends TokenType("this")
  case TRUE extends TokenType("true")
  case VAR extends TokenType("var")
  case WHILE extends TokenType("while")

  case EOF extends TokenType("EOF")

  def getLexeme(): String =
    lexeme
