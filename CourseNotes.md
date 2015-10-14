Notes on Algorithms
===================

# Table of Contents

* [Lecture 6 Hash Tables](#lecture-6-hash-tables)
    * [Hash Functions](#hash-functions)
    * [separable chaining](#separable-chaining)
    * [linear probing](#linear-probing)
    * [context](context)

# <a id="lecture-6-hash-tables"></a>Lecture 6 Hash Tables

## <a id="hash-functions"></a>Hash Functions

implemetation | search | insert | delete | search hit | insert | delete | ordered iteration | key interface |
--------------|:------:|:------:|:------:|:----------:|:------:|:------:|:-----------------:|:-------------:|
red black BST | 2 lg N | 2 lg N | 2 lg N | 1 lg N     | 1 lg N | 1 lg N | yes | `compareTo()` |

Q: Can we do better than lg(N) ?
A: Yes, but with different access to the data (if we don't need ordered ops)

**Hashing: baisc plan**

Save items in a key-indexed table (index is a function of the key).

* _Hash function_: Method for computing array index from key

```java
hash("it") = 3
```

* _Issues_
    * Computing the hash function, can get complicated for complicated types of data.
    * Equality test: Method for checking whether two keys are equal.
    * Collision resolution: Algorithm and data structure to handle two keys
    that hash to the same array index

* Classic space-time tradeoff
    * No space limitation: very huge array for every possible key
    * No time limitation: hash everything to the same place and sequtial search
    * space and time limitation: trade-off, real world hash

## <a id="separable-chaining"></a>separable chaining
## <a id="linear-probing"></a>linear probing
## <a id="context"></a>context
