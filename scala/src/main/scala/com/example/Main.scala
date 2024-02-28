package com.example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

object Main extends App {

  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  import system.dispatcher

  CreateForm(
    typeformBaseUrl = "https://api.typeform.com",
    typeformAccessToken = "tfp_4SYFGY24QRrZB5sv57wRxLJp1sy1X18Qozs61oQeq7u7_3mLLFWD3buGz9C",
    typeformWorkspace = "jqY3ZM",
    httpClient = Http()
  ).execute match {
    case Left(createFormFailure) => println(createFormFailure)
    case Right(formId) => println(formId)
  }

}
