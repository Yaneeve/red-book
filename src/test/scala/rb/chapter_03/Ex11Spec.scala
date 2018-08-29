package rb.chapter_03

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

import scala.util.{Success, Try}
import scala.{List => sList}

object Ex11Spec extends Properties("03.09") {

  import Ex11.{sum, product, length}

  private val ints = Gen.containerOf[sList, Int](Gen.chooseNum(Int.MinValue, Int.MaxValue))
  private val ds = Gen.containerOf[sList, Double](Gen.chooseNum(Double.MinValue, Double.MaxValue))

  property("sum") =  forAll(ints) { l: sList[Int] =>
    val real = Try(l.sum)
    val ex = Try(sum(List(l : _*)))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case _ => false
    }
  }

  property("product") =  forAll(ds) { l: sList[Double] =>
    val real = Try(l.product)
    val ex = Try(product(List(l : _*)))
    (real, ex) match {
      case (Success(r), Success(e)) =>
        if (r.isNaN && e.isNaN) true
        else r == e
      case _ => false
    }
  }

  property("length") =  forAll(ints) { l: sList[Int] =>
    val real = Try(l.length)
    val ex = Try(length(List(l : _*)))
    (real, ex) match {
      case (Success(r), Success(e)) => r == e
      case _ => false
    }
  }
}
