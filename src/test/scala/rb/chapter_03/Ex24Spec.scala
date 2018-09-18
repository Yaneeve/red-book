package rb.chapter_03

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}
import rb.chapter_03.ListConversions.convert

import scala.util.{Success, Try}
import scala.{List => sList}

object Ex24Spec extends Properties("03.24") {

  import Ex24.hasSubsequence

  private val subLTEsup =
    for {
      sup <- Gen.nonEmptyContainerOf[sList, Int](Gen.posNum[Int])
      sub <- Gen.containerOfN[sList, Int](sup.length, Gen.posNum[Int])
    } yield (sup, sub)

  private def convert(l: sList[Int]): String = l.mkString
  private def hasSubString(sup: String, sub: String): Boolean = sup.matches(s".*$sub.*")

  property("substring where sub is smaller to or equals in length to sup") = forAll(subLTEsup) { case (sup: sList[Int], sub: sList[Int]) =>
    val real = Try(hasSubString(
      convert(sup),
      convert(sub)
    ))
    val ex = Try(hasSubsequence(List(sup : _*), List( sub : _ *)))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case _ => false
    }
  }

}
