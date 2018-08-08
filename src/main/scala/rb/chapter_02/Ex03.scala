package rb.chapter_02

object Ex03 {

  def curry[A, B, C](f: (A, B) => C): A => B => C = {
    { a => f(a, _) }
  }
}
