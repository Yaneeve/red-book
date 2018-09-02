package rb.chapter_03

object Ex20 {

  import Ex13.foldRight
  import Ex10.foldLeft

  def flatMap[A,B](as: List[A])(f: A => List[B]): List[B] =
    foldLeft(as, Nil: List[B]){ case (bs, a) => foldRight(bs, f(a))(Cons(_, _)) }


}
