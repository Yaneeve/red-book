package rb.chapter_03

object Ex14 {

  import Ex13.foldRight

  def append[A](xs: List[A], ys: List[A]): List[A] = {
    foldRight(xs, ys)(Cons(_, _))
  }

}
