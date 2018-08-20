package rb.chapter_03

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}
import rb.chapter_03.ListConversions.convert

import scala.util.{Failure, Success, Try}
import scala.{List => sList}

object Ex03Spec extends Properties("03.03") {

  import Ex03.setHead
  val list = Gen.containerOf[sList, Int](Gen.chooseNum(Int.MinValue, Int.MaxValue))
  val head = Gen.chooseNum(Int.MinValue, Int.MaxValue)
  val tup = for {
    l <- list
    h <- head
  } yield (h, l)

  property("setHead") =  forAll(tup) { case (h: Int, l: sList[Int]) =>
    val real = Try(h :: l.tail)
    val ex = Try(convert(setHead(List(l : _*), h)))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case (Failure(r), Failure(e)) =>
        if (r.isInstanceOf[UnsupportedOperationException] && e.isInstanceOf[UnsupportedOperationException]) true
        else false
      case _ => false
    }
  }
}
