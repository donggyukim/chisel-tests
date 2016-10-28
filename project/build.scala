import sbt._
import Keys._

object TestBuild extends Build
{
  val defaultVer = Map(
    "chisel3" -> "3.1-SNAPSHOT",
    "firrtl" -> "1.1-SNAPSHOT",
    "firrtl-interpreter" -> "1.1-SNAPSHOT",
    "chisel-iotesters" -> "1.2-SNAPSHOT"
  )
  lazy val commonSettings = Seq(
     scalaVersion := "2.11.7",
     libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value,
     libraryDependencies ++= (Seq("chisel3", "firrtl", "firrtl-interpreter", "chisel-iotesters") map {
       dep: String => "edu.berkeley.cs" %% dep % sys.props.getOrElse(s"${dep}Version", defaultVer(dep))
     }),
     resolvers ++= Seq(
       Resolver.sonatypeRepo("snapshots"),
       Resolver.sonatypeRepo("releases"),
       Resolver.mavenLocal)
  )
  lazy val subModSettings = commonSettings ++ Seq(
    fork := true,
    javaOptions ++= (Seq("chisel3", "firrtl", "firrtl-interpreter", "chisel-iotesters") map {
      dep: String => s"-D${dep}Version=%s".format(sys.props getOrElse (s"${dep}Version", defaultVer(dep)))
    })
  )
  override lazy val settings = super.settings ++ commonSettings ++ Seq(
    scalaVersion := "2.11.7",
    scalacOptions ++= Seq("-deprecation", "-unchecked"),
    libraryDependencies ++= Seq("org.scalatest" % "scalatest_2.11" % "2.2.4" % Test)
  )
  lazy val tutorial = project settings subModSettings
  lazy val root = (project in file(".")) settings (settings:_*) dependsOn tutorial
} 
