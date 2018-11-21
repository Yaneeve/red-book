package rb.chapter_04


object Ex03 {

  def map2[A,B,C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = (a, b) match {
    case (Some(a1), Some(b1)) => Some(f(a1, b1))
    case _ => None
  }
}
