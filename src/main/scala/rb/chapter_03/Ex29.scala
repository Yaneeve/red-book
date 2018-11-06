package rb.chapter_03

object Ex29 {

  def fold[A,B](t: Tree[A])(f: A => B)(g: (B,B) => B): B = t match {
    case Leaf(value) => f(value)
    case Branch(left, right) =>
      val foldedLeft = fold(left)(f)(g)
      val foldedRight = fold(right)(f)(g)
      g(foldedLeft, foldedRight)
  }

  def size[A](t: Tree[A]): Int = fold(t)(_ => 1)(_ + _ + 1) // thank you fpinscala answers for correcting me

  def maximum(t: Tree[Int]): Int = fold(t)(identity)(_ max _)

  def depth[A](t: Tree[A]): Int = fold(t)(_ => 0)(_ max _ + 1) // thank you fpinscala answers for correcting me

  def map[A, B](t: Tree[A])(f: A => B): Tree[B] = fold[A, Tree[B]](t)(value => Leaf(f(value)))(Branch(_, _))
}
