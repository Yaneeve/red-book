package rb.chapter_03

object Ex16 {

  import Ex13.foldRight

  def plusOne(nums: List[Int]): List[Int] =
    foldRight(nums, Nil : List[Int]){ case (h, t) => Cons(h + 1, t)}

}
