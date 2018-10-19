package test.fp.algorithms.basic

import org.scalatest.FlatSpec
import fp.algorithms.common.Logger.Logger
import fp.algorithms.basic._
import prop.gen.Gen

class BubbleSortSpec extends FlatSpec {
  "test bubble-sort" should "success" in {
    val g = Gen.listOfN(10000, Gen.choose(1, 1000))
    val p = Sorted.sortedLaw(g)(a => Sorted.bubbleSort(a))
    assert(p.test())
  }
}
