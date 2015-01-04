name := "nwoator"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "ws.securesocial" %% "securesocial" % "2.1.4",   
  "org.reactivemongo" %% "play2-reactivemongo" % "0.10.5.0.akka22"
)     

play.Project.playScalaSettings

resolvers += Resolver.sonatypeRepo("releases")
