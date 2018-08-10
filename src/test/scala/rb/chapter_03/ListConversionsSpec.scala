package rb.chapter_03

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}
import rb.chapter_03.ListConversions.convert

import scala.util.{Failure, Success, Try}
import scala.{List => sList}

object ListConversionsSpec extends Properties("02.02") {

  val list = Gen.containerOf[sList, Int](Gen.chooseNum(Int.MinValue, Int.MaxValue))

  property("conversion") =  forAll(list) { l: sList[Int] =>
    l == convert(List(l : _*))
  }
}
