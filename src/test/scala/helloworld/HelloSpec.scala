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

  "Message dispatcher" must {
    "pass message to next" in {
      val dispatcher = system.actorOf(Props(classOf[MessageDispatcher], testActor))
      within(100 millis) {
        dispatcher ! "hello"
        expectMsg("hello")
      }
    }

    "only verify interested message" in {
      val dispatcher = system.actorOf(Props(classOf[MessageDispatcher], testActor))
      within(500 millis) {
        ignoreMsg {
          case msg => msg == "hello"
        }
        dispatcher ! "hello"
        dispatcher ! "world"
        expectMsg("world")

        expectNoMsg
        ignoreNoMsg
      }
    }
  }

  "Message filter" must {
    "filter message then pass to next" in {
      val filter = system.actorOf(Props(classOf[MessageFilter], testActor))
      filter ! "hello"
      filter ! 1
      filter ! "world"
      filter ! 2
      filter ! "!"
      var message = List[Any]()
      receiveWhile(500 millis) {
        case msg => message = msg +: message
      }

      message.reverse should be(List("hello", "world", "!"))
    }
  }

}
