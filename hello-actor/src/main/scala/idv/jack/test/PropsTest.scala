package idv.jack.test

import akka.actor.{ActorSystem, PoisonPill, Props}



object PropsTest {

  def main(args: Array[String]): Unit ={
    val system = ActorSystem("HelloSystem")

    //val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
    val valueClassProp = Props(classOf[HelloActor], 5)

    val helloActor = system.actorOf(valueClassProp, name = "helloactor")

    helloActor ! "hello"
    helloActor ! "test"
    helloActor ! "hello"
    helloActor ! "hello"
   // helloActor ! PoisonPill
    //system.stop(helloActor)
    //system.shutdown()

  }

}
