package rb.chapter_04

import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Properties}
import rb.chapter_04.OptionConversions._

import scala.util.{Success, Try}
import scala.{Option => sOption}

object Ex01Spec extends Properties("04.01") {
  import Ex01._

  private val geni = Gen.chooseNum(Int.MinValue, Int.MaxValue)
  private val genopt = Gen.option(geni)
  private val genf: Gen[Int => String] = Arbitrary.arbitrary[Int => String]


  private val generators = for {
    gopt <- genopt
    gf <- genf
  } yield (gopt, gf)

  private val genForGetters = for {
    gopt <- genopt
    gi <- geni
  } yield (gopt, gi)

  private val genForOrElse = for {
    gopt1 <- genopt
    gopt2 <- genopt
  } yield (gopt1, gopt2)

  property("option map") =  forAll(generators) { case (o: sOption[Int], f: (Int => String)) =>
    val real = Try(o.map(f))
    val ex = Try(map(convert(o))(f))
    (real, ex) match {
      case (Success(r), Success(e)) => r == convert(e)
      case _ => false
    }
  }

  property("option flatMap") =  forAll(generators) { case (o: sOption[Int], f: (Int => String)) =>
    val slifted: Int => sOption[String] = f andThen sOption.apply
    val lifted: Int => Option[String] = f andThen Some.apply
    val real = Try(o.flatMap(slifted))
    val ex = Try(flatMap(convert(o))(lifted))
    (real, ex) match {
      case (Success(r), Success(e)) => r == convert(e)
      case _ => false
    }
  }

  property("option getOrElse") = forAll(genForGetters) { case (o: sOption[Int], i: Int) =>
    val real = Try(o.getOrElse(i))
    val ex = Try(getOrElse(convert(o))(i))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case _ => false
    }
  }

  property("option orElse") = forAll(genForOrElse) { case (o1: sOption[Int], o2: sOption[Int]) =>
    val real: Try[sOption[Int]] = Try(o1.orElse(o2))
    val ex: Try[Option[Int]] = Try(orElse(convert(o1))(convert(o2)))
    (real, ex) match {
      case (Success(r), Success(e)) => r == convert(e)
      case _ => false
    }
  }
}
