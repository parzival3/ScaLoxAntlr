package io.github.parzival3.scaloxantlr.ast

case class TokenInstance(tType: TokenType, lexeme: String, literal: Option[AnyRef])

object TokenInstance:
    def fromLexeme(lexeme: String): TokenType =
        TokenType.values.find(t => t.getLexeme() == lexeme).get
