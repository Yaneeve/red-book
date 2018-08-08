package rb.chapter_02

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

object Ex02Spec extends Properties("02.02") {
  import Ex02.isSorted

  val arr = Gen.containerOf[Array, Int](Gen.chooseNum(Int.MinValue, Int.MaxValue))

  property("arbitrary array") =  forAll(arr) { arr: Array[Int] =>
    isSorted[Int](arr, { case (a:Int, b: Int) => a <= b}) == arr.sorted.zip(arr.toSeq).forall {case (a, b) => a == b}
  }
}
