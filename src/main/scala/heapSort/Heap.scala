package fp.algorithms.heapSort

import fp.algorithms.basic.Swap._
import scala.collection.mutable.{Buffer, ArrayBuffer}
import fp.algorithms.common.Logger.Logger

// define heap and first element is not
case class Heap(elements: Seq[Int], size: Int)

object Heap {
  // heapify heap with assigned index
  def maxHeapify(h: Heap, index: Int): Heap = {

    def doMaxHeapity(b: Buffer[Int], i: Int): Buffer[Int] = {
      val l        = i * 2
      val r        = i * 2 + 1
      var maxIndex = i
      if (l <= h.size && b(l) > b(i)) {
        maxIndex = l
      } else {
        maxIndex = i
      }
      if (r <= h.size && b(r) > b(maxIndex)) {
        maxIndex = r
      }
      if (maxIndex != i) {
        swap(b, maxIndex, i)
        doMaxHeapity(b, maxIndex)
      } else {
        b
      }
    }

    h.copy(elements = doMaxHeapity(h.elements.toBuffer, index))

  }

  def buildHeap(seq: Seq[Int]): Heap = {
    val size        = seq.size
    var parentIndex = size / 2
    // make element from 1 .. size, because 0 * 2
    // is meanless
    var heap = Heap(Seq(0) ++ seq, seq.size)
    while (parentIndex > 0) {
      heap = maxHeapify(heap, parentIndex)
      parentIndex -= 1
    }
    heap
  }

  def heapSort(seq: Seq[Int]): Seq[Int] = {
    var buffer = new ArrayBuffer[Int]()
    var heap   = buildHeap(seq)
    var couter = seq.size
    while (couter > 0) {
      val hb = heap.elements.toBuffer
      swap(hb, 1, couter)
      val max = hb(couter)
      max +=: buffer
      couter -= 1
      heap = Heap(hb.toSeq, couter)
      heap = maxHeapify(heap, 1)
    }
    buffer.toSeq
  }
}
