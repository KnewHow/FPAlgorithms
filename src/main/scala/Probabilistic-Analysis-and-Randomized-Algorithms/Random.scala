package fp.algorithms.probabilisticAndrandom

import prop.gen.Gen
import scala.math._
import fp.algorithms.basic.Swap.swap

object Random {
  def randomUnit: Int = Gen.run(Gen.choose(0, 2)).take(2).toList.head

  def randomBasede: Int = Gen.run(Gen.choose(0, 2)).take(2).toList.head

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
    val rSeq    = Gen.run(Gen.choose(1, n * n * n)).take(n).toList.toSeq
    val combine = rSeq.zip(a)
    combine.sortWith {
      case (f, s) => f._1 < s._1
    }.map(_._2)
  }

  // def randomizeInPlace(a: Seq[Int]): Seq[Int] = {

  // }
}
