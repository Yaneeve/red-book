package rb.chapter_04

import scala.util.{Left => sLeft, Either => sEither, Right => sRight}

object EitherConversions {

  def convert[E, A](so: sEither[E, A]): Either[E, A] = so match {
    case sRight(a) => Right(a)
    case sLeft(e) => Left(e)
  }

  def convert[E, A](o: Either[E, A]): sEither[E, A] = o match {
    case Right(a) => sRight(a)
    case Left(e) => sLeft(e)
  }


}
