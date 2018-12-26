package test.fp.algorithms.heapSort

import org.scalatest.FlatSpec
import fp.algorithms.heapSort._
import fp.algorithms.common.Logger.Logger
import prop.gen.Gen
import fp.algorithms.basic.Sorted

class AbsHeapSpec extends FlatSpec {
  case class Age(age: Int) extends Weight[Int] { self =>
    val value                = age
    def compare(a: Int): Int = value - a
    def max: Int             = Int.MaxValue
    def min: Int             = Int.MinValue
    def unit(a: Int)         = Age(a)
  }

  case class Person(name: String)

  val data = Seq(
    HeapElement(
      Age(20),
      Person("路飞")
    ),
    HeapElement(
      Age(15),
      Person("娜美")
    ),
    HeapElement(
      Age(10),
      Person("乔巴")
    ),
  )

  val g = Gen.listOfN(Gen.choose(100, 1000), Gen.choose(1, 10000).map { age =>
    HeapElement(
      Age(age),
      Person(s"路飞-${age}")
    )
  })

  "test absHeapSort" should "succeed" in {
    val p =
      Sorted.sortedLaw(g)(s => AbsHeap.heapSort(s))((a, b) =>
        a.weight.value <= b.weight.value)
    assert(p.test())
  }
}
