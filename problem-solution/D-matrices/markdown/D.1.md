# D.1

## D.1-1

*  Q

Show that if A and B are symmetric `n x n` matrices, then so are `A + B` and `A - B`.

*  A

![](https://github.com/KnewHow/FPAlgorithms/blob/master/problem-solution/D-matrices/img/D.1-1-a.gif?raw=true)

## D.1-2

*  Q

Prove that![](http://latex.codecogs.com/gif.latex?\(AB\)^T=B^TA^T) and that ![](http://latex.codecogs.com/gif.latex?A^TA) is always a symmetric matrix.

*  A

*  1

![](https://github.com/KnewHow/FPAlgorithms/blob/master/problem-solution/D-matrices/img/D.1-2-a-1.gif?raw=true)

![](https://github.com/KnewHow/FPAlgorithms/blob/master/problem-solution/D-matrices/img/D.1-2-a-2.gif?raw=true)

![](https://github.com/KnewHow/FPAlgorithms/blob/master/problem-solution/D-matrices/img/D.1-2-a-3.gif?raw=true)

  * 2

  ![](https://github.com/KnewHow/FPAlgorithms/blob/master/problem-solution/D-matrices/img/D.1-2-a-4.gif?raw=true)


## D.1-3

*  Q

Prove that the product of two lower-triangular matrices is lower-triangular.

*  A

![](https://github.com/KnewHow/FPAlgorithms/blob/master/problem-solution/D-matrices/img/D.1-3-a-1.gif?raw=true)

![](https://github.com/KnewHow/FPAlgorithms/blob/master/problem-solution/D-matrices/img/D.1-3-a-2.gif?raw=true)

## D.1-4
*  Q

![](https://github.com/KnewHow/FPAlgorithms/blob/master/problem-solution/D-matrices/img/D.1-4-q.png?raw=true)
*  A

Suppose row i of P has a 1 in column j. Then row i of P A is row j of A, so P A permutes the rows. On the other hand, column j of AP is column i of A, so AP permutes the columns. We can view the product of two permutation matrices as one permutation matrix permuting the rows of another. This preserves the property that there is only a single 1 in each row and column, so the product is also a permutation matrix.
