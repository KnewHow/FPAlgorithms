## Q:
Rewrite the M ERGE procedure so that it does not use sentinels, instead stopping
once either array L or R has had all its elements copied back to A and then copying
the remainder of the other array back into A.

## A:
```Scala
/**
* merge two sorted sequence into a sequence
*/
private def merge[A](l: Seq[A], r: Seq[A])(f: (A, A) => Boolean): Seq[A] = {
    import scala.collection.mutable.ArrayBuffer
    var i  = 0
    var j  = 0
    val rs = ArrayBuffer[A]()
    while (i < l.size && j < r.size) {
      if (f(l(i), r(j))) {
        rs += l(i)
        i += 1
      } else {
        rs += r(j)
        j += 1
      }
    }

    while (i < l.size) {
      rs += l(i)
      i += 1
    }
    while (j < r.size) {
      rs += r(j)
      j += 1
    }
    rs.toSeq
}
```
