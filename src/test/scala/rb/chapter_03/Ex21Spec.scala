package rb.chapter_03

import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Properties}
import rb.chapter_03.ListConversions.{convert, convertFunction}

import scala.util.{Success, Try}
import scala.{List => sList}

object Ex21Spec extends Properties("03.21") {

  import Ex21.filter

  private val tup =
    for {
      l <- Gen.containerOf[sList, Double](Gen.chooseNum(Double.MinValue, Double.MaxValue))
      f <- Arbitrary.arbitrary[Double => Boolean]
    } yield (l, f)

  property("filter") = forAll(tup) { case (l: sList[Double], f: (Double => Boolean)) =>
    val real = Try(l.filter(f))
    val ex = Try(convert(filter(List(l : _*))(f)))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case _ => false
    }
  }

}
