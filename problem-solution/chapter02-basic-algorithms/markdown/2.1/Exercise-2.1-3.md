## Q:
Consider the searching problem: Input: A sequence of n numbers `A = (a1, a2 ... an)` and a value `v`
Output: An index i such that `a[i] == v` or the special value NIL if `v`  does not appear in `A`.
Write pseudocode for linear search, which scans through the sequence, looking
for `v`. Using a loop invariant, prove that your algorithm is correct. Make sure that
your loop invariant fulfills the three necessary properties.

## A:
The code of Scala, you can refer following:
```Scala
 def lineSearch[A](a: Seq[A], element: A): Int = {
    var i = 0
    while (i < a.size) {
      if (a(i) == element) {
        return i
      }
      i += 1
    }
    -1
}
```

Initialization: We let i =0, scan sequence from first element to last element

Maintenance: If element in current index don't equal `v`, we will let `i = i + 1` to compare next element in next loop. So it will scan through the sequence

Termination: If we find a index whose element equal `v`, the loop will be termiated and return it. Otherwise it will scan to last element, at current time, we has knew none of elements can equal `v`, so we return -1(Nil)
