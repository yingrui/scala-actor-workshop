package mars.rover

import org.scalatest.Matchers
import org.scalatest.{BeforeAndAfterAll, WordSpecLike}
import akka.actor.{Props, ActorSystem}
import akka.testkit.{ImplicitSender, TestKit}
import scala.concurrent.duration._

class RoverSpec(_system: ActorSystem) extends TestKit(_system)
with ImplicitSender with WordSpecLike with Matchers with BeforeAndAfterAll {
  def this() = this(ActorSystem("RoverSpec"))

  override def afterAll {
    _system.shutdown()
  }

  "Rover" should {

    "return coordinates when deployed on terrain" in {
      val rover = system.actorOf(Props[Rover])
      within(500 millis) {
        rover ! Coords(1, 2, "W")
        rover ! "Location"
        expectMsg(Coords(1, 2, "W"))
      }
    }

    "left rotate" in {
      val rover = system.actorOf(Props[Rover])
      rover ! Coords(1, 2, "N")
      rover ! "L"
      rover ! "Location"
      expectMsg(Coords(1, 2, "W"))
      rover ! "L"
      rover ! "Location"
      expectMsg(Coords(1, 2, "S"))
      rover ! "L"
      rover ! "Location"
      expectMsg(Coords(1, 2, "E"))
      rover ! "L"
      rover ! "Location"
      expectMsg(Coords(1, 2, "N"))
    }

    "right rotate" in {
      val rover = system.actorOf(Props[Rover])
      rover ! Coords(1, 2, "N")
      rover ! "R"
      rover ! "Location"
      expectMsg(Coords(1, 2, "E"))
      rover ! "R"
      rover ! "Location"
      expectMsg(Coords(1, 2, "S"))
      rover ! "R"
      rover ! "Location"
      expectMsg(Coords(1, 2, "W"))
      rover ! "R"
      rover ! "Location"
      expectMsg(Coords(1, 2, "N"))
    }

    "move forward" in {
      val rover = system.actorOf(Props[Rover])
      rover ! Coords(1, 2, "N")
      rover ! "M"
      rover ! "Location"
      expectMsg(Coords(1, 3, "N"))
      rover ! "R"
      rover ! "M"
      rover ! "Location"
      expectMsg(Coords(2, 3, "E"))
      rover ! "R"
      rover ! "M"
      rover ! "Location"
      expectMsg(Coords(2, 2, "S"))
      rover ! "R"
      rover ! "M"
      rover ! "Location"
      expectMsg(Coords(1, 2, "W"))
    }

    "move to coordinate (1 3 N)" in {
      val rover = system.actorOf(Props[Rover])
      rover ! Coords(1, 2, "N")
      rover ! "L"
      rover ! "M"
      rover ! "L"
      rover ! "M"
      rover ! "L"
      rover ! "M"
      rover ! "L"
      rover ! "M"
      rover ! "M"
      rover ! "Location"
      expectMsg(Coords(1, 3, "N"))
    }

    "move to coordinate (5 1 E)" in {
      val rover = system.actorOf(Props[Rover])
      rover ! Coords(3, 3, "E")
      rover ! "M"
      rover ! "M"
      rover ! "R"
      rover ! "M"
      rover ! "M"
      rover ! "R"
      rover ! "M"
      rover ! "R"
      rover ! "R"
      rover ! "M"
      rover ! "Location"
      expectMsg(Coords(5, 1, "E"))
    }
  }
}
