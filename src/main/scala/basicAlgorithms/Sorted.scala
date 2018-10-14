package fp.algorithms.basic

import fp.algorithms.common.Logger.Logger

/**
 * Sorting object, we will implement INSERTION-SORTED and MERGE-SORT IN it.
 */
object Sorted {
  def insertionSort(a: Seq[Int]): Seq[Int] = {
    val w = a.toBuffer
    var i = 0
    while (i < w.size - 1) {
      var j = i + 1
      while (j > 0 && (w(j) < w(j - 1))) {
        var tmp = w(j - 1)
        var t2  = w(j)
        w.update(j - 1, w(j))
        w.update(j, tmp)
        j -= 1
      }
      i += 1
    }
    w.toSeq
  }
}
