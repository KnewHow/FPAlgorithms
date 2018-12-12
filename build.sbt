scalaVersion := "2.12.6"
name := "FPAlgorithms"
organization := "com.github.knewhow"
version := "1.0"
libraryDependencies ++= Seq(
  "org.typelevel"      %% "cats-core"   % "1.1.0",
  "org.scalatest"      %% "scalatest"   % "3.0.5" % "test",
  "org.typelevel"      %% "cats-core"   % "1.1.0",
  "com.github.knewhow" %% "scalaprop"   % "1.1.0-SNAPSHOT",
  "org.slf4j"          % "slf4j-api"    % "1.7.5",
  "org.slf4j"          % "slf4j-simple" % "1.7.5"
)
scalafmtVersion in ThisBuild := "1.1.0"
scalafmtOnCompile in ThisBuild := true
