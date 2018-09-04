package rb.chapter_03

object Ex21 {

  import Ex20.flatMap

  def filter[A](as: List[A])(f: A => Boolean): List[A] =
    flatMap(as){ a => if (f(a)) List(a) else Nil }

}
