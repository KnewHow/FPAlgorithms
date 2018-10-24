package test.fp.algorithms.basic

import org.scalatest.FlatSpec
import fp.algorithms.common.Logger.Logger
import fp.algorithms.basic._
import prop.gen.Gen

class InsertionSortSpec extends FlatSpec {
  "test INSERTION-SORT" should "succeed" in {
    val g = Gen.listOfN(1000, Gen.choose(1, 1000))
    val p = Sorted.sortedLaw(g)(a => Sorted.insertionSort(a)((a, b) => a > b))(
      (a, b) => a >= b)
    assert(p.test())
  }
}
