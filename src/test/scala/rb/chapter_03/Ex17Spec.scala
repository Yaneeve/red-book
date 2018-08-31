package rb.chapter_03

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}
import rb.chapter_03.ListConversions.convert

import scala.util.{Success, Try}
import scala.{List => sList}

object Ex17Spec extends Properties("03.17") {

  import Ex17.mapToString

  private val list = Gen.containerOf[sList, Double](Gen.chooseNum(Double.MinValue, Double.MaxValue))

  property("mapToString") = forAll(list) { l: sList[Double] =>
    val real = Try(l.map(_.toString))
    val ex = Try(convert(mapToString(List(l : _*))))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case _ => false
    }
  }

}
