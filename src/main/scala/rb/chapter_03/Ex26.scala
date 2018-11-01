package rb.chapter_03

object Ex26 {

  def maximum(t: Tree[Int]): Int = {
      t match {
        case Branch(left, right) => maximum(left) max maximum(right)
        case Leaf(value) => value
      }
  }

}
