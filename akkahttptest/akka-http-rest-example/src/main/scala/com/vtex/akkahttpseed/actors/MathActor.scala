package com.vtex.akkahttpseed.actors


import akka.actor.{Actor, ActorLogging, Props}
import com.amazonaws.services.sqs.AmazonSQSAsyncClient
import com.amazonaws.services.sqs.model.{GetQueueUrlRequest, GetQueueUrlResult, SendMessageRequest, SendMessageResult}
import com.vtex.akkahttpseed.actors.MathActor.Divide
import com.vtex.akkahttpseed.helpers.AWStAsyncHandler

import scala.util.{Failure, Success}


object MathActor {
  def props = Props(new MathActor)

  case class Divide(num: Int, div: Int)

}

class MathActor extends Actor with ActorLogging {
  implicit val ece = context.dispatcher

  val client = new AmazonSQSAsyncClient()
  val queueUrlResult = new AWStAsyncHandler[GetQueueUrlRequest, GetQueueUrlResult]()
  client.getQueueUrlAsync("AkkaHttpScalaDockerHandsOn", queueUrlResult)

  def receive = {
    case Divide(num, int) =>
      sqsTest()
      val result = num / int
      sender ! result
    case _ => log.info("received test")
  }

  def sqsTest(): Unit = {

    val sendResult = new AWStAsyncHandler[SendMessageRequest, SendMessageResult]
    queueUrlResult.future map {
      case (request, result) => client.sendMessageAsync(result.getQueueUrl, "test future java-> scala", sendResult)
    }

    sendResult.future onComplete {
      case Success(value) => println(s"success: $value")
      case Failure(ex) => println(s"Failure: $ex")
    }

  }


}


