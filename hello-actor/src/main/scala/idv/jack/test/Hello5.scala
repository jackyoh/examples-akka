package idv.jack.test

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by user1 on 2017/6/2.
  */
object Hello5 {
  class MyActor extends Actor {
    override def receive: Receive = {
      case name: String => println("Actor receive the " + name + " string")
    }
  }

  def main(args: Array[String]): Unit ={
    val system = ActorSystem("actor-system-test")
    val myActor = system.actorOf(Props[MyActor], "myActor")
    println(myActor.getClass())

    myActor ! "HELLO WORLD"
    system.shutdown()

  }

}
