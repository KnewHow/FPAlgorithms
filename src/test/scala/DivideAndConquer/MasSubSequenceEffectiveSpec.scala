package test.fp.algorithms.divideAndConquer

import org.scalatest.FlatSpec
import fp.algorithms.divideAndConquer.MaxSubSequence
import fp.algorithms.common.Logger.Logger
import prop.gen.Gen

class MaxSubSequenceEffectiveSpec extends FlatSpec {

  def compare: List[(Int, Long, Long)] = {
    val a = List.iterate(1, 100)(_ + 1)
    a.map { s =>
      val g = Gen
        .run(Gen.listOfN(s, Gen.choose(-10000, 100000)))
        .take(1)
        .toList
        .head
        .toIndexedSeq

      val r = run(g)
      (s, r._1, r._2)
    }
  }

  def run(list: IndexedSeq[Int]): (Long, Long) = {
    val b1 = System.nanoTime
    val r1 = MaxSubSequence.maxSubSequence(list)
    val b2 = System.nanoTime
    val r2 = MaxSubSequence.maxSubSequenceWithBruteFroce(list)
    val b3 = System.nanoTime
    (b2 - b1, b3 - b2)
  }

  "test recursive and brute-force effective" should "succeed" in {
    val rs = compare
      .filter(r => r._2 > r._3)
      .map(r => r._1)
    Logger.info(s"length->$rs")
    succeed
  }
}
