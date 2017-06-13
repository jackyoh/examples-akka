import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import scala.io.StdIn


/** 測試使用簡單的akka http,啟動程式之後可以使用curl指令輸入以下的URL
  * curl -X GET http://localhost:8080/hello
  * 可以看到HELLO WORLD在browser被印出
  * Created by jack on 2017/6/5.
  */
object HttpServerSimpleExample {

  def main(args: Array[String]): Unit ={
     implicit val system = ActorSystem("my-actor-system")
     implicit val materializer = ActorMaterializer()
     implicit val executionContext = system.dispatcher

     val route =
       path("hello"){
         get {
           complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>HELLO WORLD!</h1>"))
         }
       }

     val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
     println(s"Server online at http://localhost:8080/\nPress RETURN to stop..")

    StdIn.readLine()
    bindingFuture.flatMap(_.unbind())
                 .onComplete(_=> system.terminate())
  }

}

