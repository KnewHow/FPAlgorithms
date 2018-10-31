## Q
Consider sorting n numbers stored in array A by first finding the smallest element
of A and exchanging it with the element in `A[1]`. Then find the second smallest
element of A, and exchange it with `A[2]`. Continue in this manner for the first `n - 1`
elements of A. Write pseudocode for this algorithm, which is known as selection
sort. What loop invariant does this algorithm maintain? Why does it need to run
for only the first `n - 1` elements, rather than for all `n` elements? Give the best-case
and worst-case running times of selection sort in O notation.

## A
### Code with Scala
```Scala
/**
* SELECTIN-SORT
* First, we need find the smallest elemetn in whole sequence then swap it with a[0]
* Second, we need find the second smallest element in a[1] to a[n-1], then swap it with a[2]
* ...
* Last, we need to find n-1 smallest element in a[n-2] to a[n-1]
* @param a A sequence will be sorted
* @param lt Afunction represnt first element is less than second elemet, if yes, its result is true, otherwise is false
* @return A sequence has been sorted
* @Author KnewHow
* @Date 2018-10-27
*/
def selectionSorted[A](a: Seq[A])(lt: (A, A) => Boolean): Seq[A] = {
    val buffer = a.toBuffer
    var i      = 0
    var j      = 0
    while (i < buffer.size - 1) {
      j = i + 1
      // The minimal element in head in left sequence at first
      var minimalIndex = i
      while (j < buffer.size) {
        if (lt(buffer(j), buffer(minimalIndex))) {
          minimalIndex = j
        }
        j += 1
      }
      swap(buffer, i, minimalIndex)
      i += 1
    }
    buffer.toSeq
}
```
### Loop Invariant
Initialization: Find the smallest element in whole sequence, so we let `i = 0` and let `j = i + 1`, It can scan from `a[1]` to `a[n-1]` to find a element whose less than `a[0]`, then we swap them postion in original sequence.

Maintennace: After find the smallest element, we will find the second smallest scanning `a[2]` to `a[n-1]`, then swap it. Similarly find the third, fouth ... n-1th smallest element.

Termination: When we has foud the n-1th smallest element, the nth element is at the end of the sequence, so the whole sequence is sorted.

### Running Time
In order to find the smallest element, we need to scan `n-1` elements.

Finding second smallest element, we need to scan `n-2` elements.

Finding third smallest element, we need to scan `n-3` element.

...

Finding the n-1th smallest element, we need to scan `1`.


So the T(n) = (n-1) + (n-2) + ... + 1 = n(n-1) / 2 => O(n^2).

In best-cases, all whole sequence is sorted by ASC, but the
algorithm don't first element is smallest, it also need to scan `n-1` element to find the smallest, so the T(n) => O(n^2).

In wrost-cases, it also need to scan all element to find smallest elements, so T(n) => O(n^2).

So, in any input cases,the SELECTION-SORT running time is O(n^2).
