# Mergesort

## Question 1
(seed = 314847)
Give the array that results immediately after the 7th call (and return)
from merge() when top-down mergesorting the following array of size 12:

    14 92 38 43 78 42 10 76 11 81 36 26

Your answer should be a sequence of 12 integers, separated by whitespace.

```java
// Define this variable for MergeSort and MergeSortBU class
public static int mergeCall = 0;
// Add the following print statement in the merge method
System.out.printf("merge call time: %d\n", ++mergeCall);
show(a);
```

**Answer:**
14 38 42 43 78 92 10 11 76 81 36 26

## Question 2
(seed = 389052)
Give the array that results immediately after the 7th call (and return)
from merge() when bottom-up mergesorting the following array:

67 64 62 78 87 90 77 34 91 79

Your answer should be a sequence of 10 integers, separated by whitespace.

**Answer:**
62 64 67 78 34 77 87 90 79 91

## Question 3

(seed = 165755)
Which of the following statements about mergesort are true? Check all that apply.
Unless otherwise specified, assume that mergesort refers to the pure recursive
(top-down) version of mergesort (with no optimizations), using the merging
subroutine described in lecture.

1. For any array of N distinct keys with N a power of 2, top-down mergesort and
bottom-up mergesort compare exactly the same pairs of keys (but possibly in a
different order).
  * This can be proved by induction - in either version of mergesort, all of the
    subarray sizes are powers of 2.

2. No compare-based sorting algorithm can guarantee to use ~ N lg N compares
  (in the worst case) to sort an array of N items.
  * Mergesort uses ~ N lg N compares in the worst case.

3. Consider any two consecutive items in the array that results after
mergesorting an array of N distinct keys. Then, those two items were compared
with one another at some point during the sort.
  * This is true for any compare-based sorting algorithm. Otherwise, the
    algorithm would produce the same result if the relative order of the two
    items were switched.

4. Mergesort is a stable sorting algorithm.
  * This is a key property of mergesort.

5. It is possible to design a compare-based algorithm to merge two sorted
subarrays, each of size N, using only logarithmic extra space
(not including the input array) and a linear number of compares.
  * Such in-place merging algorithms exist (but none is known to be practical).

# Quick Sort

## Question 1
(seed = 954661)
Give the array that results after applying the standard 2-way partitioning
subroutine from lecture to the following array:

    45 84 61 99 98 13 86 46 97 14 54 53

Your answer should be a sequence of 12 integers, separated by whitespace.

Recall, in the standard 2-way partitioning subroutine, the leftmost entry is the
partitioning item.

```java
// Just put the show method in the partition method.
```

**Answer:**
13 14 45 99 98 61 86 46 97 84 54 53

## Question 2

(seed = 156825)
Give the array that results after applying Dijkstra's 3-way partitioning
subroutine from lecture to the following array:

    50 67 79 96 20 50 40 65 10 50

Your answer should be a sequence of 10 integers, separated by whitespace.

**Answer:**
40 10 20 50 50 50 96 67 79 65

## Question 3
(seed = 895349)
Which of the following statements about quicksort are true? Check all that apply. Unless otherwise specified, assume that quicksort refers to the recursive, randomized version of quicksort (with no extra optimizations) and uses the 2-way partitioning algorithm described in lecture.

1. Stability and guaranteed linearithmic performance are two reasons why the Java system sort uses a version of mergesort (instead of quicksort) to sort arrays of objects.
  * These are two good reasons to favor mergesort.

2. The number of partitioning steps to quicksort an array of N items is no larger than N.
  * Each partitioning step fixes the partitioning item into position. In fact, it will be strictly less than N because subarrays of length 1 are not partitioned.

3. Median-of-3 partitioning is useful in practice but it does not reduce either the expected number of compares or exchanges.
  * It is useful in practice; it also reduces the expected number of compares for an array of N distinct keys from ~ 2 N ln N to ~ 12/7 N ln N.

4. The expected number of compares to find a median of an array of N items using quickselect is linear.
  * This is asserted in the lecture slides without proof.

5. The expected number of compares to quicksort an array of N keys can be
substantially less (e.g., a constant factor) than ~ 2 N ln N if there are a
large number of items with equal keys.
  * For example, the expected number of compares is ~ N lg N if all keys are equal.
