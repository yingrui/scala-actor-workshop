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
      within(500 millis){
        rover ! Coords(1, 2, "W")
        rover ! "Location"
        expectMsg(Coords(1, 2, "W"))
      }
    }
  }
}
