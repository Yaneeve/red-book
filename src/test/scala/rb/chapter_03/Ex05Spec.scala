package rb.chapter_03

import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Cogen, Gen, Properties}
import rb.chapter_03.ListConversions.convert

import scala.util.{Success, Try}
import scala.{List => sList}

object Ex05Spec extends Properties("03.05") {

  import Ex05.dropWhile

  private val genf: Gen[Int => Boolean] = Arbitrary.arbitrary[Int => Boolean]

  private val list = Gen.containerOf[sList, Int](Gen.chooseNum(Int.MinValue, Int.MaxValue))
  private val tup = for {
    l <- list
    p <- genf
  } yield (p, l)

  property("dropWhile") = forAll(tup) { case (p: (Int => Boolean), l: sList[Int]) =>
    val real = Try(l.dropWhile(p))
    val ex = Try(convert(dropWhile(List(l : _*), p)))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case _ => false
    }
  }

}
