import ReleaseTransformations._
import example.sbt.ReleaseUtil

ThisBuild / scalaVersion := "2.12.7"
ThisBuild / organization := "com.example"

lazy val gitOrigin = settingKey[Option[ReleaseUtil.Origin]]("git remote repository")

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

    gitOrigin         := ReleaseUtil.gitOrigin(baseDirectory.value),
    ghreleaseRepoOrg  := gitOrigin.value.map(_.organization).getOrElse(organization.value),
    ghreleaseRepoName := gitOrigin.value.map(_.name).getOrElse(name.value),
    ghreleaseNotes    := { _ => "" },
    ghreleaseAssets   := Seq[File](
      new File((assemblyOutputPath in assembly).value.getPath)
    )
  )
