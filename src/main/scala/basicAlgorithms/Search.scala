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
   * @param a A sorted sequence
   * @param element The element will be search in sequence
   * @param f Whether first element less than second element
   * @return The index of the element in this sequence if find it, otherwise return -1.
   */
  def binaySearch[A](a: Seq[A], element: A)(f: (A, A) => Boolean): Int = {
    // binaySearchImpl(a,)
    1
  }

  private def binaySearchImpl[A](
    a: Seq[A],
    element: A,
    beginIndex: Int,
    endIndex: Int)(f: (A, A) => Boolean): Int = {
    if (endIndex - beginIndex < 1) {
      -1
    } else {
      val mid = (beginIndex + endIndex) / 2
      if (a(mid) == element) {
        mid
      }
    }
    1
  }
}
