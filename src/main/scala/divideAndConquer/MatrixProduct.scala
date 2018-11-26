package fp.algorithms.divideAndConquer

import fp.algorithms.common.Logger.Logger

object MatrixOps {
  type Matrix = Array[Array[Int]]

  def product(a: Matrix, b: Matrix): Matrix = {
    if (a.size == 1 && a.head.size == 1) {
      Array(Array(a(0)(0) * b(0)(0)))
    } else {
      val (a11, a12, a21, a22) = partitionFour(a)
      val (b11, b12, b21, b22) = partitionFour(b)
      val s1                   = minusMatrix(b12, b22)
      val s2                   = plusMatrix(a11, a12)
      val s3                   = plusMatrix(a21, a22)
      val s4                   = minusMatrix(b21, b11)
      val s5                   = plusMatrix(a11, a22)
      val s6                   = plusMatrix(b11, b22)
      val s7                   = minusMatrix(a12, a22)
      val s8                   = plusMatrix(b21, b22)
      val s9                   = minusMatrix(a11, a21)
      val s10                  = plusMatrix(b11, b12)

      val p1  = product(a11, s1)
      val p2  = product(s2, b22)
      val p3  = product(s3, b11)
      val p4  = product(a22, s4)
      val p5  = product(s5, s6)
      val p6  = product(s7, s8)
      val p7  = product(s9, s10)
      val c11 = plusMatrix(minusMatrix(plusMatrix(p4, p5), p2), p6)
      val c12 = plusMatrix(p1, p2)
      val c21 = plusMatrix(p3, p4)
      val c22 = minusMatrix(minusMatrix(plusMatrix(p5, p1), p3), p7)
      Logger.info(s"p2->${toString(p4)}")
      combine(c11, c12, c21, c22)
    }
  }

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

  // implicit def toMatrix(m: Array[Array[Int]]) = MatrixOps2(m)
  // case class MatrixOps2(val m: Array[Array[Int]]) {
  //   def plus(n: Matrix): Matrix = plusMatrix(m, n)
  //   def -(n: Matrix)            = minusMatrix(m, n)
  // }
}
