package rb.chapter_04

object Ex04 {

  def sequence[A](a: List[Option[A]]): Option[List[A]] =
    a.foldRight(Some(List.empty[A]): Option[List[A]]) {
      case (curr: Option[A], acc: Option[List[A]]) =>
        curr.flatMap (
          (c: A) => acc.map(as => c :: as)
      ).orElse(None)
  }
}
