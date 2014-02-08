package mars.rover

import akka.actor.Actor

case class Coords(x: Int, y: Int, head: String)

class Rover extends Actor {
  val heads = List("N", "E", "S", "W")
  val moves = List(() => y += 1, () => x += 1, () => y -= 1, () => x -= 1)
  var x = 0
  var y = 0
  var head = heads.indexOf("N")

  def receive = {
    case _ =>
  }
}
