package rb.chapter_03

object Ex28 {

  def map[A, B](t: Tree[A])(func: A => B): Tree[B] = t match {
    case Branch(left, right) => Branch(map(left)(func), map(right)(func))
    case Leaf(value) => Leaf(func(value))
  }

}
