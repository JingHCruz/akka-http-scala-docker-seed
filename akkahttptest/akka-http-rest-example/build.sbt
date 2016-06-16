name := """akka-http-rest-example"""

organization := "com.github.pvoznenko"

version := "0.0.1"

scalaVersion := "2.11.8"


libraryDependencies ++= {
  val akkaVersion = "2.4.7"
  val scalaTestVersion = "2.2.6"

  Seq(
    "com.typesafe.akka" %% "akka-http-core" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaVersion,
    "org.scala-lang.modules" %% "scala-java8-compat" % "0.7.0",
    "org.scalactic" %% "scalactic" % scalaTestVersion,
    "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
    "com.amazonaws" % "aws-java-sdk" % "1.11.7"
  )
}

