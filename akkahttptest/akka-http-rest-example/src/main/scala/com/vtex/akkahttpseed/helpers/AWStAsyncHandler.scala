package com.vtex.akkahttpseed.helpers

import com.amazonaws.AmazonWebServiceRequest
import com.amazonaws.handlers.AsyncHandler

import scala.util.Try

class AWStAsyncHandler[T <: AmazonWebServiceRequest ,W] extends AsyncHandler[T, W] {

  private val promise = scala.concurrent.Promise[(T, W)]()
  val future = promise.future

  override def onError(exception: Exception): Unit = {
    promise.failure(exception)
  }

  override def onSuccess(request: T, result: W): Unit = {
    promise.complete(Try {
      (request, result)
    })
  }

}
