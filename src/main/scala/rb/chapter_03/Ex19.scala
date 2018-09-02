package rb.chapter_03

object Ex19 {

  import Ex13.foldRight

  def filter[A](as: List[A])(f: A => Boolean): List[A] =
    foldRight(as, Nil : List[A]) { case (a, l) => if (f(a)) Cons(a, l) else l }

}
