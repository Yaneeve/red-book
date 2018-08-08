package rb.chapter_02

import scala.annotation.tailrec

object Ex01 extends App {

  def fibonacci(nth: Int): Int = {
    @tailrec
    def fib(first: Int, second: Int, step: Int): Int =
      if (step > 1) fib(second, first + second, step -1) else first

    fib(0, 1, nth)
  }

  println(fibonacci(1))
  println(fibonacci(2))
  println(fibonacci(3))
}
