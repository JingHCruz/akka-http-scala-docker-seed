package com.vtex.akkahttpseed.api

import java.util.UUID

import akka.http.scaladsl.server.Directives._



class RandRoutes() {
  val randRoutes = pathPrefix("rand") {
    path("guid") {
      complete(UUID.randomUUID.toString)
    }
  }

}
