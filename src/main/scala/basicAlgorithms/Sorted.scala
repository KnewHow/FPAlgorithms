package fp.algorithms.basic

import fp.algorithms.common.Logger.Logger
import prop.gen._
import scala.collection.mutable.Buffer
import fp.algorithms.basic.Swap.{swap, replace}

/**
 * Sorting object, we will implement INSERTION-SORTED and MERGE-SORT IN it.
 * @Author KnewHow 2018-10-19
 */
object Sorted {

  /**
   * @param a A sequcen will be sorted
   * @param f Whether first parameter is less than second parameter, ture is yes, otherwise is no
   */
  def insertionSort[A](a: Seq[A])(f: (A, A) => Boolean): Seq[A] = {
    val w = a.toBuffer
    var i = 1
    while (i < w.size) {
      var tmp = w(i)
      var j   = i - 1
      while (j >= 0 && f(tmp, w(j))) {
        replace(w, j + 1, w(j))
        j -= 1
      }
      replace(w, j + 1, tmp)
      i += 1
    }
    w.toSeq
  }

  /**
   * implement INSERTION-SORT by recursive. If we need sort n element, we can sort n-1 at first, then
   * sort last elemet with previous result
   * @param a A sequcen will be sorted
   * @param f Whether first parameter is less than second parameter, ture is yes, otherwise is no
   * @Author KnewHow
   * @Date 2018-10-28
   */
  def insertionSortRecursive[A](a: Seq[A])(lt: (A, A) => Boolean): Seq[A] = {
    val buffer = a.toBuffer

    /**
     * do INSERTION-SORT for current index.
     * @param b The sequence has be sorted in [0, index-1]
     * @param index The current index need to be sorted
     */
    def insertNth(b: Buffer[A], index: Int): Buffer[A] = {
      val tmp = b(index)
      var i   = index - 1
      while (i >= 0 && lt(tmp, b(i))) {
        replace(b, i + 1, b(i))
        i -= 1
      }
      replace(b, i + 1, tmp)
      b
    }

    /**
     * INSERTION-SORT implement function
     */
    def insertionSortRecursiveImp(b: Buffer[A], index: Int): Buffer[A] = {
      if (index > 0) {
        insertionSortRecursiveImp(b, index - 1)
        insertNth(b, index)
      } else {
        b
      }
    }
    insertionSortRecursiveImp(buffer, buffer.size - 1)
  }

  /**
   * It is a little, each time starting from last element then
   * find the nth smallest from `i` to `a.size -1`, So in anywhere
   *  it will take O(n^2)
   */
  def bubbleSort[A](a: Seq[A])(f: (A, A) => Boolean): Seq[A] = {
    val w = a.toBuffer
    var i = 0
    while (i < w.size - 1) {
      var j = w.size - 1
      while (j > i) {
        if (f(w(j), w(j - 1))) {
          swap[A](w, j, j - 1)
        }
        j -= 1
      }
      i += 1
    }
    w.toSeq
  }

  /**
   * SELECTIN-SORT
   * First, we need find the smallest elemetn in whole sequence then swap it with a[0]
   * Second, we need find the second smallest element in a[1] to a[n-1], then swap it with a[2]
   * ...
   * Last, we need to find n-1 smallest element in a[n-2] to a[n-1]
   * @param a A sequence will be sorted
   * @param lt Afunction represnt first element is less than second elemet, if yes, its result is true, otherwise is false
   * @return A sequence has been sorted
   * @Author KnewHow
   * @Date 2018-10-27
   */
  def selectionSorted[A](a: Seq[A])(lt: (A, A) => Boolean): Seq[A] = {
    val buffer = a.toBuffer
    var i      = 0
    var j      = 0
    while (i < buffer.size - 1) {
      j = i + 1
      // The minimal element in head in left sequence at first
      var minimalIndex = i
      while (j < buffer.size) {
        if (lt(buffer(j), buffer(minimalIndex))) {
          minimalIndex = j
        }
        j += 1
      }
      swap(buffer, i, minimalIndex)
      i += 1
    }
    // Logger.info(s"[Sorted-selectionSort]loop times>${i * j}")
    buffer.toSeq
  }

  /**
   * Meger Sort
   */
  def mergeSorted[A](a: Seq[A])(f: (A, A) => Boolean): Seq[A] = {
    if (a.size <= 1) {
      a
    } else {
      val (l, r) = a.splitAt(a.size / 2)
      val rL     = mergeSorted(l)(f)
      val rR     = mergeSorted(r)(f)
      merge(rL, rR)(f)
    }
  }

  /**
   * merge two sorted sequence into a sequence
   */
  private def merge[A](l: Seq[A], r: Seq[A])(f: (A, A) => Boolean): Seq[A] = {
    import scala.collection.mutable.ArrayBuffer
    var i  = 0
    var j  = 0
    val rs = ArrayBuffer[A]()
    while (i < l.size && j < r.size) {
      if (f(l(i), r(j))) {
        rs += l(i)
        i += 1
      } else {
        rs += r(j)
        j += 1
      }
    }

    while (i < l.size) {
      rs += l(i)
      i += 1
    }
    while (j < r.size) {
      rs += r(j)
      j += 1
    }
    rs.toSeq
  }

  /**
   * Whether the sequence is sorted
   */
  def sortedLaw[A](input: Gen[List[A]])(sortF: Seq[A] => Seq[A])(
    f: (A, A) => Boolean): Prop =
    Prop.forAll(input) { a =>
      val sorted = sortF(a)
      elementEquals(a, sorted.toList)(f) && isSorted[A](sorted)(f)
    }

  /**
   * Whether sorted List contains all elements in original List.
   * In this, `sorted` is sorted sequence,
   * so use binary search to make more effective.
   *
   * @param f Whether first element less than second element
   */
  private def elementEquals[A](original: List[A], sorted: List[A])(
    f: (A, A) => Boolean): Boolean = {
    original.size == sorted.size && original.forall { r =>
      // Search.binarySearch(sorted, r)(f) != -1
      // it is more effective than binary search
      sorted.contains(r)
    }
  }

  private def isSorted[A](sorted: Seq[A])(f: (A, A) => Boolean): Boolean = {
    var i = 0
    while (i < sorted.size - 1) {
      if (!f(sorted(i), sorted(i + 1))) {
        return false
      }
      i += 1
    }
    true
  }

}
