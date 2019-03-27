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
      pushChanges, // githubRelease を実行する前に変更をリモートに送信する必要がある
      releaseStepCommand("assembly"),
      releaseStepCommand("githubRelease"), // リリースの作成
      setNextVersion,
      commitNextVersion,
      pushChanges // 再度送信
    ),

    ghreleaseRepoOrg  := "uryoya",
    ghreleaseRepoName := "sbt-github-sample",
    ghreleaseNotes    := { _ => "" },
    ghreleaseAssets   := Seq[File](
      new java.io.File("target/" + (assemblyJarName in assembly).value)
    )
  )
