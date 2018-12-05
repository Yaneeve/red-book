package rb.chapter_04

import cats.data.EitherT
import cats.{Applicative, Id}
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Properties}
import rb.chapter_04.EitherConversions._

import scala.util.{Success, Try}
import scala.util.{Either => sEither, Right => sRight}

object Ex06Spec extends Properties("04.06") {
  import Ex06._

  private val geni = Gen.chooseNum(Int.MinValue, Int.MaxValue)
  private val geneith = for {
    gs <- Gen.alphaNumStr
    geith <- Gen.option(geni).map(_.toRight(gs))
  } yield geith

  private val genf: Gen[Int => String] = Arbitrary.arbitrary[Int => String]

  private val generators = for {
    gopt <- geneith
    gf <- genf
  } yield (gopt, gf)

  private val genForOrElse = for {
    gopt1 <- geneith
    gopt2 <- geneith
  } yield (gopt1, gopt2)

  private val genForMap2 = for {
    gopt1 <- geneith
    gopt2 <- geneith
    gf <- genf
  } yield (gopt1, gopt2, gf)

  property("either map") =  forAll(generators) { case (eith: sEither[String, Int], f: (Int => String)) =>
    val real = Try(eith.map(f))
    val ex = Try(map(convert(eith))(f))
    (real, ex) match {
      case (Success(r), Success(e)) => r == convert(e)
      case _ => false
    }
  }

  property("either flatMap") =  forAll(generators) { case (eith: sEither[String, Int], f: (Int => String)) =>
    val slifted: Int => sEither[String, String] = f andThen sRight.apply[String, String]
    val lifted: Int => Either[String, String] = f andThen Right.apply[String]
    val real = Try(eith.flatMap(slifted))
    val ex = Try(flatMap(convert(eith))(lifted))
    (real, ex) match {
      case (Success(r), Success(e)) => r == convert(e)
      case _ => false
    }
  }

  property("either orElse") = forAll(genForOrElse) { case (eith1: sEither[String, Int], eith2: sEither[String, Int]) =>
    val real: Try[sEither[String, Int]] = Try(eith1.fold({ _ => eith2}, {_ => eith1}))
    val ex: Try[Either[String, Int]] = Try(orElse(convert(eith1))(convert(eith2)))
    (real, ex) match {
      case (Success(r), Success(e)) => r == convert(e)
      case _ => false
    }
  }


  property("either map2") =  forAll(genForMap2) { case (eith1: sEither[String, Int], eith2: sEither[String, Int], f: ((Int, Int) => String)) =>
    val real = Try(for {
      r1 <- eith1
      r2 <- eith2
    } yield f(r1, r2))
    val ex = Try(map2(convert(eith1))(convert(eith2))(f))
    (real, ex) match {
      case (Success(r), Success(e)) => r == convert(e)
      case _ => false
    }
  }

}
