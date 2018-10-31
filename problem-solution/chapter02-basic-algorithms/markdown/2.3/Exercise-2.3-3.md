
## Q
![](https://raw.githubusercontent.com/KnewHow/FPAlgorithms/master/problem-solution/chapter02-basic-algorithms/img/2.3-3-question.png)

## A

* when `n = 2`,
  `T(2) = 2 * lg(2) = 2`   => Rigth!
* when `n = n/2`
  `T(n/2) = n/2 * lg(n/2)`
  `T(n) = 2T(n/2) + n = 2 *(n/2 * lg(n/2)) + n = nlg(n/2) + n = nlgn` => Rigth
