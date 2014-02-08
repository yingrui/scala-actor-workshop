
name := "scala-actor-workshop"

version := "1.0"

scalaVersion := "2.10.3"

resolvers += "Open Source China" at "http://maven.oschina.net/content/repositories/"

resolvers += "Maven Central" at "http://repo.maven.apache.org/maven2/"

resolvers += "Akka Repo" at "http://repo.akka.io/releases/"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.0" % "test"

libraryDependencies += "junit" % "junit" % "4.10" % "test"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.10.1" % "test"

libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.10.3"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.2.3",
  "com.typesafe.akka" %% "akka-testkit" % "2.2.3"
)
