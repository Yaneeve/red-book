package rb.chapter_03

object Ex18 {

  import Ex13.foldRight

  def map[A,B](as: List[A])(f: A => B): List[B] =
    foldRight(as, Nil: List[B]){ case (a, l) => Cons(f(a), l) }
}
