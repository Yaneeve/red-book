package rb.chapter_03

import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Properties}

import scala.util.{Success, Try}
import scala.{List => sList}

import rb.chapter_03.ListConversions.convert

object Ex14Spec extends Properties("03.14") {

  import Ex14.append

  private val list = Gen.containerOf[sList, Int](Gen.chooseNum(Int.MinValue, Int.MaxValue))
  private val tup = for {
    xs <- list
    ys <- list
  } yield (xs, ys)

  property("append") = forAll(tup) { case (xs: sList[Int], ys: sList[Int]) =>
    val real: Try[sList[Int]] = Try(xs ++ ys)
    val consXs: List[Int] = List[Int](xs: _*)
    val consYs: List[Int] = List[Int](ys: _*)
    val ex = Try(convert(append(consXs, consYs)))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case _ => false
    }
  }

}
