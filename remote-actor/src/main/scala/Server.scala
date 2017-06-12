import akka.actor.Actor
import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import akka.actor.Props

case class AkkaMessage(message: Any)
case class Response(response: Any)

class Server extends Actor {
  override def receive: Receive = {
    case msg: AkkaMessage => {
      println("server recvice message:" + msg.message)
      sender ! Response("response_" + msg.message)
    }
    case _ => println("server not support service type ...")
  }
}
object Server {
  def main(args: Array[String]): Unit = {

    val serverSystem = ActorSystem("actor-system", ConfigFactory.parseString(
      """
       akka {
         actor {
            provider = "akka.remote.RemoteActorRefProvider"
         }
         remote {
            enabled-transports = ["akka.remote.netty.tcp"]
            netty.tcp {
               hostname = "10.1.3.100"
               port = 2555
            }
         }
       }
      """))

    serverSystem.actorOf(Props[Server], "server")
  }

}
