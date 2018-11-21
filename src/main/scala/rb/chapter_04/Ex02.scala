package rb.chapter_04

object Ex02 {

  def variance(xs: Seq[Double]): Option[Double] = {
    xs match {
      case Nil => None
      case _ =>
        val mean = xs.sum / xs.length
        val squaredDiv = xs.map(x => scala.math.pow(x - mean, 2))
        Some(squaredDiv.sum / squaredDiv.length)
    }
  }

}
