package fp.algorithms.probabilisticAndrandom

import prop.gen.Gen
import scala.math._
import fp.algorithms.basic.Swap.swap
import prop.random.{Random => PR}
import fp.algorithms.basic.Swap
object Random {
  def randomUnit: Int = PR.choose(0, 1)

  def randomBasede: Int = PR.choose(0, 1)

  def random(a: Int, b: Int): Int = {
    if (a == b) {
      a
    } else {
      val mid = (a + b) / 2
      val r   = randomUnit
      if (r == 0) random(a, floor(mid).toInt)
      else random(ceil(mid).toInt, b)
    }
  }

  def randowUnbiased: Int = {
    while (true) {
      val a = randomBasede
      val b = randomBasede
      if (a > b) {
        return 1
      } else if (a < b) {
        return 0
      }
    }
    -1
  }

  def permuteSort(a: Seq[Int]): Seq[Int] = {
    val n       = a.size
    val rSeq    = PR.choose(1, n * n * n, n)
    val combine = rSeq.zip(a)
    combine.sortWith {
      case (f, s) => f._1 < s._1
    }.map(_._2)
  }

  def randomizeInPlace(a: Seq[Int]): Seq[Int] = {
    val buffer = a.toBuffer
    val n      = a.size
    for (i <- 0 until n) {
      Swap.swap(buffer, i, PR.choose(i, n - 1))
    }
    buffer.toList.toSeq
  }
}
