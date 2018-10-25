package fp.algorithms.basic

import fp.algorithms.common.Logger.Logger
import prop.gen._
import scala.collection.mutable.Buffer

/**
 * Search tool
 * @Author KnewHow
 * @Date 2018-10-25
 */
object Search {

  /**
   * BINARY-SEARCH
   * @param a A sorted sequence
   * @param element The element will be search in sequence
   * @param f Whether first element less than second element
   * @return The index of the element in this sequence if find it, otherwise return -1.
   */
  def binaySearch[A](a: Seq[A], element: A)(f: (A, A) => Boolean): Int = {
    def binaySearchImpl[A](
      a: Seq[A],
      element: A,
      beginIndex: Int,
      endIndex: Int)(f: (A, A) => Boolean): Int = {
      if (endIndex - beginIndex < 1) {
        -1
      } else {
        val mid  = (beginIndex + endIndex) / 2
        val midV = a(mid)
        if (midV == element) {
          mid
        } else if (f(midV, element)) {
          binaySearchImpl(a, element, mid + 1, endIndex)(f)
        } else {
          binaySearchImpl(a, element, beginIndex, mid)(f)
        }
      }
    }

    binaySearchImpl(a, element, 0, a.size)(f)
  }

  def lineSearch[A](a: Seq[A], element: A): Int = {
    var i = 0
    while (i < a.size) {
      if (a(i) == element) {
        return i
      }
      i += 1
    }
    -1
  }

}
