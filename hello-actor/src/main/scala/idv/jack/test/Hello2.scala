package idv.jack.test

import akka.actor.{Actor, ActorSystem, Props}


object Hello2 {
  case class Greeting(greet: String)
  case class Greet(name: String)

  class Hello extends Actor {
    var greeting = ""
    override def receive: Receive = {
      case Greeting(greet) => greeting = greet
      case Greet(name) => println(s"$greeting $name")
    }
  }

  def main(args: Array[String]): Unit ={
    val system = ActorSystem("actor-demo-scala")
    val hello = system.actorOf(Props[Hello], "hello")
    hello ! Greeting("Hello")
    hello! Greet("Bob")
    hello ! Greet("Alice")
    hello ! Greeting("Hola")
    hello ! Greet("Alice")
    hello ! Greet("Bob")

    Thread sleep 1000
    system.shutdown()

  }

}
