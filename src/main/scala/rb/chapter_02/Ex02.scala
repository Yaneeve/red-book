package rb.chapter_02

import scala.annotation.tailrec

object Ex02 extends App{

  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
    @tailrec
    def loop(n: Int): Boolean =
      if (n >= as.length - 1) true
      else if (ordered(as(n), as(n+1))) loop(n + 1) else false

    loop(0)
  }

  val arr = Array[Int](1, 2, 3, 4, 78)
  println(isSorted[Int](arr, { case (a:Int, b: Int) => a <= b}))
}
