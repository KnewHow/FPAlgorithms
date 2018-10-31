package test.fp.algorithms.basic

import org.scalatest.FlatSpec
import fp.algorithms.common.Logger.Logger
import fp.algorithms.basic._
import prop.gen.Gen

class InversionsSpec extends FlatSpec {
  "test inversions" should "succeed" in {
    val a = Seq(2, 3, 8, 6, 1)
    val r = Inversion.impl(a)(_ < _)
    assert(r == 5)
  }

  "test fixed inversion" should "succeed" in {
    val p = Inversion.law
    assert(p.test())
  }

}
