package idv.jack.test

import akka.actor.Actor
import akka.event.Logging

/**
  * Created by user1 on 2017/5/25.
  */
class MyActor extends Actor{
  val log = Logging(context.system, this)
  override def receive: Receive = {
    case "test" => log.info("received test")
    case _ => log.info("received unknown message")

  }
}
