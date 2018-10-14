package test.algorithms.sort

import org.scalatest.FlatSpec
import prop.gen._

class SortSpec extends FlatSpec {
  val g = Gen.listOfN(20, Gen.choose(20, 100))
  val p = Prop.forAll(g) { l =>
    val sL = l.sorted
    val h  = sL.headOption
    h.map(r => !l.exists(_ < r)).getOrElse(true)
  }
  assert(p.test())
}
