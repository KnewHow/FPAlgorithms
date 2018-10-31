## Q
Observe that the while loop of lines 5–7 of the INSERTION-SORT procedure in Section 2.1 uses a linear search to scan (backward) through the sorted subarray
`A[1 ... j-1]`. Can we use a binary search (see Exercise 2.3-5) instead to improve the overall worst-case running time of insertion sort to O(nlgn)?

## A:
No, even if we find the position, but we also need move element to `j`, `j-1` ... gradually, it also need took `j/2` steps in avreage.
