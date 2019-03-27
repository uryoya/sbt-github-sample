import ReleaseTransformations._

ThisBuild / scalaVersion := "2.12.7"
ThisBuild / organization := "com.example"

lazy val hello = (project in file("."))
  .settings(
    name := "Hello",

    releaseProcess := Seq[ReleaseStep](
      checkSnapshotDependencies,
      inquireVersions,
      runClean,
      setReleaseVersion,
      commitReleaseVersion,
      tagRelease,
      pushChanges,
      releaseStepCommand("githubRelease"),
      setNextVersion,
      commitNextVersion,
      pushChanges
    ),

    ghreleaseRepoOrg  := "uryoya",
    ghreleaseRepoName := "sbt-github-sample",
    ghreleaseNotes    := { _ => "" },
    ghreleaseAssets   := Seq[File](
      // pass
    )
  )
