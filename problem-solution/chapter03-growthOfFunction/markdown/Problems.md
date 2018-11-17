# Problems

## 3-1

*  Q

![](https://github.com/KnewHow/FPAlgorithms/blob/master/problem-solution/chapter03-growthOfFunction/img/p-3-1-q.png?raw=true)

*  A

![](https://github.com/KnewHow/FPAlgorithms/blob/master/problem-solution/chapter03-growthOfFunction/img/p-3-1-a.gif?raw=true)

## 3-2

*  Q

![](https://github.com/KnewHow/FPAlgorithms/blob/master/problem-solution/chapter03-growthOfFunction/img/p-3-2-q.png?raw=true)

*  A

![](https://github.com/KnewHow/FPAlgorithms/blob/master/problem-solution/chapter03-growthOfFunction/img/p-3-2-a.png?raw=true)

## 3-3

*  Q

![](https://github.com/KnewHow/FPAlgorithms/blob/master/problem-solution/chapter03-growthOfFunction/img/p-3-3-q.png?raw=true)

*  A

![](https://github.com/KnewHow/FPAlgorithms/blob/master/problem-solution/chapter03-growthOfFunction/img/p-3-3-a-1.png?raw=true)

![](https://github.com/KnewHow/FPAlgorithms/blob/master/problem-solution/chapter03-growthOfFunction/img/p-3-3-a-2.png?raw=true)

## 3-4
*  Q
![](https://github.com/KnewHow/FPAlgorithms/blob/master/problem-solution/chapter03-growthOfFunction/img/p-3-4-q.png?raw=true)

*  A

    * a
      False f(n) = n and g(n) = n^2, f(n) = O(g(n)), but g(n) != O(f(n))

    * b
      Flase, f(n) = n and g(n) = n^2, f(n)+ g(n) = n + n^2 != Θ(n)

    * cTranspose symmetry:
      True, because lgn is monotonically increasing when n >=1, so when g(n) >= f(n) => lg(g(n)) >= lg(f(n))

    * d
      False, 2n = O(n), but 2^2n != monotonically increasing2^n

    * e
      Flase, when f(n) = n^(-3) f(n)^2 = n^(-6), it will less than f(n)

    * f
      True, Transpose symmetry in page 52

    * g
      False, when f(n) = 2^2n f(n/2) = 2^n

    * h
      False, becaus o(f(n)) > f(n)

## 3-5

*  Q

![](https://github.com/KnewHow/FPAlgorithms/blob/master/problem-solution/chapter03-growthOfFunction/img/p-3-5-q-1.png?raw=true)
![](https://github.com/KnewHow/FPAlgorithms/blob/master/problem-solution/chapter03-growthOfFunction/img/p-3-5-q-2.png?raw=true)

*  A
    * a
      The only differ between is Ω is for all n > n0, but Ω(infinite) is **haveing** infinite n. For example, f(n) = n^2(n %2), it will so greater when n is odd,but it is zero when n is even, so for all n > 0, Ω is false.

    * b
        The advantage is that you get the result of part a which is a nice property. A
    disadvantage is that the infinite set of points on which you are making claims
    of the behavior could be very sparse. Also, there is nothing said about the
    behavior when outside of this infinite set, it can do whatever it wants.

    left answer can refer following link

Some solution refer:http://sites.math.rutgers.edu/~ajl213/CLRS/Ch3.pdf
