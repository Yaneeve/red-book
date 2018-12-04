package rb.chapter_04

sealed trait Option[+A] {
  def map[B](f: A => B): Option[B] = Ex01.map(this)(f)

  def getOrElse[B>:A](default: => B): B = Ex01.getOrElse(this : Option[B])(default)

  def flatMap[B](f: A => Option[B]): Option[B] = Ex01.flatMap(this)(f)

  def orElse[B>:A](ob: => Option[B]): Option[B] = Ex01.orElse(this: Option[B])(ob)

  def filter(f: A => Boolean): Option[A] = Ex01.filter(this)(f)
}
case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]

object Option {
  def failingFn(i: Int): Int = {
    val y: Int = throw new Exception("fail!") // `val y: Int = ...` declares `y` as having type `Int`, and sets it equal to the right hand side of the `=`.
    try {
      val x = 42 + 5
      x + y
    }
    catch { case e: Exception => 43 } // A `catch` block is just a pattern matching block like the ones we've seen. `case e: Exception` is a pattern that matches any `Exception`, and it binds this value to the identifier `e`. The match returns the value 43.
  }

  def failingFn2(i: Int): Int = {
    try {
      val x = 42 + 5
      x + ((throw new Exception("fail!")): Int) // A thrown Exception can be given any type; here we're annotating it with the type `Int`
    }
    catch { case e: Exception => 43 }
  }

  def mean(xs: Seq[Double]): Option[Double] =
    if (xs.isEmpty) None
    else Some(xs.sum / xs.length)
  def variance(xs: Seq[Double]): Option[Double] = Ex02.variance(xs)

  def map2[A,B,C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = Ex03.map2(a, b)(f)

  def sequence[A](a: List[Option[A]]): Option[List[A]] = Ex04.sequence(a)

  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = Ex05.traverse(a)(f)
}
