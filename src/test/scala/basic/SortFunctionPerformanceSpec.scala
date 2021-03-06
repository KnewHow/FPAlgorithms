package test.fp.algorithms.basic

import org.scalatest.FlatSpec
import fp.algorithms.common.Logger.Logger
import fp.algorithms.basic._
import prop.gen.Gen

class SortFunctionPerformanceSpec extends FlatSpec {
  "merge-sort performance" should "better than insertion-sort" in {
    val s  = Gen.run(Gen.listOfN(10000, Gen.choose(1, 1000))).take(10).toList
    val b1 = System.currentTimeMillis
    s.map { r =>
      Sorted.insertionSort(r)((a, b) => a > b)
    }
    val mergeSortB = System.currentTimeMillis
    s.map { r =>
      Sorted.mergeSorted(r)((a, b) => a > b)
    }
    val sortE = System.currentTimeMillis
    Logger.info(s"insertion-sort took->${mergeSortB - b1}")
    Logger.info(s"merge-sort took->${sortE - mergeSortB}")
    assert(sortE - mergeSortB < mergeSortB - b1)
  }
}
