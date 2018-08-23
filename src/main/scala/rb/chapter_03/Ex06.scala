package rb.chapter_03

import scala.annotation.tailrec

object Ex06 {

import Ex02.tail

  def init[A](l: List[A]): List[A] = {

    @tailrec
    def reverse(orig: List[A], dest: List[A]): (List[A], List[A]) =
      orig match {
        case Nil => (orig, dest)
        case Cons(h,t) => reverse(t, Cons(h, dest))
      }
    l match {
      case Nil => throw new UnsupportedOperationException("empty.init")
      case _ =>
        val r = reverse(l, Nil)._2
        val reversedInit  = tail(r)
        reverse(reversedInit, Nil)._2
    }
  }


}
