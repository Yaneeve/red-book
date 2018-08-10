package rb.chapter_03

object Ex02 {

  def tail[A](l: List[A]): List[A] = {
    l match {
      case Nil => throw new UnsupportedOperationException("empty.tail")
      case Cons(_, t) => t
    }
  }

}
