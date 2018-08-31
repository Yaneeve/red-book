package rb.chapter_03

import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Properties}
import rb.chapter_03.ListConversions.convert

import scala.util.{Success, Try}
import scala.{List => sList}

object Ex18Spec extends Properties("03.18") {

  import Ex18.map

  private val tup =
    for {
      l <- Gen.containerOf[sList, Double](Gen.chooseNum(Double.MinValue, Double.MaxValue))
      f <- Arbitrary.arbitrary[Double => String]
    } yield (l, f)

  property("map") = forAll(tup) { case (l: sList[Double], f: (Double => String)) =>
    val real = Try(l.map(f))
    val ex = Try(convert(map(List(l : _*))( f)))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case _ => false
    }
  }

}
