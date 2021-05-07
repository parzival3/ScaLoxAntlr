package io.github.parzival3.scaloxantlr.ast

sealed trait Expr
case class Unary(token: TokenInstance, expr: Expr) extends Expr
case class Binary(left: Expr, token: TokenInstance, right: Expr) extends Expr
case class Call(callee: Expr, arguments: List[Expr]) extends Expr
case class Grouping(expr: Expr) extends Expr
case class Literal(value: TokenInstance) extends Expr
case class Assign(value: TokenInstance, expr: Expr) extends Expr
case class Variable(name: TokenInstance) extends Expr
case class Logical(left: Expr, token: TokenInstance, right: Expr) extends Expr
case class Set(obj: Expr, name: TokenInstance, value: Expr) extends Expr
case class Get(obj: Expr, name: TokenInstance) extends Expr
case class This(name: TokenInstance) extends Expr
case class Super(keyword: TokenInstance, method: TokenInstance) extends Expr
