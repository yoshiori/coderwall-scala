package org.yoshiori

import net.liftweb.json._

case class Coderwall(username: String, name: String, location: String, endorsements: Int, badges: List[Badge])

case class Badge(name: String, description: String, badge: String)

object Coderwall {
  def get(username: String): Coderwall = {
    val json = scala.io.Source.fromURL(
      "http://coderwall.com/%s.json".format(username)).getLines.mkString
    implicit val formats = DefaultFormats
    parse(json).extract[Coderwall]
  }
}


object Main {
  def main(args: Array[String]) {
    args match {
      case Array(username) =>
        val c = Coderwall.get(username)
        println(c)
      case _ => println("Usage: Coderwal.scala USERNAME")
    }
  }
}
