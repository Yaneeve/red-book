package rb.chapter_03

object Ex09 {

  def length[A](as: List[A]): Int = List.foldRight(as, 0) { case (_, acc) => acc + 1}

}
