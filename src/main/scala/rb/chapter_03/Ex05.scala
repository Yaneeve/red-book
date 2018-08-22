package rb.chapter_03

import scala.annotation.tailrec

object Ex05 {

  @tailrec
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = {
    l match {
      case Nil => l
      case Cons(h, t) if f(h) => dropWhile(t, f)
      case _ => l
    }
  }


}
