organization := "gov.tubitak.minder.test"

name := "xml-generator"

version := "0.0.1"

resolvers += Resolver.mavenLocal

crossPaths := false

javacOptions in (Compile, compile) ++= Seq("-source", "1.7", "-target", "1.7")

javacOptions in (doc) ++= Seq("-source", "1.7")

libraryDependencies ++= Seq(
  "gov.tubitak.minder" % "minder-common" % "0.0.3",
  "junit" % "junit" % "4.12"
)


publishTo := Some("eid releases" at "http://eidrepo:8081/nexus/content/repositories/releases")

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")


