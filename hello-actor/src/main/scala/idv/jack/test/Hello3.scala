package idv.jack.test

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

/**
  * Created by user1 on 2017/6/1.
  */
object Hello3 {

  object Greeter {
    case class Greet(peer: ActorRef)
    case object AskName
    case class TellName(name: String)
    def props(name: String, greeting: String) = Props(new Greeter(name, greeting))
  }

  class Greeter(myName: String, greeting: String) extends Actor {
    import Greeter._
    override def receive: Receive = {
      case Greet(peer) => peer ! AskName
      case AskName => sender ! TellName(myName)
      case TellName(name) => println(s"$greeting, $name")
    }
  }

  def main(args: Array[String]): Unit ={
    import Greeter._
    val system = ActorSystem("actor-demo-scala")
    val bob = system.actorOf(props("Bob", "Howya doing"))
    val alice = system.actorOf(props("Alice", "Happy to meet you"))

    bob ! Greet(alice)
    alice ! Greet(bob)
    Thread sleep 1000
    system.shutdown()


  }

}
