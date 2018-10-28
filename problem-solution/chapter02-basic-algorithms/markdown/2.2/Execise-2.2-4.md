## Q:
How can we modify almost any algorithm to have a good best-case running time?
## A:
For a good best-case running time, modify an algorithm to first randomly
produce output and then check whether or not it satisfies the goal of the algorithm.
If so, produce this output and halt. Otherwise, run the algorithm as
usual. It is unlikely that this will be successful, but in the best-case the running
time will only be as long as it takes to check a solution. For example, we could
modify selection sort to first randomly permute the elements of A, then check if
they are in sorted order. If they are, output A. Otherwise run selection sort as
usual. In the best case, this modified algorithm will have running time Θ(n).
How can we modify almost any algorithm to have a good best-case running time?

Reference from: [CLRS](http://sites.math.rutgers.edu/~ajl213/CLRS/CLRS.html)
