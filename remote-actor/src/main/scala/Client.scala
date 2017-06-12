import akka.actor.Actor
import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import akka.actor.Props
import akka.actor.ActorSelection
import scala.concurrent.Await
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent._
import scala.concurrent.Future
import Server._



class Client extends Actor {
  var remoteActor: ActorSelection = null
  var localActor: akka.actor.ActorRef = null

  @throws[Exception](classOf[Exception])
  override def preStart(): Unit = {
    remoteActor = context.actorSelection("akka.tcp://actor-system@10.1.3.100:2555/user/server")
    println("Remote server address:" + remoteActor)
  }

  override def receive: Receive = {
    case msg: AkkaMessage => {
      println("client send message:" + msg)
      this.localActor = sender()
      remoteActor ! msg
    }
    case res: Response => {
      localActor ! res
    }
    case _ => println("Client not support message type...")
  }
}
object Client {
  def main(args: Array[String]): Unit = {
    val clientSystem = ActorSystem("ClientSystem", ConfigFactory.parseString(
      """
        akka {
           actor {
             provider = "akka.remote.RemoteActorRefProvider"
           }
        }
      """))

    var client = clientSystem.actorOf(Props[Client])
    var msgs = Array[AkkaMessage](AkkaMessage("message1"), AkkaMessage("message2"), AkkaMessage("message3"), AkkaMessage("message4"))
    implicit val timeout = Timeout(3 seconds)

    msgs.foreach { x =>
      val future = client ? x
      val result = Await.result(future, timeout.duration).asInstanceOf[Response]
      println("recvice feedback is:" + result)
    }

    clientSystem.terminate()

  }


}
