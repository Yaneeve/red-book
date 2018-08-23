package rb.chapter_03

import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Properties}
import rb.chapter_03.ListConversions.convert

import scala.util.{Failure, Success, Try}
import scala.{List => sList}

object Ex06Spec extends Properties("03.06") {

  import Ex06.init

  private val list = Gen.containerOf[sList, Int](Gen.chooseNum(Int.MinValue, Int.MaxValue))

  property("init") = forAll(list) {  l: sList[Int] =>
    val real = Try(l.init)
    val ex = Try(convert(init(List(l : _*))))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case (Failure(r), Failure(e)) =>
        if (r.isInstanceOf[UnsupportedOperationException] && e.isInstanceOf[UnsupportedOperationException]) true
        else false
      case _ => false
    }
  }

}
