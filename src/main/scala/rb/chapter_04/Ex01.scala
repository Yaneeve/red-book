package rb.chapter_04

object Ex01 {

  def map[A,B](o: Option[A])(f: A => B): Option[B] =
    o match {
      case None => None
      case Some(a) => Some(f(a))
    }

  def getOrElse[A, B>:A](o: Option[A])(default: => B): B = o match {
    case None => default
    case Some(a) => a
  }

  def flatMap[A, B](o: Option[A])(f: A => Option[B]): Option[B] = map(o)(f).getOrElse(None)

  def orElse[A, B >: A](o: Option[A])(ob: => Option[B]): Option[B] =
    getOrElse(map(o)(Some(_): Option[B]))(ob)


  def filter[A](o: Option[A])(f: A => Boolean): Option[A] = flatMap(o)({case a => 
    if (f(a)) Some(a) else None
  }) 

}
