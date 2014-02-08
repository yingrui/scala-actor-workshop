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
    case Coords(i, j, h) => x = i; y = j; head = heads.indexOf(h)
    case "Location" => sender ! Coords(x, y, heads(head))
    case "L" => if (head - 1 < 0) head = 3 else head -= 1
  }
}
