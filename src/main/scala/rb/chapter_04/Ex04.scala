package rb.chapter_04

object Ex04 {

  def sequence[A](a: List[Option[A]]): Option[List[A]] = a match {
  case Nil => None
  case _ =>
    a.foldRight(Some(List.empty[A]): Option[List[A]]) {
      case (curr: Option[A], acc: Some[List[A]]) =>
        curr.flatMap {
          case c: A => acc.map(as => c :: as).orElse(None)
        }
    }
  }
}
