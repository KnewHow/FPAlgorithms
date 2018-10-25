package test.fp.algorithms.basic

import org.scalatest.FlatSpec
import fp.algorithms.common.Logger.Logger
import fp.algorithms.basic._
import prop.gen._

class LineSearchAndBinarySearchPerformanceSpec extends FlatSpec {
  val list: List[List[Int]] =
    Gen.run(Gen.listOfN(10000, Gen.choose(1, 100000))).take(10).toList

  val randomElement = list.map { l =>
    val r  = new scala.util.Random
    val r1 = 0 + r.nextInt((l.size))
    l(r1)
  }

  val testCases = list.zip(randomElement)

  "binary search performace" should "better than line search" in {
    val b1 = System.currentTimeMillis
    val r1 = testCases.forall { r =>
      Search.lineSearch(r._1, r._2) != -1
    }
    val b2 = System.currentTimeMillis
    val r2 = testCases.forall { r =>
      val rs = Search.binaySearch(
        Sorted.mergeSorted(r._1)(_ < _),
        r._2
      )(_ < _)
      rs != -1
    }
    val e = System.currentTimeMillis

    val lineTook  = b2 - b1
    val binayTook = e - b2
    Logger.info(s"line search took -> $lineTook")
    Logger.info(s"binary search took -> $binayTook")
    assert(r1 && r2 && (binayTook < lineTook))
  }
}
