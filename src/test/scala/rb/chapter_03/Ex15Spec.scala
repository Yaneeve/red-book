package rb.chapter_03

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}
import rb.chapter_03.ListConversions.convert

import scala.util.{Success, Try}
import scala.{List => sList}

object Ex15Spec extends Properties("03.14") {

  import Ex15.flatten

  private val list = Gen.containerOf[sList, Int](Gen.chooseNum(Int.MinValue, Int.MaxValue))
  private val lola = for {
    xs <- list
    lola <- Gen.listOf(xs)
  } yield lola

  property("append") = forAll(lola) { slola: sList[sList[Int]] =>
    val real: Try[sList[Int]] = Try(slola.flatten)
    val lola: List[List[Int]] =  List(slola.map(List(_ : _*)) : _ *)
    val flat: List[Int] = flatten(lola)
    val ex = Try(convert(flat))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case _ => false
    }
  }

}
