organization := "com.xyz"

name := "sample-gw-wrapper"

version := "0.0.1"

resolvers += "Eid public repository" at "http://eidrepo:8081/nexus/content/groups/public/"

resolvers += Resolver.mavenLocal

crossPaths := false

javacOptions in (Compile, compile) ++= Seq("-source", "1.7", "-target", "1.7")

javacOptions in (doc) ++= Seq("-source", "1.7")

libraryDependencies ++= Seq(
  "gov.tubitak.minder" % "minder-common" % "0.0.1"
)
