
# deleteMin()

The invariant of the recursive call is that the current node is not a 2-node.
And the tree is perfectly balanced.
And we will make our left child a 3-node or 4-node if it is not already a 3-node,
(of course it can not be a 4-node, otherwise it's not a legal LLRB).

Because if we delete a node in a 3-node, the tree 2-3 tree is still perfectly balanced.
So we want to transform the tree, such that if we are seeing the min node,
we know it is in a 3-node or a 4-node and we could safely delete it.

## Way down analysis: `moveRedLeft` method.

On the way down the tree, We need to distinguish three cases:
* the left child is already a 3-node, then we need to do nothing.
* If the left child is a 2-node and the middle child is also a 2-node, then we
just need to push the current node `h` one level down, to make a 4-node.
The implementation is just to `flipColors`. We can see the tree is still perfect
black balanced, current node is not a 2-node, it is a 4-node.
The left child `g` is also in the same 4-node. So the invariant is retained.

![moveRedLeft Case1](https://github.com/eroicaleo/algs4ed/blob/master/ch03/moveRedLeftCase1.png)

* If the left child is a 2-node and the middle child is 3-node. This case is
a little bit tricky. Our goal is to make node `j` to the position of node `h`,
we still use the 3 operations we have. It’s just 3 more operations compare to case 2.
The better thing is, not only `deleteMin` invariant is maintained, the red black
tree invariant is also maintained.

![moveRedLeft Case2](https://github.com/eroicaleo/algs4ed/blob/master/ch03/moveRedLeftCase2.png)

* A comment about `moveRedLeft(Node h)`, why we can assert `isRed(h)` in this method?
  This is because we only call `moveRedLeft` when we have `!isRed(h.left) && !isRed(h.left.left)`.
  Now because `h` is already a 3-node or a 4-node, only when `h` is the left key
  of the 3-node or 4-node, `h` can have `!isRed(h.left) && !isRed(h.left.left)`.
  So `h` must be red node.

```java
private Node moveRedLeft(Node h) {
    assert (h != null);
    assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);
    ...
}
```

## Way up analysis: `balance` method.

On the way up, before deleteMin recursive call, I am either a 3-node, or a 4-node.

1. I am 3-node
  1. I am left node of 3-node
    1. after `deleteMin()`, I might got deleted, then no need to do anything.
    2. after `deleteMin()`, I am still a 3-node, then there is no need to
      do anything, because the red-black tree invariant is retained.
    3. after `deleteMin()`, I become a 4-node, I could only be the middle
      node of a 4-node, from my perspective, there is no need to do anything,
      because the red-black tree invariant is retained.
  2. I am right node of 3-node
    1. after `deleteMin()`, I might become a 2-node, then no need to do anything.
    2. after `deleteMin()`, I might still a 3-node, then no need to do anything.
    3. after `deleteMin()`, I become a 4-node, then do need to perform the second transformation.

2. I am 4-node
  1. I am left node of 4-node
    1. after `deleteMin()`, I might got deleted, then no need to do anything.
    2. after `deleteMin()`, I am still a 4-node, then there is no need to do
      anything, because from my perspective, the red-black tree invariant is retained.
    3. after `deleteMin()`, I become a 5-node, I could only be the middle node
      of a 5-node, from my perspective, there is no need to do anything, because
      the red-black tree invariant is retained.
  2. I am middle node of 4-node
    1. after `deleteMin()`, I might become a 3-node, then perform the first operation.
    2. after `deleteMin()`, I am still a 4-node, then perform the third operation.
      The current 4-node is gone, I become the left node of upper level node.
    3. after `deleteMin()`, I become a 5-node, I could only be the middle right
      node of a 5-node, the perform the third operation.
  3. I am right node of 4-node, it is impossible on the way down path. So way up
    won't go to it.

Following figure shows the worst case 2.2.3, which needs all three transformations
in `balance`. Two comments about this `balance` method.
* Why in the case 2.2.3, we don't just use the third transformation?
    * We want to make the code concise and consistent for all cases, from one
      state to another, so even the first two `rotateRight` and `rotateLeft` are
      redundant and make the tree back to where we start off, we still keep them.
* Why is it a little bit different from the `put` method three transformations?
    * In `put` method, we do first transformation like this:
      `if (isRed(h.right) && !isRed(h.left)) h = rotateRight(h);`. Case 2.2.3
      shows the case. If we do it like `put`, we will skip the `rotateRight`
      transformation. Then we will do `rotateLeft`, this can make we have two
      right leaning red links in a row. And we will not be able to recover the
      right red links back.
    * So why we don't use `balance` in the `put` method. I think maybe because
      we have a lot `put` operations, so we might better save as much as we could.
      *We can check in the forum.*

![balance Case 2.2.3](https://github.com/eroicaleo/algs4ed/blob/master/ch03/balanceCase2_2_3.png)

## Other comments about `deleteMin`.
* During the `deleteMin` of normal BST, we need to return `h.right`. But now,
  since we are RBBST, if `h.left` is null, then `h.right` must be null,
  so we could return null. Otherwise it’s a violation of perfect black balance.
  During the moveRedLeft transformation, the black balance is maintained.
* Why we need the second condition of `if (!isRed(root.left) && !isRed(root.right)) root.color = RED;`?
    * *We can check with forum.*
