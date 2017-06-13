import akka.actor.Actor

class ServerActor extends Actor{
  override def receive: Receive = {
    case value: String => println(s"revice the $value value")
    case _ => println("Not support data type")
  }
}
