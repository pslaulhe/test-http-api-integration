ThisBuild / scalaVersion     := "2.13.12"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "com.example"

val akkaVersion = "2.5.26"
val akkaHttpVersion = "10.1.11"

lazy val root = (project in file("."))
  .settings(
    name := "test-http-api-integration",
    libraryDependencies ++= Seq(
      // akka streams
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
      // akka http
      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      // testing
      "org.scalactic" %% "scalactic" % "3.2.18",
      "org.scalatest" %% "scalatest" % "3.2.18" % "test",
      "org.assertj" % "assertj-core" % "3.25.2" % "test"
    ),
    resolvers += Resolver.url(
      "MAVEN_CENTRAL",
      url("https://repo.maven.apache.org/maven2"))(
      Patterns("[organisation]/[module]/[revision]/[artifact]-[revision](-[classifier]).[ext]") )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
