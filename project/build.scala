import sbt._
import Keys._

object GCDBuild extends Build
{
  lazy val root =     Project("tests",  base=file(".")).dependsOn(chisel)
  lazy val chisel =   Project("chisel", base=file("chisel"))
} 
