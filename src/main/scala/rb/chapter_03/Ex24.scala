package rb.chapter_03

import scala.annotation.tailrec

object Ex24 {

  import Ex23.zipWith
  import Ex09.length
  import Ex19.filter
  import Ex02.tail

  @tailrec
  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = {
    val supL = length(sup)
    val subL = length(sub)
    if (supL < subL) false
    else if (supL == 0) false
    else if (subL == 0) false
    else {
      val zipped = zipWith(sub, sup)
      val filtered = filter(zipped)(z => z._1 == z._2)
      (length(zipped) == length(filtered)) || hasSubsequence(tail(sup), sub)
    }


  }

}
