package rb.chapter_02

import org.scalacheck.{Gen, Properties}
import org.scalacheck.Prop.{BooleanOperators, forAll}

object Ex01Spec extends Properties("02.01") {
  import Ex01.fibonacci

  val smallInteger = Gen.choose(2,49)


  property("positive") =  forAll(smallInteger) { n: Int =>
    fibonacci(n - 1) + fibonacci(n) == fibonacci(n + 1)
  }
}
