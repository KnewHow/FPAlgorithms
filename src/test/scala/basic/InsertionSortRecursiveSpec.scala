package test.fp.algorithms.basic

import org.scalatest.FlatSpec
import fp.algorithms.common.Logger.Logger
import fp.algorithms.basic._
import prop.gen._

class InsertionSortRecursiveSpec extends FlatSpec {
  "test INSERTION-SORT" should "succeed" in {
    val g = Gen.listOfN(10, Gen.choose(1, 1000))
    val p = Sorted.sortedLaw(g)(a =>
      Sorted.insertionSortRecursive(a)((a, b) => a > b))((a, b) => a >= b)
    assert(p.test())
  }
}
