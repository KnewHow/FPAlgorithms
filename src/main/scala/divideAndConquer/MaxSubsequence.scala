package fp.algorithms.divideAndConquer

import fp.algorithms.common.Logger.Logger
import prop.gen.{Prop, Gen}

object MaxSubSequence {
  type From   = Int
  type To     = Int
  type MaxSum = Int
  def maxSubSequence(a: IndexedSeq[Int]): (From, To, MaxSum) =
    maxSubSequence(0, a.size, a)

  def maxSubSequence(f: From, t: To, a: IndexedSeq[Int]): (From, To, MaxSum) = {
    if (a.isEmpty) {
      (-1, 0, 0)
    } else if (t - f == 1) {
      (f, t, a(f))
    } else {
      val mid = (f + t) / 2
      val l   = a.slice(f, mid)
      val r   = a.slice(mid, t)
      val r1  = maxSubSequence(f, mid, a)
      val r2  = maxSubSequence(mid, t, a)
      val rm  = getMaxSubSequenceCrossingMid(a, f, t)
      if (r1._3 > r2._3 && r1._3 > rm._3) {
        r1
      } else if (r2._3 > r1._3 && r2._3 > rm._3) {
        r2
      } else {
        rm
      }
    }
  }

  /**
   * query left max-sub-sequence and right max-sub-sequence
   * then add them
   */
  private def getMaxSubSequenceCrossingMid(
    a: IndexedSeq[Int],
    f: From,
    t: To
  ): (From, To, MaxSum) = {
    val mid           = (f + t) / 2
    var maxLeftSum    = Int.MinValue
    var maxRightSum   = Int.MinValue
    var sumR          = 0
    var sumL          = 0
    var i             = mid - 1
    var j             = mid
    var maxLeftIndex  = i
    var maxRightIndex = j
    while (i >= 0) {
      sumL += a(i)
      if (sumL > maxLeftSum) {
        maxLeftSum = sumL
        maxLeftIndex = i
      }
      i -= 1
    }

    while (j < a.size) {
      sumR += a(j)
      if (sumR > maxRightSum) {
        maxRightSum = sumR
        maxRightIndex = j
      }
      j += 1
    }
    (maxLeftIndex, maxRightIndex + 1, maxLeftSum + maxRightSum)
  }

  def maxSubSequenceWithBruteFroce(a: IndexedSeq[Int]): (From, To, MaxSum) = {
    if (a.isEmpty) {
      (-1, 0, 0)
    } else {
      var maxSum = (0, 0, Int.MinValue)
      for (i <- 1 to a.size) {
        val r = getMaxSubSequenceSumByLength(a, i)
        if (r._3 > maxSum._3) maxSum = r
      }
      maxSum
    }

  }

  private def getMaxSubSequenceSumByLength(
    a: IndexedSeq[Int],
    length: Int): (From, To, MaxSum) = {
    var i      = 0
    var maxSum = Int.MinValue
    var f      = 0
    var t      = length
    while (i + length <= a.size) {
      val subSequence = a.slice(i, i + length)
      val cSum        = subSequence.foldLeft(0)(_ + _)
      if (cSum > maxSum) {
        maxSum = cSum
        f = i
        t = i + length
      }
      i += 1
    }
    (f, t, maxSum)
  }

  def law(gen: Gen[List[Int]]): Prop = Prop.forAll(gen) { g =>
    maxSubSequence(g.toIndexedSeq)._3 == maxSubSequence(g.toIndexedSeq)._3
  }
}
