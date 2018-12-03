package rb.chapter_04

import cats.implicits._
import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}
import rb.chapter_04.OptionConversions._

import scala.util.{Success, Try}
import scala.{Option => sOption}

object Ex04Spec extends Properties("04.04") {

  import Ex04._

  private val geni = Gen.chooseNum(Int.MinValue, Int.MaxValue)
  private val genopt = Gen.option(geni)
  private val genList = Gen.listOf(genopt)

  property("sequence") = forAll(genList) { los: List[sOption[Int]] =>
    val real: Try[sOption[List[Int]]] = Try(los.sequence)
    val ex: Try[Option[List[Int]]] = Try(sequence(los.map(convert(_))))
    (real, ex) match {
      case (Success(r), Success(e)) => r == convert(e)
      case _ => false
    }
  }
}
