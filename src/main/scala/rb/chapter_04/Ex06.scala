package rb.chapter_04

object Ex06 {

  def map[E, A, B](e: Either[E, A])(f: A => B): Either[E, B] = e match {
    case Right(a) => Right(f(a))
    case Left(err) => Left(err)
  }

  def orElse[E, A, EE >: E, B >: A](e: Either[E, A])(b: => Either[EE, B]): Either[EE, B] = e match {
    case r@Right(_) => r
    case _ => b
  }

  def flatMap[E, A, EE >: E, B](e: Either[E, A])(f: A => Either[EE, B]): Either[EE, B] = e match {
    case Right(a) => f(a)
    case l@Left(_) => l
  }

  def map2[E, A, EE >: E, B, C](a: Either[E, A])(b: Either[EE, B])(f: (A, B) => C): Either[EE, C] =
    (a, b) match {
      case (Right(aa), Right(bb)) => Right(f(aa, bb))
      case (l@Left(e), _) => l
      case (_, l@Left(e)) => l
    }
}
