package com.vtex.akkahttpseed.helpers

import com.typesafe.config.ConfigFactory

trait Config {
  private val config = ConfigFactory.load()
  private val httpConfig = config.getConfig("akkascalahandson")

  val httpInterface = httpConfig.getString("interface")
  val httpPort = httpConfig.getInt("port")
}