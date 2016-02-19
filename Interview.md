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
