package fp.algorithms.basic

import fp.algorithms.common.Logger.Logger
import prop.gen._

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

/**
 * Sorting Law, the first element of the sorted result list is minimal element is original,
 * So any element
 *
 */
object SortedLaw {
  def law(input: Gen[List[Int]])(sortF: Seq[Int] => Seq[Int]): Prop =
    Prop.forAll(input) { a =>
      val r    = sortF(a)
      val head = r.headOption
      head.map(h => !r.exists(_ < h)).getOrElse(true) && r.size == a.size
    }

}
