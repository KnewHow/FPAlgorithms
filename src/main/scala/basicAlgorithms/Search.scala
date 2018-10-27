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
  def binarySearchRecursive[A](a: Seq[A], element: A)(
    f: (A, A) => Boolean): Int = {
    def binarySearchImpl[A](
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
          binarySearchImpl(a, element, mid + 1, endIndex)(f)
        } else {
          binarySearchImpl(a, element, beginIndex, mid)(f)
        }
      }
    }
    binarySearchImpl(a, element, 0, a.size)(f)
  }

  def binarySearch[A](a: Seq[A], element: A)(lt: (A, A) => Boolean): Int = {
    var b = 0
    var e = a.size
    while (b < e) {
      val mid  = (b + e) / 2
      val midV = a(mid)
      if (midV == element) {
        return mid
      } else if (lt(midV, element)) {
        b = mid + 1
      } else {
        e = mid
      }
    }
    -1
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
