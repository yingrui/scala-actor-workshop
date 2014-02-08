package helloworld

import akka.actor.{Props, ActorSystem}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.Matchers._
import org.scalatest.BeforeAndAfterAll
import scala.concurrent.duration._
import org.scalatest.WordSpecLike

class HelloSpec(_system: ActorSystem) extends TestKit(_system)
with ImplicitSender with WordSpecLike with BeforeAndAfterAll {
  def this() = this(ActorSystem("HelloSpec"))

  override def afterAll {
    _system.shutdown()
  }

  "Greeting actor" must {
    "send back messages" in {
      val echo = system.actorOf(Props[Greeting])
      echo ! "world"
      expectMsg("hello world")
    }
  }

}
