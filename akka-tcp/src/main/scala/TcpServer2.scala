import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.io.{IO, Tcp}
import akka.util.ByteString
import java.net.InetSocketAddress
object TcpServer2 {

  class TcpServer2 extends Actor {

    import Tcp._
    import context.system

    IO(Tcp) ! Bind(self, new InetSocketAddress("localhost", 9000))

    override def receive: Receive = {
      case b @ Bound(localAddress) => println("Bound finish")

      case CommandFailed(_: Bind) => context stop self

      case c @ Connected(remote, local) =>
        val handler = context.actorOf(Props[SimplisticHandler])
        val connection = sender()
        connection ! Register(handler)
      case value: String => println("TEST" + value)
     }
  }

  class SimplisticHandler extends Actor {
    import Tcp._
    override def receive: Receive = {
      case Received(data) =>
        println("received the:" + data.utf8String + " data")
        sender() ! Write(data)

      case PeerClosed => context stop self
    }
  }

  def main(args: Array[String]) = {
    val system = ActorSystem("actor-system-1")
    val serverAddress = new InetSocketAddress("localhost", 9000)
    val server = system.actorOf(Props(classOf[TcpServer2]))
    server ! "hello world"
  }
}