package rb.chapter_03

object Ex08 extends App {


  private val orig = List(1, 2, 3)
  val identity = List.foldRight(orig, Nil:List[Int])(Cons(_,_))
  println(orig)
  println(identity)
  assert(! (orig eq identity), "This should actually replicate the structure by allocating a new list")
  // https://stackoverflow.com/a/22103302/101715 <- explains why `eq` and not `==`
}
