import de.johoop.jacoco4sbt._
import JacocoPlugin._

name := "Sandbook-Tool"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "com.clever-age" % "play2-elasticsearch" % "0.8.2"
)     

play.Project.playJavaSettings

jacoco.settings

parallelExecution in Test := false

ScctPlugin.instrumentSettings