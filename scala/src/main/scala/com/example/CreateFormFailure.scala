package com.example

sealed trait CreateFormFailure
object CreateFormFailure {
  case object InvalidUrl extends CreateFormFailure
  case object LocationHeaderNotPresent extends CreateFormFailure
  final case class RequestNotExecuted(exception: Throwable) extends CreateFormFailure
  final case class ErrorResponse(code: Int, body: Option[String] = None) extends CreateFormFailure
}
