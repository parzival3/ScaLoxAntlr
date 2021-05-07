package io.github.parzival3.scaloxantlr.test

import io.github.parzival3.scaloxantlr.ast._
import io.github.parzival3.scaloxantlr.antlr._
import io.github.parzival3.scaloxantlr.builder._
import org.scalatest._
import flatspec._
import matchers._
import org.scalatest.funsuite.AnyFunSuite
import scala.io.Source

import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._


class ParserTest extends AnyFunSuite with should.Matchers {

  def getParserForResource(fileName: String): LoxParser = {
    val stream = Source.fromResource(fileName)
    val input: CharStream= CharStreams.fromReader(stream.reader);
    val lexer: LoxLexer = LoxLexer(input)
    val tokens: CommonTokenStream  = CommonTokenStream(lexer)
    LoxParser(tokens);
  }

  test(s"Test correct parser") {
      val parser = getParserForResource("test_antlr4_parser.lox")
      val tree = parser.compilationUnit()
      println(tree.toStringTree(parser))
  }

  test(s"Test correct class field assignment") {
      val parser  =  getParserForResource("field/get_and_set_method.lox")
      val tree = parser.compilationUnit()
      val astBuilder = AstBuilderVisitor()
      val ast = astBuilder.visit(tree)
      println(ast)
      println(tree.toStringTree(parser))
  }
}
