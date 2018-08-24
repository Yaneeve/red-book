package rb.chapter_03

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}
import rb.chapter_03.ListConversions.convert

import scala.util.{Failure, Success, Try}
import scala.{List => sList}

object Ex09Spec extends Properties("03.09") {

  import Ex09.length
  private val list = Gen.containerOf[sList, Int](Gen.chooseNum(Int.MinValue, Int.MaxValue))

  property("length") =  forAll(list) { l: sList[Int] =>
    val real = Try(l.length)
    val ex = Try(length(List(l : _*)))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case _ => false
    }
  }
}
