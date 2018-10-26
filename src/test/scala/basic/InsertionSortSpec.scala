package test.fp.algorithms.basic

import org.scalatest.FlatSpec
import fp.algorithms.common.Logger.Logger
import fp.algorithms.basic._
import prop.gen._

class InsertionSortSpec extends FlatSpec {
  "test INSERTION-SORT" should "succeed" in {
    val g = Gen.listOfN(1000, Gen.choose(1, 1000))
    val p = Sorted.sortedLaw(g)(a => Sorted.insertionSort(a)((a, b) => a > b))(
      (a, b) => a >= b)
    assert(p.test())
  }

  "test INSERTION-SORT desc" should "suceed" in {
    val g = Gen.listOfN(10, Gen.choose(1, 1000))
    val p = Prop.forAll(g) { s =>
      val r = Sorted.insertionSort(s)(_ > _)
      val h = r.head
      !s.exists(_ > h)
    }
    assert(p.test())
  }
}
