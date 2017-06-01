package idv.jack.test

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by user1 on 2017/6/1.
  */
object Hello4 {
  class MyActor extends Actor {
    override def receive: Receive = {
      case value: String => println("MY ACTOR:" + value)
    }
  }

  class FirstActor extends Actor {
    val child = context.actorOf(Props[MyActor])
    override def receive: Receive = {
      case value: String => child ! "FIRST ACTOR:" + value
    }
  }


  def main(args: Array[String]): Unit ={
    val system = ActorSystem("mysystem")
    val firstActor = system.actorOf(Props[FirstActor], "firstactor")
    firstActor ! "UNIT TEST111"
    system.shutdown()
  }

}
