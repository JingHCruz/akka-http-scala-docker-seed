//package com.vtex.akkahttpseed.api
//
//import akka.http.scaladsl.model.StatusCodes
//import akka.http.scaladsl.server.Directives._
//import akka.http.scaladsl.server._
//import akka.http.scaladsl.testkit.ScalatestRouteTest
//import com.vtex.akkahttpseed.Routes
//import org.scalatest.{Matchers, WordSpec}
//
//class handson extends  WordSpec with Matchers with ScalatestRouteTest  with Routes {
//
//
//
//  "The service" should {
//
//    "return a greeting for GET requests to the root path" in {
//      // tests:
//      Get("/hello") ~> routes ~> check {
//          responseAs[String] shouldEqual "hello"
//      }
//    }
//
////    "return a 'PONG!' response for GET requests to /ping" in {
////       tests:
////      Get("/ping") ~> smallRoute ~> check {
////        responseAs[String] shouldEqual "PONG!"
////      }
////    }
//
////    "leave GET requests to other paths unhandled" in {
////       tests:
////      Get("/kermit") ~> smallRoute ~> check {
////        handled shouldBe false
////      }
////    }
//
////    "return a MethodNotAllowed error for PUT requests to the root path" in {
////      // tests:
////      Put() ~> Route.seal(smallRoute) ~> check {
////        status === StatusCodes.MethodNotAllowed
////        responseAs[String] shouldEqual "HTTP method not allowed, supported methods: GET"
////      }
////    }
//  }
//}