lazy val settings = Seq(
  name := "tasks",
  organization := "com.github.hilton",
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.11.4"
)

lazy val root = (project in file(".")).settings(settings: _*)

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"
