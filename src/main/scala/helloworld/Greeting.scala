package helloworld

import akka.actor.{ActorRef, Actor}

class Greeting extends Actor {
  def receive = {
    case name => sender ! s"hello $name"
  }
}

class MessageDispatcher(next: ActorRef) extends Actor {
  def receive = {
    case message => next ! message
  }
}

class MessageFilter(next: ActorRef) extends Actor {
  def receive = {
    case message: String => next ! message
    case _ =>
  }
}