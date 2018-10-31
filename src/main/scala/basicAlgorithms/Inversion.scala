package fp.algorithms.basic

import fp.algorithms.common.Logger.Logger
import prop.gen._
import scala.collection.mutable.Buffer

object Inversion {
  def impl[A](a: Seq[A])(lt: (A, A) => Boolean): Int = {
    def sortAndFind(s: Seq[A]): (Int, Seq[A]) = {
      if (s.size <= 1) {
        0 -> s
      } else {
        val (l, r) = s.splitAt(s.size / 2)
        val r1     = sortAndFind(l)
        val r2     = sortAndFind(r)
        merge(r1, r2)(lt)
      }
    }
    sortAndFind(a)._1
  }

  private def merge[A](
    r1: (Int, Seq[A]),
    r2: (Int, Seq[A])
  )(
    lt: (A, A) => Boolean
  ): (Int, Seq[A]) = {
    var counter = r1._1 + r2._1
    val s1      = r1._2
    val s2      = r2._2
    var i       = 0
    var j       = 0
    val buffer  = Buffer[A]()
    while (i < s1.size && j < s2.size) {
      if (lt(s2(j), s1(i))) {
        buffer += s2(j)
        j += 1

        /**
         * if s1(i) is bigger than s2(j), indicating i to s1.size is bigger than s2(j), the inversion is s1.size - i
         *
         */
        counter += (s1.size - i)
      } else {
        buffer += s1(i)
        i += 1
      }
    }

    while (i < s1.size) {
      buffer += s1(i)
      i += 1
      counter += (s2.size - j)
    }
    while (j < s2.size) {
      buffer += s2(j)
      j += 1
    }
    counter -> buffer.toSeq
  }

  def law: Prop = {
    val g = Gen
      .listOfN(Gen.choose(100, 1000), Gen.choose(0, 100000))
      .map(r => r.distinct)
    Prop.forAll(g) { s =>
      val r1 = impl(s)(_ < _)
      val r2 = s.map { r =>
        val index = s.indexOf(r)
        s.slice(index + 1, s.size).filter(_ < r).size
      }.sum
      r1 == r2
    }
  }
}
