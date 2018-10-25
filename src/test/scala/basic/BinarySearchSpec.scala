package test.fp.algorithms.basic

import org.scalatest.FlatSpec
import fp.algorithms.common.Logger.Logger
import fp.algorithms.basic._
import prop.gen._

class BinarySearchSpec extends FlatSpec {
  "test BINARY-SEARCH" should "succeed" in {
    val g = Gen.listOfN(10000, Gen.choose(0, 100000))
    val p = Prop.forAll(g) { s =>
      val element = s.head
      val sortedS = Sorted.mergeSorted(s)((a, b) => a < b)
      val index   = Search.binaySearch(sortedS, element)((a, b) => a < b)
      index != -1 && sortedS(index) == element
    }
    assert(p.test())
  }
}
