package fp.algorithms.basic

import fp.algorithms.common.Logger.Logger
import prop.gen._
import scala.collection.mutable.Buffer

object TwoElementsSum {

  /**
   * Give a sequence and a number x, find whether exsit two
   * element in this sequence whose sum is equal x
   *
   * First we you MERGE-SORT to sort the sequence, it took `nlgn`
   * The we traverse all element, find whether exsit `x - currentElement` in this sequence, it will took n * lgn in worst cases
   * In this way, it will took 2 * nlgn => O(nlgn)
   *
   * @param a integer sequence will find two elements from it
   * @param x The value the two elements sum equal
   * @return Return a sequence, whose element is two index
   * in `a`, the a(first) + a(second) == v, If the sequence
   * is empty, it indicates there no two element can sum
   * equal `x`
   * @Author KnewHow
   * @Date 2018-10-29
   */
  def find(a: Seq[Int], x: Int): Seq[(Int, Int)] = {
    val sortedSeq = Sorted.mergeSorted(a)(_ < _)
    val fut = a.map { r =>
      val index = Search.binarySearch(sortedSeq, x - r)(_ < _)
      r -> index
    }

    fut.filter(r => r._2 != -1).map { r =>
      a.indexOf(r._1) -> a.indexOf(sortedSeq(r._2))
    }

  }

  /**
   * A Law to check whether above algorithms is right
   * If the sequence returned is empty, indicating there no
   * two elements sum equal `x`. otherwise the sum of them must equal `x`
   * @Author KnewHow
   * @Date 2018-10-29
   */
  def law: Prop = {
    val g = Gen.listOfN(1000, Gen.choose(0, 100000))
    val x = Gen.run(Gen.choose(0, 100000)).take(10).toList.head
    Prop.forAll(g) { a =>
      val r = find(a, x)
      if (r.isEmpty) {
        Logger.info(s"there no two elements sum equal $x")
        true
      } else {
        Logger.info(s"find some element sum equal ${x}")
        r.forall { e =>
          // Logger.info(
          //   s"index1 is ${e._1} value1 is ${a(e._1)}, index2 is ${e._2}, value2 is ${a(e._2)}")
          a(e._1) + a(e._2) == x
        }
      }

    }
  }
}
