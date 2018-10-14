package test.fp.algorithms.basic

import org.scalatest.FlatSpec
import fp.algorithms.common.Logger.Logger
import fp.algorithms.basic.Sorted
import prop.gen._

class InsertionSortSpec extends FlatSpec {
  "test INSERTION-SORT" should "succeed" in {
    val g = Gen.listOfN(10000, Gen.choose(1, 1000))
    val p = Prop.forAll(g) { a =>
      val sortedA = Sorted.insertionSort(a)
      val h       = sortedA.headOption
      a.size > 0 &&
      h.map(min => !a.exists(_ < min)).getOrElse(true)
    }
    assert(p.test())
  }
}
