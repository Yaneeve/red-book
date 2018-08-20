package rb.chapter_03

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}
import rb.chapter_03.ListConversions.convert

import scala.util.{Failure, Success, Try}
import scala.{List => sList}

object Ex04Spec extends Properties("03.03") {

  import Ex04.drop

  val list = Gen.containerOf[sList, Int](Gen.chooseNum(Int.MinValue, Int.MaxValue))
  val tupValid = for {
    l <- list
    i <- Gen.chooseNum(0, Math.max(0, l.size - 1))
  } yield (i, l)

  property("drop") = forAll(tupValid) { case (i: Int, l: sList[Int]) =>
    val real = Try(l.drop(i))
    val ex = Try(convert(drop(List(l : _*), i)))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case _ => false
    }
  }

  val tupInvalid = for {
    l <- list
    i <- Gen.oneOf(Gen.negNum[Int], Gen.chooseNum(l.size, Int.MaxValue))
  } yield (i, l)


  property("invalid drop") = forAll(tupInvalid) { case (i: Int, l: sList[Int]) =>
    val real = Try(l.drop(i))
    val ex = Try(convert(drop(List(l : _*), i)))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case _ => false
    }
  }
}
