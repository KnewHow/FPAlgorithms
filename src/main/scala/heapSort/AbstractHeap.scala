package fp.algorithms.heapSort

import fp.algorithms.basic.Swap._
import scala.collection.mutable.ArrayBuffer

case class AbsHeap[A, B](element: Seq[HeapElement[A, B]], size: Int) {
  def map[C](f: B => C): AbsHeap[A, C] = ???
}

case class HeapElement[A, B](weight: Weight[A], value: B)

trait Weight[A] {
  def compare[A](w: Weight[A]): Int
  def modifyTo[A](w: Weight[A]): Weight[A]
  def max: Weight[A]
  def min: Weight[A]
}

object AbsHeap {
  def maxHeapify[A, B](heap: AbsHeap[A, B], i: Int): AbsHeap[A, B] = {
    val elements     = heap.element.toBuffer
    var largestIndex = i
    val l            = i * 2
    val r            = i * 2 + 1
    if (l <= heap.size && elements(l).weight.compare(elements(i).weight) > 0) {
      largestIndex = l
    } else {
      largestIndex = i
    }
    if (r <= heap.size && elements(r).weight.compare(
          elements(largestIndex).weight) > 0) {
      largestIndex = r
    }
    if (largestIndex != i) {
      val newBuffer = swap(elements, i, largestIndex)
      val newHeap   = AbsHeap(newBuffer.toSeq, heap.size)
      maxHeapify(newHeap, largestIndex)
    } else {
      heap
    }
  }
  def initMaxHeap[A, B](elements: Seq[HeapElement[A, B]]): AbsHeap[A, B] = {
    val eles   = Seq(elements.head) ++ elements
    var parent = elements.size / 2
    var heap   = AbsHeap(eles, elements.size)
    while (parent > 0) {
      heap = maxHeapify(heap, parent)
      parent -= 1
    }
    heap
  }

  def heapSort[A, B](elements: Seq[HeapElement[A, B]]): Seq[HeapElement[A, B]] = {
    var heap   = initMaxHeap(elements)
    val buffer = new ArrayBuffer[HeapElement[A, B]]()
    while (heap.size > 0) {
      val r   = dropMaxFirst(heap)
      val max = r._1
      heap = r._2
      max +=: buffer
    }
    buffer.toSeq
  }

  def dropMaxFirst[A, B](
    heap: AbsHeap[A, B]): (HeapElement[A, B], AbsHeap[A, B]) = {
    if (heap.size < 1) {
      sys.error("heap size less than 1")
    } else {
      val buffer    = heap.element.toBuffer
      val first     = buffer.head
      val newBuffer = swap(buffer, 1, heap.size)
      val tmpHeap   = AbsHeap(newBuffer.toSeq, buffer.size - 1)
      first -> maxHeapify(tmpHeap, 1)
    }
  }

  def addElementToMax[A, B](
    heap: AbsHeap[A, B],
    element: HeapElement[A, B]): AbsHeap[A, B] = {
    val buffer = heap.element.toBuffer
    buffer.append(HeapElement(element.weight.min, element.value))
    var tmpHeap = AbsHeap(buffer.toSeq, heap.size + 1)
    modifyWeight(tmpHeap, tmpHeap.size, element.weight)
  }

  def getFirst[A, B](heap: AbsHeap[A, B]): HeapElement[A, B] = {
    if (heap.size < 1) {
      sys.error("heap size less than 1")
    } else {
      heap.element.head
    }
  }
  def modifyWeight[A, B](
    heap: AbsHeap[A, B],
    index: Int,
    weight: Weight[A]): AbsHeap[A, B] = {
    if (index > heap.size) {
      sys.error(s"$index more than heap size")
    } else {
      val buffer = heap.element.toBuffer
      val oldE   = buffer(index)
      val newE   = oldE.copy(weight = weight)
      buffer.update(index, newE)
      var i = index
      while (i > 1 && buffer(i).weight.compare(buffer(i / 2).weight) > 0) {
        swap(buffer, i, i / 2)
        i = i / 2
      }
      AbsHeap(buffer, heap.size)
    }

  }

}
