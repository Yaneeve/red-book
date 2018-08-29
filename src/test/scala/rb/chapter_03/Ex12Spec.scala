package rb.chapter_03

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

import scala.util.{Success, Try}
import scala.{List => sList}

import rb.chapter_03.ListConversions._

object Ex12Spec extends Properties("03.12") {

  import Ex12.reverse

  private val ints = Gen.containerOf[sList, Int](Gen.chooseNum(Int.MinValue, Int.MaxValue))

  property("reverse") =  forAll(ints) { l: sList[Int] =>
    val real = Try(l.reverse)
    val ex = Try(convert(reverse(List(l : _*))))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case _ => false
    }
  }

}
