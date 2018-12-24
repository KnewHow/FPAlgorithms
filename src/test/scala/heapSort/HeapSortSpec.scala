package test.fp.algorithms.heapSort

import fp.algorithms.heapSort.Heap
import fp.algorithms.common.Logger.Logger
import prop.gen.Gen
import fp.algorithms.basic.Sorted
import org.scalatest.FlatSpec

class HeapSortSpec extends FlatSpec {
  "test heap sort" should "succee" in {
    val g = Gen.listOfN(1000, Gen.choose(1, 10000))
    val p =
      Sorted.sortedLaw(g)(s => Heap.heapSort(s))((a, b) => a <= b)
    assert(p.test())
  }
}
