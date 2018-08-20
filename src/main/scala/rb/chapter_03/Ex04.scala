package rb.chapter_03

import scala.annotation.tailrec

object Ex04 {
  import Ex02.tail

  @tailrec
  def drop[A](l: List[A], n: Int): List[A] = {
    l match {
      case Nil => l
      case _ if n <= 0 => l
      case _ => drop(tail(l), n - 1)
    }
  }


}
