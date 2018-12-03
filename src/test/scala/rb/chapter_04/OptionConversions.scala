package rb.chapter_04

import scala.{Option => sOption, Some => sSome, None => sNone}

object OptionConversions {

  def convert[A](so: sOption[A]): Option[A] = so match {
    case sSome(a) => Some(a)
    case sNone => None
  }

  def convert[A](o: Option[A]): sOption[A] = o match {
    case Some(a) => sSome(a)
    case None => sNone
  }


}
