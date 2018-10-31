## Q
Rewrite the INSERTION-SORT procedure to sort into nonincreasing instead of non-
decreasing order.

## A:
In this case, we implement a common INSERT-SORT, the code is following:
```Scala
 /**
   * @param a A sequcen will be sorted
   * @param f Whether first parameter is less than second parameter, ture is yes, otherwise is no
   */
  def insertionSort[A](a: Seq[A])(f: (A, A) => Boolean): Seq[A] = {
    val w = a.toBuffer
    var i = 1
    while (i < w.size) {
      var tmp = w(i)
      var j   = i - 1
      while (j >= 0 && f(tmp, w(j))) {
        replace(w, j + 1, w(j))
        j -= 1
      }
      replace(w, j + 1, tmp)
      i += 1
    }
    w.toSeq
  }
```

In this funcion, we pass a function which compare first parameter with second parameter, So the function can
move element by this. If you want make the sort result ASC, you can call with:
```Scala
Sorted.insertionSort(s)(_ < _)
```
or you want to be DESC, you can call with:
```Scala
Sorted.insertionSort(s)(_ > _)
```
