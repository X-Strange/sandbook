import sbt._
import Keys._
import play.Project._
import com.github.play2war.plugin._

object ApplicationBuild extends Build {

  val appName = "textdat"
  val appVersion = "1.0"

  lazy val scct_settings = Defaults.defaultSettings ++ Seq(ScctPlugin.instrumentSettings: _*)

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    cache,
    "org.seleniumhq.selenium" % "selenium-java" % "2.41.0"
  )

  val main = play.Project(appName, appVersion, appDependencies)
    .settings(Play2WarPlugin.play2WarSettings: _*)

    .settings(
      Play2WarKeys.servletVersion := "3.0"
    )

    .settings(
      testOptions in Test := Nil
    )
  // Add your own project settings here
}