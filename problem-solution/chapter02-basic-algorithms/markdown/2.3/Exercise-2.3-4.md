
## Q
We can express insertion sort as a recursive procedure as follows. In order to sort `A[1 ... n]`, we recursively sort `A[1 ... n-1]` and then insert A[n] into the sorted array `A[1 ... n-1]`. Write a recurrence for the running time of this recursive version of insertion sort.
## A
```Scala
/**
* implement INSERTION-SORT by recursive. If we need sort n element, we can sort n-1 at first, then
* sort last elemet with previous result
* @param a A sequcen will be sorted
* @param f Whether first parameter is less than second parameter, ture is yes, otherwise is no
* @Author KnewHow
* @Date 2018-10-28
*/
def insertionSortRecursive[A](a: Seq[A])(lt: (A, A) => Boolean): Seq[A] = {
    val buffer = a.toBuffer

    /**
     * do INSERTION-SORT for current index.
     * @param b The sequence has be sorted in [0, index-1]
     * @param index The current index need to be sorted
     */
    def insertNth(b: Buffer[A], index: Int): Buffer[A] = {
      val tmp = b(index)
      var i   = index - 1
      while (i >= 0 && lt(tmp, b(i))) {
        replace(b, i + 1, b(i))
        i -= 1
      }
      replace(b, i + 1, tmp)
      b
    }

    /**
     * INSERTION-SORT implement function
     */
    def insertionSortRecursiveImp(b: Buffer[A], index: Int): Buffer[A] = {
      if (index > 0) {
        insertionSortRecursiveImp(b, index - 1)
        insertNth(b, index)
      } else {
        b
      }
    }
    insertionSortRecursiveImp(buffer, buffer.size - 1)
}
```
