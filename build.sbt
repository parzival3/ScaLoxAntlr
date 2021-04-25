val scala3Version = "3.0.0-RC2"
val projectVersion = "0.1.0"


lazy val ast = project.settings (
    version := projectVersion,
    scalaVersion := scala3Version,
)

lazy val antlr = project.dependsOn(ast).aggregate(ast).enablePlugins(Antlr4Plugin).settings (
    Antlr4 / antlr4Version := "4.9.2",
    Antlr4 / antlr4Dependency  := "org.antlr" % "antlr4" % (Antlr4 / antlr4Version).value,
    Antlr4 / antlr4RuntimeDependency := "org.antlr" % "antlr4-runtime" % (Antlr4 / antlr4Version).value,
    Antlr4 / antlr4PackageName := Some("io.github.parzival3.scaloxantlr.ast"),
    Antlr4 / antlr4GenListener := false,
    Antlr4 / antlr4GenVisitor := true,
    version := projectVersion,
    scalaVersion := scala3Version,
)

lazy val loxparser = project.dependsOn(antlr, ast).aggregate(ast, antlr)
  .in(file("."))
  .settings (
    name := "parser-tests",
    version := projectVersion,
    scalaVersion := scala3Version,
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.7",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.7" % "test",
)
