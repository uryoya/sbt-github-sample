ThisBuild / scalaVersion := "2.12.7"
ThisBuild / organization := "com.example"
ThisBuild / version      := "0.1.2"

lazy val hello = (project in file("."))
  .settings(
    name := "Hello",
    ghreleaseRepoOrg  := "uryoya",
    ghreleaseRepoName := "sbt-github-sample",
    ghreleaseNotes    := { _ => "" },
  )
