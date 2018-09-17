package rb.chapter_03

import scala.annotation.tailrec

object Ex24 {

  import Ex23.zipWith
  import Ex09.length
  import Ex19.filter
  import Ex02.tail

  @tailrec
  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = {
    if (length(sup) == 0) false
    else if (length(sub) == 0) false
    else {
      val zipped = zipWith(sub, sup)
      val filtered = filter(zipped)(z => z._1 == z._2)
      (length(zipped) == length(filtered)) || hasSubsequence(tail(sup), sub)
    }


  }

}
