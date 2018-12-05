package rb.chapter_04

import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Properties}
import rb.chapter_04.OptionConversions._

import scala.util.{Success, Try}
import scala.{Option => sOption}
import cats.implicits._
import cats.Applicative

object Ex03Spec extends Properties("04.03") {
  import Ex03._

  private val geni = Gen.chooseNum(Int.MinValue, Int.MaxValue)
  private val genopt = Gen.option(geni)
  private val genf: Gen[(Int, Int) => String] = Arbitrary.arbitrary[(Int, Int) => String]


  private val generators = for {
    gopt1 <- genopt
    gopt2 <- genopt
    gf <- genf
  } yield (gopt1, gopt2, gf)

  property("option map2") =  forAll(generators) { case (o1: sOption[Int], o2: sOption[Int], f: ((Int, Int) => String)) =>

    val real = Try(Applicative[sOption].map2(o1, o2)(f))
    val ex = Try(map2(convert(o1), convert(o2))(f))
    (real, ex) match {
      case (Success(r), Success(e)) => r == convert(e)
      case _ => false
    }
  }
}
