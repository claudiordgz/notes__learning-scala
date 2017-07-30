name := "learning-sbt"

version := "1.0"

scalaVersion := "2.12.3"

// This comes from the specs2 website
// The one from SBT in Action didn't work
val testingLibrary = Seq("org.specs2" %% "specs2-core" % "3.9.1" % "test")
libraryDependencies ++= testingLibrary

def LearningSbtProject(name: String): Project = Project(name, file(name)).settings(
    version := "1.0",
    organization := "com.claudiordgz.learning-sbt",
    libraryDependencies ++= testingLibrary
  )

// console to test your code in scala inside sbt
// test to run your tests
// reload when you make changes to build.sbt
/* ~test
    This is the best command of your life
    It's like watch for your tests
 */

// Fetch github commit and slap it into a variable
// run `show gitHeadCommitSha` to see it in SBT
val gitHeadCommitSha = taskKey[String] (
  "Determines the current git commit SHA"
)
gitHeadCommitSha in ThisBuild := Process("git rev-parse HEAD").lines.head

// Then use that variable to slap it into a version.properties file
// run `show makeVersionProperties` to get the file in which the version is saved on compile time
val makeVersionProperties = taskKey[Seq[File]](
  "Makes a version.properties file."
)

// Subprojects!
lazy val common: Project = LearningSbtProject("common").settings(
  makeVersionProperties := {
    val propFile = (resourceManaged in Compile).value / "version.properties"
    val content = "version=%s" format gitHeadCommitSha.value
    IO.write(propFile, content)
    Seq(propFile)
  }
)

// You can see all projects using `projects` in sbt
// Additionally!
// You can run specific project test using the following: `projectName/test`
// So in the case of the common project it would be: `common/test`

// MORE SUBPROJECTS
lazy val subprojectOne: Project = LearningSbtProject("subprojectOne").dependsOn(common).settings()
lazy val subprojectTwo: Project = LearningSbtProject("subprojectTwo").dependsOn(common).settings()
