package rb.chapter_03

object Ex11 {
  import Ex10.foldLeft

  def sum(ints: List[Int]): Int = foldLeft(ints, 0)(_ + _)

  def product(ds: List[Double]): Double = foldLeft(ds, 1d)(_ * _)

  def length[A](as: List[A]): Int = foldLeft(as, 0){ case (acc, _) => acc + 1 }

}
