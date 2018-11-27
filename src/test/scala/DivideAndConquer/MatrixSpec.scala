package test.fp.algorithms.divideAndConquer

import org.scalatest.FlatSpec
import fp.algorithms.divideAndConquer.MatrixOps
import fp.algorithms.common.Logger.Logger
import prop.gen.Gen

class MatrixSpec extends FlatSpec {
  type Matrix = Array[Array[Int]]
  val m = Array(
    Array(1, 1, 1, 1),
    Array(2, 2, 2, 2),
    Array(3, 3, 3, 3),
    Array(4, 4, 4, 4))
  val (a, b, c, d) = MatrixOps.partitionFour(m)
  "test partitionFour" should "succeed" in {
    assert(
      a.deep == Array(Array(1, 1), Array(2, 2)).deep &&
        b.deep == Array(Array(1, 1), Array(2, 2)).deep &&
        c.deep == Array(Array(3, 3), Array(4, 4)).deep &&
        d.deep == Array(Array(3, 3), Array(4, 4)).deep
    )
  }

  "test plusMatrix" should "succeed" in {
    val r = MatrixOps.plusMatrix(a, b)
    assert(
      r.deep == Array(
        Array(2, 2),
        Array(4, 4)
      ).deep
    )
  }

  "test minusMatrix" should "succeed" in {
    val r = MatrixOps.minusMatrix(c, a)
    assert(
      r.deep == Array(
        Array(2, 2),
        Array(2, 2)
      ).deep
    )
  }

  "test combine" should "succeed" in {
    val r = MatrixOps.combine(a, b, c, d)
    assert(r.deep == m.deep)
  }

  "test matrix product" should "succeed" in {
    val a    = Array(Array(1, 3), Array(7, 5))
    val b    = Array(Array(6, 8), Array(4, 2))
    val r    = MatrixOps.product(a, b)
    val clcR = Array(Array(18, 14), Array(62, 66))
    assert(r.deep == clcR.deep)
  }

  "test matix product with 4x4 random" should "succeed" in {
    val a = Array(
      Array(1, 1, 1, 1),
      Array(2, 2, 2, 2),
      Array(3, 3, 3, 3),
      Array(4, 4, 4, 4))

    val b = Array(
      Array(1, 3, 1, 1),
      Array(2, 10, 2, 2),
      Array(3, 3, 6, 3),
      Array(-3, 4, 4, 4))

    assert(MatrixOps.product(a, b).deep == MatrixOps.originalProduct(a, b).deep)
  }

  "test original matrixProduct" should "succeed" in {
    val a    = Array(Array(1, 3), Array(7, 5))
    val b    = Array(Array(6, 8), Array(4, 2))
    val r    = MatrixOps.originalProduct(a, b)
    val clcR = Array(Array(18, 14), Array(62, 66))
    assert(r.deep == clcR.deep)
  }

  "test matrix product with law" should "succeed" in {
    val m = getMatrixTuple
    val r = MatrixOps.law(m._1, m._2)
    assert(r.test())
  }

  def getMatrixTuple = {
    val s = Gen
      .run(
        Gen
          .choose(2, 8)
          .map { r =>
            scala.math.pow(2, r).toInt
          }
      )
      .take(1)
      .toList
      .head
    getMatrix(s) -> getMatrix(s)
  }

  def getMatrix(s: Int) = {
    val fut = Gen.listOfN(s, Gen.choose(0, 1000)).flatMap { r =>
      val m: List[Gen[List[Int]]] = r.map { l =>
        Gen.listOfN(s, Gen.choose(0, 1000))
      }
      m.foldLeft(Gen.unit(List[List[Int]]()))((a, b) => b.map2(a)(_ :: _))
    }
    fut.map(m => m.map(_.toArray).toArray)
  }
}
