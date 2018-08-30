package rb.chapter_03

object Ex15 {

  import Ex10.foldLeft
  import Ex14.append

  def flatten[A](lola: List[List[A]]): List[A] = lola match {
    case Nil => Nil
    case Cons(h, t) => foldLeft(t, h)(append)
  }

}
