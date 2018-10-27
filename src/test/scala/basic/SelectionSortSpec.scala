package test.fp.algorithms.basic

import org.scalatest.FlatSpec
import fp.algorithms.common.Logger.Logger
import fp.algorithms.basic._
import prop.gen.Gen

class SelectionSortSpec extends FlatSpec {
  "test selection sort" should "succeed" in {
    val g = Gen.listOfN(1000, Gen.choose(0, 100000))
    val p = Sorted.sortedLaw(g)(a => Sorted.selectionSorted(a)(_ < _))(_ <= _)
    assert(p.test())
  }
}
