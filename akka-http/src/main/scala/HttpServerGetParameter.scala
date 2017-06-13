import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import scala.io.StdIn

/**
  * 測試啟動akka http server,啟動完成之後透過curl指令輸入以下的指令
  * curl -X GET http://localhost:8080/hello?color=blue\&backgroundColor=red
  * Created by jack on 2017/6/12.
  */
object HttpServerGetParameter {

  def main(args: Array[String]): Unit ={
    implicit val system = ActorSystem("my-actor-system")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    //'符號代表欄位要求輸入
    val route =
      path("hello"){
        get {
          parameters('color, 'backgroundColor) { (color, backgroundColor) =>
            complete(s"The color is '$color' and the background is '$backgroundColor'")
          }
        }
      }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop..")

    StdIn.readLine()
    bindingFuture.flatMap(_.unbind())
      .onComplete(_=> system.terminate())


  }
}
