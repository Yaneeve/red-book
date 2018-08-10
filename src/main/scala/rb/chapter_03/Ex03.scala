package rb.chapter_03

object Ex03 {

  import Ex02.tail

  def setHead[A](l: List[A], h: A): List[A] = Cons(h, tail(l))

}
