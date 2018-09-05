package rb.chapter_03

import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Properties}
import rb.chapter_03.ListConversions.{convert, convertFunction}

import scala.util.{Success, Try}
import scala.{List => sList}

object Ex23Spec extends Properties("03.23") {

  import Ex23.zipWith

  private val tup =
    for {
      ld <- Gen.containerOf[sList, Double](Gen.chooseNum(Double.MinValue, Double.MaxValue))
      ls <- Gen.containerOf[sList, String](Gen.alphaNumStr)
    } yield (ld, ls)

  property("zipWith") = forAll(tup) { case (ds: sList[Double], strs: sList[String]) =>
    val real = Try(ds.zip(strs))
    val ex = Try(convert(zipWith(List(ds : _*), List( strs : _ *))))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case _ => false
    }
  }

}
