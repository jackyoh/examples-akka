package idv.jack.test

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by user1 on 2017/5/23.
  */
class HelloActor(a: Int) extends Actor {

  override def postStop(): Unit ={
    println("POST STOP BBBBBBBBBBBBBBBBBBBB")
  }
  override def preStart(): Unit = {
    println("PRE START AAAAAAAAAAAAAAAAAAAAA")
  }
  def receive = {
    case "hello" => println("hello test " + a)
    case _       => println("your is ?")
  }
}

object Main extends App {
  val system = ActorSystem("HelloSystem")
  val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
  helloActor ! "hello"
  helloActor ! "test"
  helloActor ! "hello"
}