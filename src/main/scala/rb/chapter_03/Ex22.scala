package rb.chapter_03

import scala.annotation.tailrec

object Ex22 extends App {

  import Ex12.reverse

  def listAdd(lhs: List[Int], rhs: List[Int]): List[Int] = {
    @tailrec
    def helper(l: List[Int], r: List[Int], acc: List[Int]): List[Int] =
    (l, r) match {
      case (Nil, _) => acc
      case (_, Nil) => acc
      case (Cons(hl, tl), Cons(hr, tr)) => helper(tl, tr, Cons(hl + hr, acc))
    }
    reverse(helper(lhs, rhs, Nil))
  }

  println(listAdd(List(1, 2, 3), List(4, 5, 6)))
}
