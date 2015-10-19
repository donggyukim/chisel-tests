import sbt._
import Keys._

object GCDBuild extends Build
{
  override lazy val settings = super.settings ++ Seq(
    scalaVersion := "2.11.6",
    scalacOptions ++= Seq("-deprecation", "-unchecked")
  )
  lazy val chisel = Project("chisel", base=file("chisel"))
  lazy val root   = Project("tests",  base=file(".")).dependsOn(chisel)
} 
