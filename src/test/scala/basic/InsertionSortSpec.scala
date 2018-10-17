package test.fp.algorithms.basic

import org.scalatest.FlatSpec
import fp.algorithms.common.Logger.Logger
import fp.algorithms.basic._
import prop.gen.Gen

class InsertionSortSpec extends FlatSpec {
  "test INSERTION-SORT" should "succeed" in {
    val g = Gen.listOfN(10000, Gen.choose(1, 1000))
    val p = SortedLaw.law(g)(a => Sorted.insertionSort(a))
    assert(p.test())
  }
}
