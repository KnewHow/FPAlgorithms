package fp.algorithms.basic

import fp.algorithms.common.Logger.Logger
import prop.gen._
import scala.collection.mutable.Buffer

/**
 * Binary number operators
 * @Author KnewHow
 * @Date 2018-10-27
 */
object BinaryOps {

  /**
   * Adding two binary number which store with collection.
   * In this funciton, we just need scan the collection then add corresponding element,
   * but we need a variable number to store Carray.
   */
  def add(a: Seq[Int], b: Seq[Int]): Seq[Int] = {
    var as     = a.size - 1
    var bs     = b.size - 1
    var carry  = 0
    val buffer = Buffer[Int]()
    // `a` and `b` with common length
    while (as >= 0 && bs >= 0) {
      val v = a(as) + b(bs) + carry
      if (v > 1) {
        carry = 1
        (v - 2) +=: buffer
      } else {
        carry = 0
        v +=: buffer
      }
      as -= 1
      bs -= 1
    }

    // when `b` is finished, do `a` only
    while (as >= 0) {
      val v = a(as) + carry
      if (v > 1) {
        carry = 1
        (v - 2) +=: buffer
      } else {
        carry = 0
        v +=: buffer
      }
      as -= 1
    }
    // when `a` is finished, do `b` only
    while (bs >= 0) {
      val v = b(bs) + carry
      if (v > 1) {
        carry = 1
        (v - 2) +=: buffer
      } else {
        carry = 0
        v +=: buffer
      }
      bs -= 1
    }
    carry +=: buffer
    buffer.toSeq
  }

  /**
   * The result of  two binary number add should equal convert them to decimal then add result.
   * In this case, we use some Scala/Java API to convert binary number to decimal number. It will make our code elegent.
   */
  def addLaw: Prop = {
    val ga = Gen.listOfN(Gen.choose(1, 1000), Gen.choose(0, 2))
    val gb = Gen.listOfN(Gen.choose(1, 1000), Gen.choose(0, 2))

    val g: Gen[(List[Int], List[Int])] = ga.map2(gb)(_ -> _)

    Prop.forAll(g) { s =>
      val r  = add(s._1, s._2)
      val rB = BigInt(r.foldLeft("")(_ + _), 2)
      val aB = BigInt(s._1.foldLeft("")(_ + _), 2)
      val bB = BigInt(s._2.foldLeft("")(_ + _), 2)
      rB == aB + bB
    }
  }
}
