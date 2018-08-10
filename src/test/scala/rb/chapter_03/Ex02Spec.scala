package rb.chapter_03

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

import scala.{List => sList, Nil => sNil}
import ListConversions.convert

import scala.util.{Failure, Success, Try}

object Ex02Spec extends Properties("02.02") {

  import Ex02.tail
  val list = Gen.containerOf[sList, Int](Gen.chooseNum(Int.MinValue, Int.MaxValue))

  property("tail") =  forAll(list) { l: sList[Int] =>
    val real = Try(l.tail)
    val ex = Try(convert(tail(List(l : _*))))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case (Failure(r), Failure(e)) =>
        if (r.isInstanceOf[UnsupportedOperationException] && e.isInstanceOf[UnsupportedOperationException]) true
        else false
      case _ => false
    }
  }
}
