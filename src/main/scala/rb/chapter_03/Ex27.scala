package rb.chapter_03

class Ex27 {

  def depth[A](t: Tree[A]): Int = t match {
    case Branch(left, right) => (depth(left) + 1) max (depth(right) + 1)
    case Leaf(value) => 0
  }
}
