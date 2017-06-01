package idv.jack.test

import akka.actor.{Actor, ActorSystem, Props}


class Hello extends Actor {
  override def receive: Receive = {
    case name: String => println(name)
  }
}

object Hello1 extends App {
  val system1 = ActorSystem("actor-demo-scala1")
  val hello1 = system1.actorOf(Props[Hello])

  val system2 = ActorSystem("actor-demo-scala1")
  val hello2 = system2.actorOf(Props[Hello])

  val system3 = ActorSystem("actor-demo-scala1")
  val hello3 = system3.actorOf(Props[Hello])

  val system4 = ActorSystem("actor-demo-scala1")
  val hello4 = system4.actorOf(Props[Hello])

  val system5 = ActorSystem("actor-demo-scala1")
  val hello5 = system5.actorOf(Props[Hello])

  hello1 ! "tomcat"
  hello2 ! "java"
  hello3 ! "scala"
  hello4 ! "akka"
  hello5 ! "kafka"

  /*system1.shutdown()
  system2.shutdown()
  system3.shutdown()
  system4.shutdown()
  system5.shutdown()
  */
  /*system1.shutdown()
  system2.shutdown()
  system3.shutdown()
  system4.shutdown()
  system5.shutdown()*/

}
