import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object LookupClient {

   def main(args: Array[String]): Unit ={
     val system = ActorSystem("LookupClient", ConfigFactory.load("lookup-client"))
     val remotePath = "akka.tcp://lookupServer@127.0.0.1:2552/user/serverActor"

     val serverActor = system.actorSelection(remotePath)
     serverActor ! "HELLO WORLD"
   }

}
