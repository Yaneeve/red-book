package rb.chapter_03

object Ex15 {

  import Ex13.foldRight
  import Ex10.foldLeft
  import Ex14.append
  import Ex12.reverse

  def flatten[A](lola: List[List[A]]): List[A] = {
    lola match {
      case Nil => Nil
      case _ =>
        val rlola = reverse(foldLeft(lola, Nil: List[List[A]]){ case (acc, a) => Cons(reverse(a), acc) })
        val Cons(h, t) = rlola
        reverse(foldRight(t, h)(append))
    }
  }

}
