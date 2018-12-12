package fp.algorithms.basic

import scala.collection.mutable.Buffer

object Swap {

  /**
   * Swapping two element of a ArrayBuffer, this function has side effective,
   * but in order to make more effective, we use it
   *
   */
  def swap[A](a: Buffer[A], i: Int, j: Int): Buffer[A] = {
    var t = a(i)
    replace(a, i, a(j))
    replace(a, j, t)
    a
  }

  /**
   * replace a element with another element
   * @param a The Buffer we will replace element in it
   * @param index The index of element will be replaced
   * @param element The new element will be put in the index
   * @Author KnewHow
   * @Create 2018-10-24
   */
  def replace[A](a: Buffer[A], index: Int, element: A): Buffer[A] = {
    a.update(index, element)
    a
  }
}
