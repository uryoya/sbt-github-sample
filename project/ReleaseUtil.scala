package example.sbt

import sbt._
import scala.util.Try


object ReleaseUtil {
  case class Origin(organization: String, name: String)

  def gitOrigin(base: File): Option[Origin] = {
    val remoteOut: Try[String] = Try{
      sys.process.Process(Seq("git", "ls-remote", "--get-url", "origin"), base).!!
    }

    val remotePattern = """.*/([^/]*)/([^/]*)\.git$""".r

    remoteOut
      .map(_.trim)
      .map {
      case remotePattern(org, name) => Some(Origin(org, name))
      case _ => None
    }.toOption.flatten
  }
}
