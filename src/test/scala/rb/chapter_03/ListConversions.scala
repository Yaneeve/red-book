package rb.chapter_03

import scala.{Nil => sNil, List => sList}

import scala.annotation.tailrec

object ListConversions {

  def convert[A](l: List[A]): sList[A] = {
    @tailrec
    def split(l: List[A], as: sList[A]): (List[A], sList[A]) = l match {
      case Nil => Nil -> as
      case Cons(h, t) => split(t, as :+ h)
    }

    split(l, sNil)._2
  }

  implicit def convertFunction[A, B](f : A => sList[B]): A => List[B] = { a :A => List(f(a): _ * ) }

}
