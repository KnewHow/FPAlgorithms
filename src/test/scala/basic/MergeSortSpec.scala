package test.fp.algorithms.basic

import org.scalatest.FlatSpec
import fp.algorithms.common.Logger.Logger
import fp.algorithms.basic._
import prop.gen.Gen

class MergeSortSpec extends FlatSpec {
  "test merge-sort" should "succeed" in {
    val g = Gen.listOfN(100000, Gen.choose(1, 1000))
    val p = Sorted.sortedLaw(g)(a => Sorted.mergeSorted(a))
    assert(p.test())
  }
}
