package rb.chapter_03

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}
import rb.chapter_03.ListConversions.convert

import scala.util.{Success, Try}
import scala.{List => sList}

object Ex16Spec extends Properties("03.16") {

  import Ex16.plusOne

  private val list = Gen.containerOf[sList, Int](Gen.chooseNum(Int.MinValue, Int.MaxValue))

  property("plusOne") = forAll(list) { l: sList[Int] =>
    val real = Try(l.map(_ + 1))
    val ex = Try(convert(plusOne(List(l : _*))))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case _ => false
    }
  }

}
