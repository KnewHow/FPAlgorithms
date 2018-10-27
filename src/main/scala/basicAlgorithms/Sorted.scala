package fp.algorithms.basic

import fp.algorithms.common.Logger.Logger
import prop.gen._
import scala.collection.mutable.Buffer

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
   * bubble sort
   */
  def bubbleSort[A](a: Seq[A])(f: (A, A) => Boolean): Seq[A] = {
    val w = a.toBuffer
    var i = 0
    while (i < w.size - 1) {
      var j = i + 1
      while (j > 0 && f(w(j), w(j - 1))) {
        swap[A](w, j, j - 1)
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
   * Swapping two element of a ArrayBuffer, this function has side effective,
   * but in order to make more effective, we use it
   *
   */
  private def swap[A](a: Buffer[A], i: Int, j: Int): Buffer[A] = {
    var t = a(i)
    replace(a, i, a(j))
    replace(a, j, t)
    a
  }

  /**
   * replace a element with another element
   * @param a The Buffer we will replace element in it
   * @param index The index of element will be replaced
   * @param element The new element will be put in the index
   * @Author KnewHow
   * @Create 2018-10-24
   */
  private def replace[A](a: Buffer[A], index: Int, element: A): Buffer[A] = {
    a.update(index, element)
    a
  }

  /**
   * merge two sorted sequence
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
