package rb.chapter_03

object Ex15 {

  import Ex13.foldRight
  import Ex10.foldLeft

  def flatten[A](lola: List[List[A]]): List[A] = {
    lola match {
      case Nil => Nil
      case Cons(h, t) =>
        foldLeft(t, h){case (ls, l) => foldRight(l, ls)(Cons(_, _))}
    }
  }

}
