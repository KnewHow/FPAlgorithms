package fp.algorithms.basic

import fp.algorithms.common.Logger.Logger
import prop.gen._
import scala.collection.mutable.Buffer

object TwoElementsSum {

  /**
   * Give a sequence and a number x, find wether exsit two
   * element in this sequence whose sum is equal x
   *
   * First we you MERGE-SORT to sort the sequence, it took `nlgn`
   * The we traverse all element, find wether exsit `x - currentElement` in this sequence, it will took n * lgn in worst cases
   * In this way, it will took 2 * nlgn => O(nlgn)
   *
   * @param a integer sequence will find two elements from it
   * @param x The value the two elements sum equal
   * @return If the two elements is exsit, return two index in original sequence, otherwise return none
   *
   */
  def find(a: Seq[Int], x: Int): Option[(Int, Int)] = {
    val sortedSeq = Sorted.mergeSorted(a)(_ < _)
  }
}
