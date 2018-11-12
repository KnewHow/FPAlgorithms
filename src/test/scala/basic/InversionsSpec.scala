package test.fp.algorithms.basic

import org.scalatest.FlatSpec
import fp.algorithms.common.Logger.Logger
import fp.algorithms.basic._
import prop.gen.Gen

class InversionsSpec extends FlatSpec {
  "test inversions" should "succeed" in {
    val p = Inversion.law
    assert(p.test())
  }

}
