package rb.chapter_02

object Ex04 {

  def uncurry[A, B, C](f: A => B => C): (A , B) => C = {
    {case (a, b) => f(a)(b)}
  }
}
