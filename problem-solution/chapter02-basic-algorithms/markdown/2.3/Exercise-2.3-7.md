## Q
Describe a `O(nlgn)`-time algorithm that, given a set `S` of n integers and another integer `x`, determines whether or not there exist two elements in `S` whose sum is
exactly `x`.

## A
```Scala
/**
* Give a sequence and a number x, find whether exsit two
* element in this sequence whose sum is equal x
*
* First we you MERGE-SORT to sort the sequence, it took `nlgn`
* The we traverse all element, find whether exsit `x - currentElement` in this sequence, it will took n * lgn in worst cases
* In this way, it will took 2 * nlgn => O(nlgn)
*
* @param a integer sequence will find two elements from it
* @param x The value the two elements sum equal
* @return Return a sequence, whose element is two index
* in `a`, the a(first) + a(second) == v, If the sequence
* is empty, it indicates there no two element can sum
* equal `x`
* @Author KnewHow
* @Date 2018-10-29
*/
  def find(a: Seq[Int], x: Int): Seq[(Int, Int)] = {
    val sortedSeq = Sorted.mergeSorted(a)(_ < _)
    val fut = a.map { r =>
      val index = Search.binarySearch(sortedSeq, x - r)(_ < _)
      r -> index
    }

    fut.filter(r => r._2 != -1).map { r =>
      a.indexOf(r._1) -> a.indexOf(sortedSeq(r._2))
}
```
