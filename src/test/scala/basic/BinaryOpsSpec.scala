package test.fp.algorithms.basic

import org.scalatest.FlatSpec
import fp.algorithms.common.Logger.Logger
import fp.algorithms.basic._
import prop.gen.Gen

class BinaryOpsSpec extends FlatSpec {
  "test binary add ops" should "succeed" in {
    val p = BinaryOps.addLaw
    assert(p.test())
  }
}
