package rb.chapter_03

import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Properties}
import rb.chapter_03.ListConversions.{ convert, convertFunction }

import scala.util.{Success, Try}
import scala.{List => sList}

object Ex20Spec extends Properties("03.20") {

  import Ex20.flatMap

  private val tup =
    for {
      l <- Gen.containerOf[sList, Double](Gen.chooseNum(Double.MinValue, Double.MaxValue))
      f <- Arbitrary.arbitrary[Double => sList[String]]
    } yield (l, f)

  property("map") = forAll(tup) { case (l: sList[Double], f: (Double => sList[String])) =>
    val real = Try(l.flatMap(f))
    val ex = Try(convert(flatMap(List(l : _*))(f)))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case _ => false
    }
  }

}
