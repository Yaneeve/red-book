package rb.chapter_03

object Ex17 {

  import Ex13.foldRight

  def mapToString(ds: List[Double]): List[String] =
    foldRight(ds, Nil : List[String]){ case (d, l) => Cons(d.toString, l) }

}
