## Q
Referring back to the searching problem (see Exercise 2.1-3), observe that if the sequence A is sorted, we can check the midpoint of the sequence against `v` and eliminate half of the sequence from further consideration. The binary search algorithm repeats this procedure, halving the size of the remaining portion of the sequence each time. Write pseudocode, either iterative or recursive, for binary search. Argue that the worst-case running time of binary search is O(lgn).

## A:

### code
```Scala
/**
* BINARY-SEARCH
* @param a A sorted sequence
* @param element The element will be search in sequence
* @param lt Whether first element less than second element
* @return The index of the element in this sequence if find it, otherwise return -1.
*/
def binarySearch[A](a: Seq[A], element: A)(lt: (A, A) => Boolean): Int = {
    var b = 0
    var e = a.size
    while (b < e) {
      val mid  = (b + e) / 2
      val midV = a(mid)
      if (midV == element) {
        return mid
      } else if (lt(midV, element)) {
        b = mid + 1
      } else {
        e = mid
      }
    }
    -1
  }
```

### Running Time

`T(n) = T(n/2) + 1 = T(n/4) + 1 + 1 = T(n/(2^3)) + 3 = T(n/(2^k)) + k`

when `k = lgn` T(n) = T(1) + lgn => lgn
