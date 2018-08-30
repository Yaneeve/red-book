package rb.chapter_03

object Ex13 {

  import Ex10.foldLeft
  import Ex12.reverse

  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = {
    val rs = reverse(as)
    rs match {
      case Nil => z
      case _ => foldLeft(rs, z){ case (acc, a) => f(a, acc) }
    }
  }

}
