package test.fp.algorithms.probabilisticAndrandom

import org.scalatest.FlatSpec
import fp.algorithms.probabilisticAndrandom.Random

class RandomSpec extends FlatSpec {
  "test random(a,b) implement with random(0,1)" should "succeed" in {
    val a = 0
    val b = 200
    val re = List.fill(1000)(0).map { r =>
      val rv = Random.random(a, b)
      if (rv >= a && rv <= b) true else false
    }
    assert(re.forall(r => r))
  }
}
