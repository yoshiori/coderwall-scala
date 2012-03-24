package org.yoshiori

import net.liftweb.json._

case class Coderwall(username:String, name:String,location:String,endorsements:Int, badges: List[Badge])
case class Badge(name:String,description:String,badge:String)

object Coderwall {
  def get(username:String):Coderwall = {
    val json = scala.io.Source.fromURL(
      "http://coderwall.com/%s.json".format(username)).getLines.mkString
    implicit val formats = DefaultFormats
    parse(json).extract[Coderwall]
  }
}


object Main {
  def main(args: Array[String]) {
    if( args.size < 1){
      println("Usage: Coderwal.scala USERNAME")
    } else {
      val username = args(0)
      val c = Coderwall.get(username)
      println(c)
    }
  }
}
