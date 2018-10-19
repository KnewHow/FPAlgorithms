package fp.algorithms.basic

import fp.algorithms.common.Logger.Logger
import prop.gen._
import scala.collection.mutable.ArrayBuffer

/**
 * Sorting object, we will implement INSERTION-SORTED and MERGE-SORT IN it.
 * @Author KnewHow 2018-10-19
 */
object Sorted {
  def insertionSort(a: Seq[Int]): Seq[Int] = {
    val w = a.toBuffer
    var i = 1
    while (i < w.size) {
      var tmp = w(i)
      var j   = i - 1
      while (j >= 0 && w(j) > tmp) {
        w.update(j + 1, w(j))
        j -= 1
      }
      w.update(j + 1, tmp)
      i += 1
    }
    w.toSeq
  }

  /**
   * bubble sort
   */
  def bubbleSort(a: Seq[Int]): Seq[Int] = {
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

  /**
   * Meger Sort
   */
  def mergeSorted(a: Seq[Int]): Seq[Int] = {
    if (a.size <= 1) {
      a
    } else {
      val (l, r) = a.splitAt(a.size / 2)
      val rL     = mergeSorted(l)
      val rR     = mergeSorted(r)
      merge(rL, rR)
    }
  }

  /**
   * Swapping two element of a ArrayBuffer, this function has side effective,
   * but in order to make more effective, we use it
   *
   */
  private def swap[A](a: ArrayBuffer[A], i: Int, j: Int): ArrayBuffer[A] = {
    var t = a(i)
    a.update(i, a(j))
    a.update(j, t)
    a
  }

  /**
   * merge two sorted sequence
   */
  private def merge(l: Seq[Int], r: Seq[Int]): Seq[Int] = {
    import scala.collection.mutable.ArrayBuffer
    var i  = 0
    var j  = 0
    val rs = ArrayBuffer[Int]()
    while (i < l.size && j < r.size) {
      if (l(i) < r(j)) {
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
  def sortedLaw(input: Gen[List[Int]])(sortF: Seq[Int] => Seq[Int]): Prop = {
    ascLaw(input)(sortF) || descLaw(input)(sortF)
  }

  /**
   * Whether the sequence sorted by asc
   */
  def ascLaw(input: Gen[List[Int]])(sortF: Seq[Int] => Seq[Int]): Prop =
    Prop.forAll(input) { a =>
      val r    = sortF(a)
      val head = r.headOption
      head.map(h => !r.exists(_ < h)).getOrElse(true) && r.size == a.size
    }

  /**
   * Whether the sequence sorted by desc
   */
  def descLaw(input: Gen[List[Int]])(sortF: Seq[Int] => Seq[Int]): Prop =
    Prop.forAll(input) { a =>
      val r    = sortF(a)
      val head = r.headOption
      head.map(h => !r.exists(_ > h)).getOrElse(true) && r.size == a.size
    }
}
