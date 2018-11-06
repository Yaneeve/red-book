package rb.chapter_03

object Ex25 {

  def size[A](tree: Tree[A]): Int = {
    tree match {
      case Leaf(_) => 1
      case Branch(rhs, lhs) => size(rhs) + size(lhs) + 1 // thank you fpinscala answers for correcting me
    }
  }

}
