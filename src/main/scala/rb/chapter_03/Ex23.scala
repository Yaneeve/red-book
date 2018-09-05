package rb.chapter_03


import scala.annotation.tailrec

object Ex23 {
  import Ex12.reverse

  def zipWith[A, B](lhs: List[A], rhs: List[B]): List[(A, B)] = {
    @tailrec
    def helper(l: List[A], r: List[B], acc: List[(A, B)]): List[(A, B)] =
      (l, r) match {
        case (Nil, _) => acc
        case (_, Nil) => acc
        case (Cons(hl, tl), Cons(hr, tr)) => helper(tl, tr, Cons(hl -> hr, acc))
      }
    reverse(helper(lhs, rhs, Nil))
  }

}
