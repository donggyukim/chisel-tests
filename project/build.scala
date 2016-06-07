import sbt._
import Keys._

object GCDBuild extends Build
{
  override lazy val settings = super.settings ++ Seq(
    scalaVersion := "2.11.7",
    scalacOptions ++= Seq("-deprecation", "-unchecked"),
    libraryDependencies ++= Seq(
      "org.scalatest" % "scalatest_2.11" % "2.2.4" % Test)
  )
  lazy val chisel  = project
  lazy val firrtl  = project
  lazy val interp  = project in file("firrtl-interpreter") dependsOn firrtl
  lazy val testers = project dependsOn (chisel, interp)
  lazy val root    = (project in file(".")).settings(settings:_*) dependsOn testers
} 
