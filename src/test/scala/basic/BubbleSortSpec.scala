package test.fp.algorithms.basic

import org.scalatest.FlatSpec
import fp.algorithms.common.Logger.Logger
import fp.algorithms.basic._
import prop.gen.Gen

class BubbleSortSpec extends FlatSpec {
  "test bubble-sort" should "success" in {
    val g = Gen.listOfN(10000, Gen.choose(1, 100000))
    val p =
      Sorted.sortedLaw(g)(a => Sorted.bubbleSort(a)((a, b) => a < b))((a, b) =>
        a <= b)
    assert(p.test())
  }
}
