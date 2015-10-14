Notes on Algorithms
===================

# Table of Contents

* [Lecture 6 Hash Tables][#lecture-6-hash-tables]
    * [Hash Functions][#hash-functions]

# <a id="lecture-6-hash-tables"></a>Lecture 6 Hash Tables

## <a id="hash-functions"></a>Hash Functions

implemetation | search | insert | delete | search hit | insert | delete | ordered iteration | key interface |
--------------|:------:|:------:|:------:|:----------:|:------:|:------:|:-----------------:|:-------------:|
red black BST | 2 lg N | 2 lg N | 2 lg N | 1 lg N     | 1 lg N | 1 lg N | yes | `compareTo()` |

Q: Can we do better than lg(N) ?
