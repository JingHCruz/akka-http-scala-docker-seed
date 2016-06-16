package com.vtex.akkahttpseed.api

import akka.http.scaladsl.server.Directives._



class RootRoutes(mathRoutes: MathRoutes, randRoutes: RandRoutes) {
  val routes =
    mathRoutes.route ~
      randRoutes.randRoutes
}
