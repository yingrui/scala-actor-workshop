package helloworld

import akka.actor.Actor

class Greeting extends Actor {
  def receive = {
    case name => sender ! s"hello $name"
  }
}
