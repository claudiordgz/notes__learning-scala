name := "learning-sbt"

version := "1.0"

scalaVersion := "2.12.3"


// This comes from the specs2 website
// The one from SBT in Action didn't work
libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.9.1" % "test")

// console to test your code in scala inside sbt
// test to run your tests
// reload when you make changes to build.sbt
/* ~test
    This is the best command of your life
    It's like watch for your tests
 */

// github shit
val gitHeadCommitSha = taskKey[String] (
  "Determines the current git commit SHA"
)
gitHeadCommitSha := Process("git rev-parse HEAD").lines.head

val makeVersionProperties = taskKey[Seq[File]](
  "Makes a version.properties file."
)
makeVersionProperties := {
  val propFile =
    new File((resourceManaged in Compile).value, "version.properties")
  val content = "version=%s" format gitHeadCommitSha.value
  IO.write(propFile, content)
  Seq(propFile)
}

resourceGenerators in Compile +=
  makeVersionProperties