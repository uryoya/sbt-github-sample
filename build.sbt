import ReleaseTransformations._

ThisBuild / scalaVersion := "2.12.7"
ThisBuild / organization := "com.example"
ThisBuild / version      := "0.1.4"

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
      setNextVersion,
      commitNextVersion,
      pushChanges
    ),

    ghreleaseRepoOrg  := "uryoya",
    ghreleaseRepoName := "sbt-github-sample",
    ghreleaseNotes    := { _ => "" },
  )
