package rb.chapter_03

import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Properties}

import scala.util.{Success, Try}
import scala.{List => sList}

object Ex10Spec extends Properties("03.10") {

  import Ex10.foldLeft

  private val genf: Gen[(String, Int) => String] = Arbitrary.arbitrary[(String, Int) => String]

  private val list = Gen.containerOf[sList, Int](Gen.chooseNum(Int.MinValue, Int.MaxValue))
  private val tup = for {
    l <- list
    p <- genf
    str <- Gen.alphaNumStr
  } yield (p, l, str)

  property("foldLeft") = forAll(tup) { case (p: ((String, Int) => String), l: sList[Int], str: String) =>
    val real: Try[String] = Try(l.foldLeft(str)(p))
    val consList: List[Int] = List[Int](l: _*)
    val ex = Try(foldLeft(consList, str)(p))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case _ => false
    }
  }

}
