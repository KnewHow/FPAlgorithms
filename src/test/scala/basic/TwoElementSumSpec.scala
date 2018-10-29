package test.fp.algorithms.basic

import org.scalatest.FlatSpec
import fp.algorithms.common.Logger.Logger
import fp.algorithms.basic._
import prop.gen._

class TwoElementsSumSpec extends FlatSpec {
  "test two elements sum " should "succeed" in {
    val p = TwoElementsSum.law
    assert(p.test())
  }
}
