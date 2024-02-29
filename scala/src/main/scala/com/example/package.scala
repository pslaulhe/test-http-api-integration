package com

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.language.postfixOps
import scala.util.Try

package object example {

  implicit class TryOps[B](t: Try[B]) {
    def toEitherA[A](ta: Throwable => A): Either[A, B] = t.fold(throwable => ta(throwable).left(), _.right())
  }

  implicit class FutureOps[B](f: Future[B]) {
    def toTry: Try[B] = Try(Await.result(f, 2 seconds))
    def toEither[A](ta: Throwable => A): Either[A, B] = f.toTry.toEitherA[A](ta)
  }

  implicit class AnyOps[T](t: T) {
    def right[A](): Either[A, T] = Right(t)
    def left[B](): Either[T, B] = Left(t)
  }

}
