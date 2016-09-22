name := "AkkaTest"

version := "1.0"

scalaVersion := "2.11.0"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"


libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.15"

libraryDependencies += "com.typesafe.akka" % "akka-remote_2.11" % "2.3.15"

libraryDependencies += "org.scalaz" % "scalaz-core_2.11" % "7.1.0"

libraryDependencies += "org.scalaz" % "scalaz-effect_2.11" % "7.1.0"
