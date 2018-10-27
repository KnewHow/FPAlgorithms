package test.fp.algorithms.basic

import org.scalatest.FlatSpec
import fp.algorithms.common.Logger.Logger
import fp.algorithms.basic._
import prop.gen.Gen

class BubbleSortWorstSpec extends FlatSpec {
  val descList =
    Gen
      .run(Gen.listOfN(1000, Gen.choose(0, 100000)))
      .take(10)
      .toList
      .map(r => r.sortWith(_ > _))

  "test bubble sort worst situation" should "succceed" in {
    descList.map { r =>
      Sorted.bubbleSort(r)(_ < _)
    }
  }
}
