package fp.algorithms.heap

case class AbsHeap[A, B](element: Seq[HeapElement[A, B]], size: Int)

case class HeapElement[A, B](weight: Weight[A], value: B)

trait Weight[A] {
  def compare(w: Weight[A]): Int
  def modifyTo(a: A)
  def max: A
  def min: A
}

object AbsHeap {
  def maxHeapify[A, B](heap: AbsHeap[A, B], i: Int)     = ???
  def initHeap[A, B](arr: AbsHeap[A, B]): AbsHeap[A, B] = ???
  def dropFirst[A, B](heap: AbsHeap[A, B]): (HeapElement[A, B], AbsHeap[A, B]) =
    ???
  def addElement[A, B](
    heap: AbsHeap[A, B],
    element: HeapElement[A, B]): AbsHeap[A, B] = ???

  def getFirst[A, B](heap: AbsHeap[A, B]): HeapElement[A, B] = ???
  def modifyWeight[A, B](
    heap: AbsHeap[A, B],
    index: Int,
    weight: A): AbsHeap[A, B] =
    ???

}
