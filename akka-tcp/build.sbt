name := "akka-tcp"

version := "1.0"

scalaVersion := "2.11.0"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"


libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.0"

libraryDependencies += "com.typesafe.akka" %% "akka-remote" % "2.5.0"

libraryDependencies += "com.typesafe.akka" %% "akka-http-core" % "10.0.7"

libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.0.7"

libraryDependencies += "com.typesafe.akka" %% "akka-http-testkit" % "10.0.7"

libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.7"

libraryDependencies += "com.typesafe.play" %% "play" % "2.4.11"
