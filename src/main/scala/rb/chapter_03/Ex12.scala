package rb.chapter_03

object Ex12 {

  import Ex10.foldLeft

  def reverse[A](as: List[A]): List[A] = as match {
    case Nil => Nil
    case _ => foldLeft(as, Nil: List[A]){ case (acc, a) => Cons(a, acc) }
  }

}
