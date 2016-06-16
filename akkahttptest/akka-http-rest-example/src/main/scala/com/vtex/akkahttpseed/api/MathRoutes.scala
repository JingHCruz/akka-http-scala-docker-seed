package com.vtex.akkahttpseed.api

import akka.actor.ActorRef
import akka.http.scaladsl.server.Directives._
import akka.pattern.ask
import akka.util.Timeout
import com.vtex.akkahttpseed.actors.MathActor.Divide

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration._


class MathRoutes(mathActor: ActorRef)
                (implicit context: ExecutionContextExecutor, timeout: Timeout) {


  val route =
    pathPrefix("calculator") {
      path("div" / IntNumber / IntNumber) { (num, div) =>
        get {
          implicit val timeout = Timeout(5 seconds)
          val divResult = (mathActor ? Divide(num, div)).mapTo[Int]
          val output = divResult.map(v => v.toString)
          complete(output)
        }
      }
    }

}
