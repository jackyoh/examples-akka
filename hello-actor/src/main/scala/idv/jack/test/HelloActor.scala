package idv.jack.test

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by user1 on 2017/5/23.
  */
class HelloActor extends Actor {
   def receive = {
     case "hello" => println("hello test")
     case _       => println("your is ?")
   }
}

object Main extends App {
  val system = ActorSystem("HelloSystem")
  val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
  helloActor ! "hello"
  helloActor ! "test"
}