package test.fp.algorithms.divideAndConquer

import org.scalatest.FlatSpec
import fp.algorithms.divideAndConquer.MaxSubSequence
import fp.algorithms.common.Logger.Logger

class MaxSubSequenceSpec extends FlatSpec {
  "test maxSubsequence " should "succeed" in {
    val a = IndexedSeq(1, -2, 3, -4, 7, -3, 4, 1)
    // val a = IndexedSeq(1, -2, 3)
    val r = MaxSubSequence.maxSubSequence(a)
    assert(r._3 == 9 && a.slice(r._1, r._2).foldLeft(0)(_ + _) == 9)
  }

  "test maxSubSequence with all nagitive" should "succeed" in {
    val a = IndexedSeq(-3, -2, -1, -6, -8)
    val r = MaxSubSequence.maxSubSequence(a)
    assert(r._3 == -1)
  }

  "test maxSubSequence with brute-force way" should "succeed" in {
    val a = IndexedSeq(1, -2, 3, -4, 7, -3, 4, 1)
    val r = MaxSubSequence.maxSubSequenceWithBruteFroce(a)
    assert(r._3 == 9)
  }
}
