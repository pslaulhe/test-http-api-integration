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
    typeformAccessToken = "YOUR_ACCESS_TOKEN",
    typeformWorkspace = "WORKSPACE_ID",
    httpClient = Http()
  ).execute match {
    case Left(createFormFailure) => println(createFormFailure)
    case Right(formId) => println(formId)
  }

}
