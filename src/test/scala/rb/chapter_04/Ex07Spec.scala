package rb.chapter_04

import cats.implicits._
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Properties}
import rb.chapter_04.EitherConversions._

import scala.util.{Success, Try}
import scala.util.{Either => sEither}

object Ex07Spec extends Properties("04.07") {

  import Ex07._

  private val geni = Gen.chooseNum(Int.MinValue, Int.MaxValue)
  private val geneith = for {
    gs <- Gen.alphaNumStr
    geith <- Gen.option(geni).map(_.toRight(gs))
  } yield geith
  private val genList = Gen.listOf(geneith)
  private val genf: Gen[Int => sEither[String, Int]] = Arbitrary.arbitrary[Int => sEither[String, Int]]

  private val genForTraverse = for {
    gl <- Gen.listOf(geni)
    gf <- genf
  } yield (gl, gf)

  property("either sequence") = forAll(genList) { los: List[sEither[String, Int]] =>
    val real: Try[sEither[String, List[Int]]] = Try(los.sequence)
    val ex: Try[Either[String, List[Int]]] = Try(sequence(los.map(convert(_))))
    (real, ex) match {
      case (Success(r), Success(e)) => r == convert(e)
      case _ => false
    }
  }

  property("either traverse") = forAll(genForTraverse) { case (lis: List[Int], func: (Int => sEither[String, Int]))=>
    val exFunc: Int => Either[String, Int] = (EitherConversions.convert (_ : sEither[String, Int])).compose(func)
    val real: Try[sEither[String, List[Int]]] = Try(lis.traverse(func))
    val ex: Try[Either[String, List[Int]]] = Try(traverse(lis)(exFunc))
    (real, ex) match {
      case (Success(r), Success(e)) => r == convert(e)
      case _ => false
    }
  }
}
