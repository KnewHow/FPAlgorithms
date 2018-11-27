package fp.algorithms.divideAndConquer

import fp.algorithms.common.Logger.Logger
import prop.gen.{Prop, Gen}

object MatrixOps {
  type Matrix = Array[Array[Int]]

  def product(a: Matrix, b: Matrix): Matrix = {
    if (a.size == 1 && a.head.size == 1) {
      Array(Array(a(0)(0) * b(0)(0)))
    } else {
      val (a11, a12, a21, a22) = partitionFour(a)
      val (b11, b12, b21, b22) = partitionFour(b)

      val s1  = b12 - b22
      val s2  = a11 + a12
      val s3  = a21 + a22
      val s4  = b21 - b11
      val s5  = a11 + a22
      val s6  = b11 + b22
      val s7  = a12 - a22
      val s8  = b21 + b22
      val s9  = a11 - a21
      val s10 = b11 + b12

      val p1 = a11 * s1
      val p2 = s2 * b22
      val p3 = s3 * b11
      val p4 = a22 * s4
      val p5 = s5 * s6
      val p6 = s7 * s8
      val p7 = s9 * s10

      val c11 = p5 + p4 - p2 + p6
      val c12 = p1 + p2
      val c21 = p3 + p4
      val c22 = p5 + p1 - p3 - p7
      combine(c11, c12, c21, c22)
    }
  }

  // partition matrix into four sub-matrix
  def partitionFour(m: Matrix): (
    Matrix,
    Matrix,
    Matrix,
    Matrix
  ) = {
    val subSize = m.size / 2
    val a       = Array.ofDim[Int](subSize, subSize)
    val b       = Array.ofDim[Int](subSize, subSize)
    val c       = Array.ofDim[Int](subSize, subSize)
    val d       = Array.ofDim[Int](subSize, subSize)
    for {
      i <- 0 until m.size
      j <- 0 until m.head.size
    } {
      if (i < subSize && j < subSize) {
        a(i)(j) = m(i)(j)
      } else if (i >= subSize && j < subSize) {
        c(i - subSize)(j) = m(i)(j)
      } else if (i < subSize && j >= subSize) {
        b(i)(j - subSize) = m(i)(j)
      } else {
        d(i - subSize)(j - subSize) = m(i)(j)
      }
    }
    (a, b, c, d)
  }

  // combine four sub-matrix into a bigger matrix
  def combine(
    c11: Matrix,
    c12: Matrix,
    c21: Matrix,
    c22: Matrix
  ): Matrix = {
    val subSize = c11.size
    val r       = Array.ofDim[Int](c11.size * 2, c11.head.size * 2)
    for {
      i <- 0 until c11.size
      j <- 0 until c11.head.size
    } {
      r(i)(j) = c11(i)(j)
      r(i)(j + subSize) = c12(i)(j)
      r(i + subSize)(j) = c21(i)(j)
      r(i + subSize)(j + subSize) = c22(i)(j)
    }
    r
  }

  def plusMatrix(a: Matrix, b: Matrix): Matrix = {
    val r = Array.ofDim[Int](a.size, a.head.size)
    for {
      i <- 0 until a.size
      j <- 0 until a.head.size
    } {
      r(i)(j) = a(i)(j) + b(i)(j)
    }
    r
  }

  def minusMatrix(a: Matrix, b: Matrix): Matrix = {
    val r = Array.ofDim[Int](a.size, a.head.size)
    for {
      i <- 0 until a.size
      j <- 0 until a.head.size
    } {
      r(i)(j) = a(i)(j) - b(i)(j)
    }
    r
  }

  def toString(m: Matrix) = {
    m.deep.mkString("--^--")
  }

  // force matrix product
  def originalProduct(a: Matrix, b: Matrix): Matrix = {
    val r = Array.ofDim[Int](a.size, a.head.size)
    for {
      i <- 0 until a.size
      j <- 0 until a.head.size
    } {
      var sum = 0
      for (k <- 0 until a.size) {
        var t = a(i)(k) * b(k)(j)
        sum = sum + t
      }
      r(i)(j) = sum
    }
    r
  }

  def law(a: Gen[Matrix], b: Gen[Matrix]): Prop = {
    Prop.forAll(
      for {
        m <- a
        n <- b
      } yield (m, n)
    ) {
      case (m, n) =>
        product(m, n).deep == originalProduct(m, n).deep

    }
  }

  // use implicit, make you write `a + b` directory
  implicit def toMatrix(m: Array[Array[Int]]): MatrixOps = MatrixOps(m)
  case class MatrixOps(val m: Array[Array[Int]]) {
    def +(n: Matrix): Matrix = plusMatrix(m, n)
    def -(n: Matrix)         = minusMatrix(m, n)
    def *(n: Matrix)         = product(m, n)
  }
}
