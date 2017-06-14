import akka.actor.{Actor, ActorSystem, Props}

object HelloRemote {

  def main(args: Array[String]): Unit ={
    val system = ActorSystem("HelloRemoteSystem")
    val remoteActor = system.actorOf(Props[RemoteActor], name = "RemoteActor")
    remoteActor ! "The remote actor is alive"
  }

}

class RemoteActor extends Actor {
  override def receive: Receive = {
    case msg: String =>
      println(s"RemoteActor received message $msg")
      sender ! "Hello from the remote actor"
  }
}
