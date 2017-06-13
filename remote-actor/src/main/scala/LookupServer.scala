import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object LookupServer {

  def main(args: Array[String]): Unit ={
      val system = ActorSystem("lookupServer", ConfigFactory.load("lookup-server"))
      val serverActor = system.actorOf(Props(classOf[ServerActor]), "serverActor")


  }

}
