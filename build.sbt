import slick.codegen.SourceCodeGenerator
import slick.{ model => m }

lazy val root = (project in file("."))
  .enablePlugins(CodegenPlugin)
  .enablePlugins(PlayScala)
  .settings(
    name := """quiz-engine""",
    version := "1.0",
    scalaVersion := "2.12.9",
    libraryDependencies ++= Seq(
      guice,
      "com.typesafe.play" %% "play-slick" % "5.0.0",
      "com.typesafe.slick" %% "slick" % "3.3.2",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.3.2",
      "org.postgresql" % "postgresql" % "42.2.6",
      specs2 % Test,
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )


slickCodegenDatabaseUrl := "jdbc:postgresql://localhost:5432/quizzes"
slickCodegenDatabaseUser := "quizzes"
slickCodegenDatabasePassword := "quizzes"
slickCodegenOutputPackage := "quizzes.slick"
slickCodegenExcludedTables := Seq("flyway_schema_history")

slickCodegenCodeGenerator := { (model: m.Model) =>
  val optionColumns = Set("id", "createdAt", "updatedAt")
  new SourceCodeGenerator(model) {
    override def Table = new Table(_) {
      override def Column = new Column(_) {
        override def asOption = optionColumns.contains(rawName)
      }
    }
  }
}

sourceGenerators in Compile += slickCodegen

mainClass in assembly := Some("play.core.server.ProdServerStart")
fullClasspath in assembly += Attributed.blank(PlayKeys.playPackageAssets.value)

assemblyMergeStrategy in assembly := {
  case manifest if manifest.contains("MANIFEST.MF") =>
    MergeStrategy.discard
  case referenceOverrides if referenceOverrides.contains("reference-overrides.conf") =>
    MergeStrategy.concat
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

assemblyJarName in assembly := "quizzes.jar"
