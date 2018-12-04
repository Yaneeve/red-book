package rb.chapter_04

object Ex05 {

  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] =
    a.foldRight(Some(List.empty[B]) : Option[List[B]])(
      (curr: A, acc: Option[List[B]]) =>
        acc.flatMap((bs: List[B]) => f(curr).map(_ :: bs))
    )

  def sequence[A](a: List[Option[A]]): Option[List[A]] = traverse(a)(identity)
}
