organization := "com.xyz"

name := "sample-gw-wrapper"

version := "0.0.1"

crossPaths := false

scalaVersion := "2.11.2"

resolvers += "Eid public repository" at "http://eidrepo:8081/nexus/content/groups/public/"

resolvers += Resolver.mavenLocal

libraryDependencies ++= Seq(
  "gov.tubitak.minder" %% "minder-common" % "0.0.1"
)
