import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import twirl.sbt.TwirlPlugin._
import com.typesafe.sbteclipse.plugin.EclipsePlugin.EclipseKeys
import com.github.siasia.PluginKeys.port

object MyBuild extends Build {
  val Organization = "com.github.rabitarochan"
  val Name = "scalatra-yabe"
  val Version = "0.1-SNAPSHOT"
  val ScalaVersion = "2.10.1"
  val ScalatraVersion = "2.2.0"
  
  // change jetty port
  def container = config("container")
  val jettyPort = 8081
  
  lazy val project = Project (
    "scalatra-yabe",
    file("."),
    settings = Defaults.defaultSettings ++ ScalatraPlugin.scalatraWithJRebel ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      libraryDependencies ++= Seq(
        // scalatra
        "org.scalatra" %% "scalatra"        % ScalatraVersion,
        "org.scalatra" %% "scalatra-specs2" % ScalatraVersion,

        // container(jetty)
        "org.eclipse.jetty"       % "jetty-webapp"  % "8.1.8.v20121106"     % "container",
        "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar")),

        // others
        "ch.qos.logback" % "logback-classic" % "1.0.6"   % "runtime",
        "com.h2database" % "h2"              % "1.3.171"
      ),
      EclipseKeys.withSource := true,
      
      // change jetty port
      port in container := jettyPort
    ) ++ seq(Twirl.settings: _*)
  )
}