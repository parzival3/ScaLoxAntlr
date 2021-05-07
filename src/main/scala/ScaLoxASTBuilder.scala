//remove if not needed
package io.github.parzival3.scaloxantlr.builder
import io.github.parzival3.scaloxantlr.ast._
import io.github.parzival3.scaloxantlr.antlr._
import io.github.parzival3.scaloxantlr.antlr._
import org.antlr.v4.runtime.tree.ParseTree

import scala.collection.JavaConverters._

class AstBuilderVisitor extends LoxParserBaseVisitor[Stmt | Expr] {

  extension[T <: ParseTree] (l : java.util.List[T])
    def getStmts: List[Stmt] = {
      l.asScala.flatMap {
        visit(_) match
          case s: Stmt => Some(s)
          case _ => None
      }.toList
    }

  override def visitCuStatement(ctx: LoxParser.CuStatementContext): Stmt | Expr = {
    visitChildren(ctx)
  }

  override def visitCuVarDeclaration(ctx: LoxParser.CuVarDeclarationContext): Stmt | Expr = {
    Variable(TokenInstance(TokenType.IDENTIFIER, ctx.IDENTIFIER().getText, Some(visit(ctx.expression()))))
  }

  override def visitCuFunctionDeclaration(ctx: LoxParser.CuFunctionDeclarationContext): Stmt | Expr =  {
    visitChildren(ctx)
  }

  override def visitVariableDeclaration(ctx: LoxParser.VariableDeclarationContext): Stmt | Expr = {
    visitChildren(ctx)
  }

  override def visitCuClassDeclaration(ctx: LoxParser.CuClassDeclarationContext): Stmt | Expr = {
    val superClass = if ctx.sup != null then
        Some(TokenInstance(TokenType.IDENTIFIER, ctx.IDENTIFIER(1).getText, None))
      else
        None

    LoxClass(TokenInstance(TokenType.IDENTIFIER, ctx.IDENTIFIER(0).getText, None), superClass, ctx.function.getStmts)
  }

  override def visitStatementBlock(ctx: LoxParser.StatementBlockContext): Stmt | Expr = {
    Block(ctx.compilationUnit().getStmts)
  }

  override def visitIfStatement(ctx: LoxParser.IfStatementContext): Stmt | Expr = {
    val elseB: Option[Stmt] = if ctx.statement().size() == 2 then
      visit(ctx.statement(1)) match
        case s : Stmt => Some(s)
        case _ => None
    else
      None

    If(visit(ctx.expression()).asInstanceOf[Expr], visit(ctx.statement(0)).asInstanceOf[Stmt], elseB)
  }

  override def visitForStatement(ctx: LoxParser.ForStatementContext): Stmt | Expr = visitChildren(ctx)

  override def visitPrintStatement(ctx: LoxParser.PrintStatementContext): Stmt | Expr =  {
    Print(visit(ctx.expression()).asInstanceOf[Expr])
  }

  override def visitExpressionStatement(
      ctx: LoxParser.ExpressionStatementContext): Stmt | Expr =  Expression(visit(ctx.expression()).asInstanceOf[Expr])

  override def visitBinary(ctx: LoxParser.BinaryContext): Stmt | Expr = {
    val opType = TokenInstance(TokenInstance.fromLexeme(ctx.bop.getText), ctx.bop.getText, None)
    Binary(visit(ctx.expression(0)).asInstanceOf[Expr], opType, visit(ctx.expression(1)).asInstanceOf[Expr])
  }

  override def visitAssignment(ctx: LoxParser.AssignmentContext): Stmt | Expr = visitChildren(ctx)

  override def visitPrimaryE(ctx: LoxParser.PrimaryEContext): Stmt | Expr =
    visitChildren(ctx)

  override def visitCallE(ctx: LoxParser.CallEContext): Stmt | Expr =  visitChildren(ctx)

  override def visitLogic(ctx: LoxParser.LogicContext): Stmt | Expr = {
    val instb = TokenInstance(TokenInstance.fromLexeme(ctx.op.getText), ctx.op.getText, None)
    Logical(visit(ctx.expression(0)).asInstanceOf[Expr], instb, visit(ctx.expression(1)).asInstanceOf[Expr])
  }

  override def visitUnary(ctx: LoxParser.UnaryContext): Stmt | Expr =  visitChildren(ctx)


  /**
    * {@inheritDoc}
    *
    * <p>The default implementation returns the result of calling
    * {@link #visitChildren} on {@code ctx}.</p>
    */
  override def visitCall(ctx: LoxParser.CallContext): Stmt | Expr =  visitChildren(ctx)

  /**
    * {@inheritDoc}
    *
    * <p>The default implementation returns the result of calling
    * {@link #visitChildren} on {@code ctx}.</p>
    */
  override def visitParenExpression(ctx: LoxParser.ParenExpressionContext): Stmt | Expr =
    visitChildren(ctx)

  /**
    * {@inheritDoc}
    *
    * <p>The default implementation returns the result of calling
    * {@link #visitChildren} on {@code ctx}.</p>
    */
  override def visitBoolExpression(ctx: LoxParser.BoolExpressionContext): Stmt | Expr =
    visitChildren(ctx)

  /**
    * {@inheritDoc}
    *
    * <p>The default implementation returns the result of calling
    * {@link #visitChildren} on {@code ctx}.</p>
    */
  override def visitNilExpression(ctx: LoxParser.NilExpressionContext): Stmt | Expr =
    visitChildren(ctx)

  /**
    * {@inheritDoc}
    *
    * <p>The default implementation returns the result of calling
    * {@link #visitChildren} on {@code ctx}.</p>
    */
  override def visitThisExpression(ctx: LoxParser.ThisExpressionContext): Stmt | Expr =
    visitChildren(ctx)

  /**
    * {@inheritDoc}
    *
    * <p>The default implementation returns the result of calling
    * {@link #visitChildren} on {@code ctx}.</p>
    */
  override def visitNumberExpression(
      ctx: LoxParser.NumberExpressionContext): Stmt | Expr =  visitChildren(ctx)

  /**
    * {@inheritDoc}
    *
    * <p>The default implementation returns the result of calling
    * {@link #visitChildren} on {@code ctx}.</p>
    */
  override def visitStringExpression(
      ctx: LoxParser.StringExpressionContext): Stmt | Expr =  visitChildren(ctx)

  /**
    * {@inheritDoc}
    *
    * <p>The default implementation returns the result of calling
    * {@link #visitChildren} on {@code ctx}.</p>
    */
  override def visitIndentifierExpression(
      ctx: LoxParser.IndentifierExpressionContext): Stmt | Expr =  visitChildren(ctx)

  /**
    * {@inheritDoc}
    *
    * <p>The default implementation returns the result of calling
    * {@link #visitChildren} on {@code ctx}.</p>
    */
  override def visitSuperExpression(ctx: LoxParser.SuperExpressionContext): Stmt | Expr =
    visitChildren(ctx)

  override def visitFunction(ctx: LoxParser.FunctionContext): Stmt | Expr = {
    val parameters = ctx.IDENTIFIER().asScala.tail.map(t => TokenInstance(TokenType.IDENTIFIER, t.getText, None)).toList
    val statements = ctx.compilationUnit().asScala.flatMap{ x => visit(x) match
      case a : Stmt => Some(a)
      case _ => None
    }

    Function(TokenInstance(TokenType.FUN, ctx.name.getText, None), parameters, statements.toList)
    visitChildren(ctx)
  }
}
