val versions = new {
  val cats = "2.0.0-RC1"
  val catsEffect = cats
  val http4s = "0.21.0-M4"
  val logback = "1.2.3"
  val slf4j = "1.7.26"
  val commonsIo = "2.6"
  val pureconfig = "0.11.1"
  val refined = "0.9.8"
  val guava = "28.0-jre"
  val scala = "2.13.0"
  val scalaLogging = "3.9.2"
  val scalaTest = "3.0.8"
  val scalaCheck = "1.14.0"
}

val dependencies = {
  import versions._
  new {
    val `cats-core` = "org.typelevel" %% "cats-core" % cats
    val `cats-effect` = "org.typelevel" %% "cats-effect" % catsEffect
    val `http4s-blaze-server` = "org.http4s" %% "http4s-blaze-server" % http4s
    val `http4s-blaze-client` = "org.http4s" %% "http4s-blaze-client" % http4s
    val `http4s-circe` = "org.http4s" %% "http4s-circe" % http4s
    val `http4s-dsl` = "org.http4s" %% "http4s-dsl" % http4s
    val `commons-io` = "commons-io" % "commons-io" % commonsIo
    val pureconfig = "com.github.pureconfig" %% "pureconfig" % versions.pureconfig
    val refined = "eu.timepit" %% "refined" % versions.refined
    val guava = "com.google.guava" % "guava" % versions.guava
    val `logback-classic` = "ch.qos.logback" % "logback-classic" % logback
    val `scala-logging` = "com.typesafe.scala-logging" %% "scala-logging" % scalaLogging
    val `slf4j-api` = "org.slf4j" % "slf4j-api" % slf4j
    val `scala-test` = "org.scalatest" %% "scalatest" % scalaTest % "test"
    val scalacheck = "org.scalacheck" %% "scalacheck" % scalaCheck % "test"
  }
}

val commonSettings = Seq(
  organization := "com.hastybox.dradizz.hub",
  version := "0.0.1-SNAPSHOT",
  scalaVersion := versions.scala,
  scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8"),
  dependencyOverrides ++= {
    import dependencies._
    Seq(
    `slf4j-api`
    )
  }
)

lazy val core = Project(
  id = "dradizz-hub-core",
  base = file("core")
)
  .settings(
    libraryDependencies ++= {
      import dependencies._
      Seq(
      )
    }
  )
  .settings(commonSettings: _*)

lazy val movies = Project(
  id = "dradizz-hub-movies",
  base = file("movies")
)
  .settings(
    libraryDependencies ++= {
      import dependencies._
      Seq(
      )
    }
  )
  .settings(commonSettings: _*)
  .dependsOn(core)

lazy val tv = Project(
  id = "dradizz-hub-tv",
  base = file("tv")
)
  .settings(
    libraryDependencies ++= {
      import dependencies._
      Seq(
      )
    }
  )
  .settings(commonSettings: _*)
  .dependsOn(core)

lazy val app = Project(
  id = "dradizz-hub-app",
  base = file("app")
)
  .settings(
    libraryDependencies ++= {
      import dependencies._
      Seq(
      )
    },
    crossPaths := false
  )
  .settings(commonSettings: _*)
  .enablePlugins(JavaAppPackaging, UniversalDeployPlugin)
  .dependsOn(movies, tv)


lazy val `dradizz-hub` = (project in file("."))
  .settings(commonSettings: _*)
  .aggregate(core, movies, tv, app)

