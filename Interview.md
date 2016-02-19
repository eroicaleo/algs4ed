# Priority Queues

**Dynamic median.** Design a data type that supports insert in logarithmic time,
find-the-median in constant time, and remove-the-median in logarithmic time.

Hint: maintain two binary heaps, one that is max-oriented and one that is min-oriented.

**Randomized priority queue.** Describe how to add the methods sample() and delRandom()
to our binary heap implementation. The two methods return a key that is chosen
uniformly at random among the remaining keys, with the latter method also removing
that key. The sample() method should take constant time; the delRandom() method
should take logarithmic time. Do not worry about resizing the underlying array.

**Taxicab numbers.** A taxicab number is an integer that can be expressed as the sum
of two cubes of integers in two different ways: a^3+b^3=c^3+d^3. For example,
1729=9^3+10^3=1^3+12^3. Design an algorithm to find all taxicab numbers with a, b, c, and d less than N.
Version 1: Use time proportional to N^2 logN and space proportional to N^2.
Version 2: Use time proportional to N^2 logN and space proportional to N.

Hints:
Version 1: Form the sums a3+b3 and sort.
Version 2: Use a min-oriented priority queue with N items.

# BST

**Java autoboxing and equals().** Consider two double values a and b and their
corresponding Double values x and y.
* Find values such that (a == b) is true but x.equals(y) is false.
* Find values such that (a == b) is false but x.equals(y) is true.

Hint: IEEE floating point arithmetic has some peculiar rules for 0.0, -0.0, and
NaN. Java requires that equals() implements an equivalence relation.

**Inorder traversal with constant extra space.** Design an algorithm to perform an
inorder traversal of a binary search tree using only a constant amount of extra space.

Hint: you may modify the BST during the traversal provided you restore it upon completion.

**Web tracking.** Suppose that you are tracking N web sites and M users and you
want to support the following API:
* User visits a website.
* How many times has a given user visited a given site?
What data structure or data structures would you use?

Hint: maintain a symbol table of symbol tables.
