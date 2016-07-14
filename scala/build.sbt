lazy val settings = Seq(
  name := "tasks",
  organization := "com.codurance.training",
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.11.8"
)

lazy val root = (project in file(".")).settings(settings: _*)

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % Test
