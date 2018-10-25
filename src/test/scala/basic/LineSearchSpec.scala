package test.fp.algorithms.basic

import org.scalatest.FlatSpec
import fp.algorithms.common.Logger.Logger
import fp.algorithms.basic._
import prop.gen._

class LineSearchSpec extends FlatSpec {
  val g = Gen.listOfN(10000, Gen.choose(0, 100000))
  val p = Prop.forAll(g) { s =>
    val r     = new scala.util.Random
    val r1    = 0 + r.nextInt((s.size) + 1)
    val index = Search.lineSearch(s, s(r1))
    index != -1
  }
  assert(p.test())
}
