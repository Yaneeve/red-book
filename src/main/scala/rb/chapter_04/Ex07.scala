package rb.chapter_04

object Ex07 {

  def traverse[E, A, B](as: List[A])(f: A => Either[E, B]): Either[E, List[B]] =
    as.foldLeft(Right(List.empty[B]): Either[E, List[B]])(
      (acc: Either[E, List[B]], curr: A) => acc.flatMap(bs =>
        f(curr).map(_ :: bs)
      )).map(_.reverse)

  def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]] = traverse(es)(identity)
}
