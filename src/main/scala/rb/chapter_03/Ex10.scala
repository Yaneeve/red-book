package rb.chapter_03

import scala.annotation.tailrec

object Ex10 extends  App {

  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = {
    @tailrec
    def helper(xs: List[A], acc: B): B = xs match {
      case Cons(h, t) => helper(t, f(acc, h))
      case Nil => acc
    }
    helper(as, z)
  }

  val sum = foldLeft(List(1, 2, 3, 14), 0)(_ + _)
  println(sum)
  println(foldLeft(Nil: List[Int], 42)(_ + _))
  println(foldLeft(List(1, 2, 3), "start")(_ + " " + _))

}
