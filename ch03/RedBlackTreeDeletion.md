
# deleteMin()

The invariant of the recursive call is that the current node is not a 2-node.
And the tree is perfectly balanced.
And we will make our left child a 3-node or 4-node if it is not already a 3-node,
(of course it can not be a 4-node, otherwise it's not a legal LLRB).

Because if we delete a node in a 3-node, the tree 2-3 tree is still perfectly balanced.
So we want to transform the tree, such that if we are seeing the min node,
we know it is in a 3-node or a 4-node and we could safely delete it.

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
