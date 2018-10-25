package fp.algorithms.basic

import fp.algorithms.common.Logger.Logger

/**
 * A Search tool
 * @Author KnewHow
 * @Date 2018-10-25
 */
object Search {
  /**
   * BINARY-SEARCH
   * @param s A sorted sequence
   * @param a The element will be searched in the sequence
   * @return Return the index if the element is in the sequence, otherwise  return -1
   * @Author KnewHow
   * @Date 2018-10-25
   */
  def binarySearch[A](s: Seq[A],a: A):Int = {
    s.size match {
      case 0 => -1
      case 1 =>
        if(a(0) == a) 0 else -1
      case l =>

    }
  }
}
