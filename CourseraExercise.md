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

# Priority Queue

See the ch02/Quiz/PQQuiz.java

# Binary Search Tree

See the ch03/Quiz/BSTQuiz.java

# Balanced Search Tree

## Question 1

(seed = 240407)
Consider the left-leaning red-black BST whose level-order traversal is:

    54 45 89 21 50 62 90 16 39 58 77 55 75

List (in ascending order) the keys in the red nodes. A node is red if the link
from its parent is red. Your answer should be a sequence of integers, separated
by whitespace.

## Question 2

(seed = 772103)
Consider the left-leaning red-black BST whose level-order traversal is

    58 40 85 20 56 71 96 16 62 77          ( red links = 16 71 )

What is the level-order traversal of the red-black BST that results after
inserting the following sequence of keys:

    52 66 18

Your answer should be a sequence of 13 integers, separated by whitespace.

## Question 3

(seed = 870744)
Which of the following statements about balanced search trees are true? Check all that apply. Unless otherwise specified, assume that the 2-3 tree and red-black BSTs are as described in lecture (e.g., 2-3 trees are perfectly balanced and red-black BST are left-leaning red-black BSTs with internal links colored either red or black).

* The height of a red-black BST on N nodes is less than or equal to 2 log_3 N.
* In a red-black BST of height h, the number of nodes is less than or equal to 2^h.
* The number of structurally different 2-3 trees (with respect to tree shape
  and node types) 2-3 trees containing 4 keys is 2.
* The maximum number of color flips triggered by inserting a key into a
  red-black BST on N nodes is ~ 2 lg N.
* The height of a 2-3 tree on N keys is solely determined by N.

# Geometric Searching Applications of BSTs

## Question 1

(seed = 315821)
Suppose that you run the orthogonal line segment intersection algorithm from lecture
on the following set of segments:

A (13,  4)  ->  (13, 18)  [ vertical   ]
B ( 8,  0)  ->  (19,  0)  [ horizontal ]
C ( 5,  5)  ->  ( 5, 11)  [ vertical   ]
D (17,  2)  ->  (17,  9)  [ vertical   ]
E (11, 14)  ->  (15, 14)  [ horizontal ]
F ( 7, 15)  ->  (18, 15)  [ horizontal ]
G (10,  3)  ->  (14,  3)  [ horizontal ]
H (12, 10)  ->  (16, 10)  [ horizontal ]


Give the horizontal line segments in the BST (sorted in increasing order of y-coordinate) just before
the sweep-line algorithm processes the vertical line segment A.

Your answer should be a sequence of uppercase letters, separated by whitespace.

## Question 2

(seed = 869198)
What is the level-order traversal of the kd-tree that results after inserting
the following sequence of points into an initially empty tree?

A (0.22, 0.44)
B (0.59, 0.08)
C (0.94, 0.93)
D (0.88, 0.22)
E (0.73, 0.34)
F (0.53, 0.61)
G (0.62, 0.97)
H (0.04, 0.95)

Your answer should be a sequence of uppercase letters, starting with A and separated by whitespace.

Recall that our convention is to subdivide the region using the x-coordinate at even levels
(including the root) and using the y-coordinate at odd levels. Also, we use the left subtree
for points with smaller x- or y-coordinates.

## Question 3

(seed = 821215)
Consider an interval search tree containing the set of 8 intervals

A [ 6, 25]
B [ 0,  7]
C [10, 13]
D [23, 34]
E [ 2, 24]
F [ 4, 37]
G [38, 40]
H [ 1, 16]

and whose level-order traversal is:  A B C E D H F G.

Suppose that you use the search algorithm described in lecture to search for
any *one* interval that intersects [30, 36]. What is the sequence of
intervals in the tree that are checked for intersection with the query interval?

Your answer should be a sequence of uppercase letters, starting with A, separated by whitespace.
