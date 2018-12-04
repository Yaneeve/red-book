package rb.chapter_04

import cats.implicits._
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Properties}
import rb.chapter_04.OptionConversions._

import scala.util.{Success, Try}
import scala.{Option => sOption}

object Ex05Spec extends Properties("04.05") {

  import Ex05._

  private val geni = Gen.chooseNum(Int.MinValue, Int.MaxValue)
  private val genopt = Gen.option(geni)
  private val genList = Gen.listOf(genopt)
  private val genf: Gen[Int => sOption[String]] = Arbitrary.arbitrary[Int => sOption[String]]

  private val genForTraverse = for {
    gl <- Gen.listOf(geni)
    gf <- genf
  } yield (gl, gf)

  property("sequence") = forAll(genList) { los: List[sOption[Int]] =>
    val real: Try[sOption[List[Int]]] = Try(los.sequence)
    val ex: Try[Option[List[Int]]] = Try(sequence(los.map(convert(_))))
    (real, ex) match {
      case (Success(r), Success(e)) => r == convert(e)
      case _ => false
    }
  }

  property("traverse") = forAll(genForTraverse) { case (lis: List[Int], func: (Int => sOption[String]))=>
    val exFunc: Int => Option[String] = (OptionConversions.convert (_ : sOption[String])).compose(func)
    val real: Try[sOption[List[String]]] = Try(lis.traverse[sOption, String](func))
    val ex: Try[Option[List[String]]] = Try(traverse(lis)(exFunc))
    (real, ex) match {
      case (Success(r), Success(e)) => r == convert(e)
      case _ => false
    }
  }
}
