package test.fp.algorithms.basic

import org.scalatest.FlatSpec
import fp.algorithms.common.Logger.Logger
import fp.algorithms.basic._
import prop.gen.Gen

class MergeSortSpec extends FlatSpec {
  "test merge-sort" should "succeed" in {
    val g = Gen.listOfN(10000, Gen.choose(1, 10))
    val p =
      Sorted.sortedLaw(g)(a => Sorted.mergeSorted(a)((a, b) => a < b))((a, b) =>
        a <= b)
    assert(p.test())
  }
}
