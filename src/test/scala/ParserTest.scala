package io.github.parzival3.scaloxantlr.test

import io.github.parzival3.scaloxantlr.ast._
import org.scalatest._
import flatspec._
import matchers._
import org.scalatest.funsuite.AnyFunSuite
import scala.io.Source

import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.tree.*


class ParserTest extends AnyFunSuite with should.Matchers {
  val stream = Source.fromResource( "test_antlr4_parser.lox")
  val input: CharStream= CharStreams.fromReader(stream.reader);
  val lexer: LoxLexer = LoxLexer(input)
  val tokens: CommonTokenStream  = CommonTokenStream(lexer)
  val parser: LoxParser =  LoxParser(tokens);

  test(s"Test correct parser") {
      val tree: ParseTree = parser.compilationUnit()
      println(tree.toStringTree(parser))
  }

}
