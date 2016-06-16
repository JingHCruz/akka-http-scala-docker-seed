package com.vtex.akkahttpseed

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.vtex.akkahttpseed.actors.MathActor
import com.vtex.akkahttpseed.api.{MathRoutes, RandRoutes, RootRoutes}
import com.vtex.akkahttpseed.helpers.Config

import scala.concurrent.duration._

object Main extends App with Config {


  implicit val system = ActorSystem()
  implicit val context = system.dispatcher
  implicit val materializer = ActorMaterializer()

  val mathActor = system.actorOf(MathActor.props, "mathactor")

  implicit def myExceptionHandler: ExceptionHandler =
    ExceptionHandler {
      case ex =>
        complete(HttpResponse(InternalServerError, entity = s"something wrong: $ex"))
    }

  implicit val timeout2 = Timeout(20 seconds)
  val routes = new RootRoutes(new MathRoutes(mathActor), new RandRoutes())

  Http().bindAndHandle(routes.routes, interface = httpInterface, port = httpPort)
}


