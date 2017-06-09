import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.io.{IO, Tcp}
import akka.util.ByteString
import java.net.InetSocketAddress

import akka.io.Tcp.Connected

object TcpClient2 {

  class TcpClient2(remote: InetSocketAddress, listener: ActorRef) extends Actor {
    import Tcp._
    import context.system
    IO(Tcp) ! Connect(remote)

    override def receive: Receive = {
      case CommandFailed(_: Connect) =>
        listener ! "connect failed"
        context stop self

      case c @ Connected(remote, local) =>
        listener ! c
        val connection = sender()
        connection ! Register(self)
        context become {
          case data: ByteString =>
            connection ! Write(data)
          case CommandFailed(w: Write) =>
            listener ! "write failed"
          case Received(data) =>
            listener ! data
          case "close" =>
            connection ! Close
          case _: ConnectionClosed =>
            listener ! "connection closed"
            context stop self
        }
    }

  }

  class ClientHandler(value: String) extends Actor {
    def receive: Receive = {
      case c@Connected(remote, local) =>
        println(s"Connnected -> remote: $remote, local: $local")
        sender() ! ByteString(value)
      case b: ByteString => println(b.utf8String)

      case f@("connect failed" | "connection closed" | "write failed") =>
        println(f)
    }
  }

  def main(args: Array[String]): Unit ={
    val serverAddress = new InetSocketAddress("localhost", 9000)
    val system = ActorSystem("actor-system")
    val clientHandler = system.actorOf(Props(classOf[ClientHandler], "value234"))
    system.actorOf(Props(classOf[TcpClient2], serverAddress, clientHandler))
    //system.terminate()

  }

}
