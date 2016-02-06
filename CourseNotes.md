Notes on Algorithms
===================

<!-- TOC depth:6 withLinks:1 updateOnSave:1 orderedList:0 -->

- [How to setup algs4 environment with IntelliJ](#how-to-setup-algs4-environment-with-intellij)
- [Lecture 6 Hash Tables](#lecture-6-hash-tables)
	- [Hash Functions](#hash-functions)
	- [Separable Chaining](#separable-chaining)
	- [Linear Probing](#linear-probing)
	- [Context](#context)
	- [Symbol Table Application - Sets](#symbol-table-application-sets)
<!-- /TOC -->

# How to setup algs4 environment with IntelliJ

1. Download and install the binaries and .jar library using official installer.
	Can be found in official booksite [here](http://algs4.cs.princeton.edu/windows/)
2. In your IntelliJ project, on the top right corner, click the "Project Structure"
	icon, or simply use hotkey `Ctrl+Alt+Shift+S`.
3. In the pop up "Project Structure" menu, select Project Settings | Modules
4. In right part, click the green "+" sign or use hotkey `Alt+Insert`.
5. Then select "JARs or directories".
6. Navigate to `C:\Users\username\algs4`, which is the default install location.
7. Double click the "algs4.jar".
8. Verify the `algs4.jar` is in the Project | External Libraries.
9. Now you can simply test the following snippet.
	```java
	import edu.princeton.cs.algs4.StdOut; // IntelliJ automatically adds for you
	StdOut.println("Hello world!")
	```

# Lecture 6 Merge Sort (Book 2.2)

## 6.1 Merge sort
Given two sorted sub arrays a[lo] a [mid] and a[mid+1] a[hi].
We need three indices i, j, k. Core code: `merge()` and `sort()`.

```java
private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

    assert isSorted(a, lo, mid);
    assert isSorted(a, mid+1, hi);

    for (int k = lo; k <= hi; k++) {
        aux[k] = a[k];
    }

    int i = lo, j = mid+1;
    for (int k = lo; k <= hi; k++) {
        if (i > mid)                   a[k] = aux[j++];
        else if (j > hi)               a[k] = aux[i++];
        else if (less(aux[j], aux[i])) a[k] = aux[j++];
        else                           a[k] = aux[i++];
    }

    assert isSorted(a, lo, hi);
}

private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
    if (hi <= lo) return;

    int mid = lo + (hi - lo) / 2;
    sort(a, aux, lo, mid);
    sort(a, aux, mid+1, hi);
    merge(a, aux, lo, mid, hi);

    return;
}
```

**Easy to make mistakes:**
* I sometimes assign `aux[k] = a[i++]`;
* I sometimes do `less(a[j], a[i])`;
* I sometimes do `aux[k++] = a[i++]`;
* `int mid = lo + (lo+hi)/2`
* One gotcha: we need allocate the aux at the top, not in the recursive
  programming.

### JAVA assertion
Throw exception if condition is true. We use -ea and -da to enable and disable assertion.
```java
java -ea MyProgram
java -da MyProgram
```

### Merge Sort Complexity
`Nlog(N)`, how to prove:
`D(N) = 2xD(N/2) + N => D(N)/N = D(N/2) / (N/2) + 1 ...`

### Three optimization `MergeX`
1. when the number in the array is small (N < 7), we could use insertion sort to
	avoid recursive.
2. We could check if `a[mid+1] >= a[mid]`, if so, we don’t need to merge.
3. We could use `a` and `aux` alternatively to avoid the copy inside merge.
	* Note in this case, the `aux` has to be initialized as `a` using `Comparable[] aux = a.clone()`

# Lecture 6 Hash Tables

## Hash Functions

implemetation | search | insert | delete | search hit | insert | delete | ordered iteration | key interface |
--------------|:------:|:------:|:------:|:----------:|:------:|:------:|:-----------------:|:-------------:|
red black BST | 2 lg N | 2 lg N | 2 lg N | 1 lg N     | 1 lg N | 1 lg N | yes | `compareTo()` |

Q: Can we do better than lg(N) ?
A: Yes, but with different access to the data (if we don't need ordered ops)

**Hashing: baisc plan**

Save items in a key-indexed table (index is a function of the key).

* _Hash function_: Method for computing array index from key, reduce key to integer.

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

**computing the hash function**

Idealistic goal: Scramble the keys uniformly to produce a table index

* Efficiently computable
* Each table index equally likely for each key.

Ex 1. Phone numbers.

* Bad: First 3 digits
* Better: last 3 digits
* Ideally: want to use all data

Ex 2. Social Security numbers

* Bad: First 3 digits, associated with geographic region
* Better: last 3 digits

Practical challenge: Need different approach for each key type.

* `int`, `String`, `double` just count on JAVA
* Implement own type of data, we need to worry about it.

**Java's hash code convetions**

All Java classes inherit a method called `hashCode()`, which returns a 32-bit `int`.

* requirement:

```java
if x.equals(y)
then x.hashCode() == y.hashCode
```

* Highly desirable

```java
if x.equals(y)
then x.hashCode() != y.hashCode
```

* Default implementation: Memory address of x.
* Legal but poor implementation: always `return 17`.
* Customized implementations: `Integer`, `Double`, `String`, `File`, `URL`, `Date`, ...
    * sweet spot for hashing, some expert has done the `hashCode()` and
    application doesn't need ordering.

**Implementing hash code: integers, booleans and doubles**

Java library implementation

* integer: `return value` itself.
* boolean: `return 1231` for `true`, `1237` for `false`.
* Double: try to involve all the bits.

```java
public int hashCode() {
    long bits = doubleToLongBits(value); // IEEE 64-bit repr
    return (int) (bits ^ (bits >>> 32)); // xor MSB 32-bits with LSB 32-bits
}
```

**Implementing hash code: String**

* String: treats `String` as huge number, Horner's rule:
    * `s[0] * 31^(L-1) + ... + s[L-1] * 31^0`

```java
public int hashCode() {
    int hash = 0;
    for (int i = 0; i < length; i++) {
        hash = s[i] + 31 * hash;
    }
}
```

* Performance optimization
    * Note that `String` is immutable
    * Cache the hash value in an instance variable
    * Return cached value

```java
public final class String {
    private int hash = 0;
    public int hashCode() {
        int h = hash;
        if (h != 0) return h;
        for (int i = 0; i < length; i++)
            h = s[i] + 31 * hash;
        hash = h;
        return h;
    }
}
```

**Implementing hash code: user-defined type**

* We want to make use of all pieces of data we have
* We want to make use of the hash code implementations for the types of data
that we are using.
* Mimic Horner's method, starts with a small prime number.

```java
public final class Transaction implements Comparable<Transaction> {
    private final String who;
    private final Date   when;
    private final double amount;

    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + who.hashCode();
        hash = 31 * hash + when.hashCode();
        // primitive type: use hashCode() of wrapper type
        hash = 31 * hash + (Double amount).hashCode();
        return hash;
    }
}
```

**Hash code design**

"Standard" recipe for user-defined types

* Combine each significant field using the `31x+y` rule.
* If field is a primitive type, use wrapper type `hashCode()`.
* If field is `null`, return 0
* if field is a reference type, use `hashCode()`.
* if field is an array, apply to each entry, or use `Arrays.deepHashCode()`

In practice:

* works reasonably well; used in java libraries

In Theory:

* Keys are bitstring: "universal" hash functions exist.

Basic rule:

* Need to use whole key
* Consult an expert if see some performance issue.

**Modular hashing**

* Hash code: An `int` between -2^31 and 2^31 - 1
* Hash function: An `int` between 0 and M-1 (for use as array index)
    * M is typically a prime or power of 2

```java
// Bug, because -1 % 23 = -1
private int hash(Key key) {
    return key.hashCode() % M;
}
// 1-in-a-billion bug, the key.hashCode() can be -2^31
private int hash(Key key) {
    return Math.abs(key.hashCode()) % M;
}
// OK
private int hash(Key key) {
    return (key.hashCode() & 0x7fffffff) % M;
}
```

**Uniform hashing assumption**

* Uniform hashing assumption: each key is equally likely to hash to an integer between 0 and M-1
* Bins and balls: Throw balls uniformly at random into M bins
* Birthday problem: expect two balls in the same bin after `sqrt(pi*M/2)` tosses.
* Coupon collector: Expect every bin has >= 1 ball after ~ MlnN tosses.
* Load balancing: after M tosses, expect most loaded bin has \Theta(logM/loglogM) balls.

## Separable Chaining

**Collisions**

* Collision: Two distinct keys hashing to same index.
    * Birthday problem: can't avoid collisions unless you have quadratic amount of memory.
    * Coupon collector + Load balancing: collisions will be evenly distributed.
* Challenge: Deal with collision efficiently.

**Separable chaining symbol table**

* Use an array of M < N linked list. [H.P. Luhn, IBM 1953]
    * Hash: map key to integer `i` between `0` to `M-1`.
    * Insert: put at front of ith chain (if not already there).
    * Search: need to search only ith chain.

**Separable chaining ST: Java implementation**

```java
public class SeparateChainingHashST<Key, Value> {
    private int M = 97;
    private Node[] st = new Node[M];

    private static class Node {
        private Object key;
        private Object val;
        private Node next;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return (Value) x.val;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        st[i] = new Node(key, val, st[i]);
        return;
    }

}
```

* There is no generic array creation, so we have to define `Node` class. And we have to
  declare `key` and `value` of type `Object`, because its `static` nested class.
* In `put`, we link to the beginning of a chain.
* Very little code, so it's very popular.

**Analysis of separable chaining**

* Proposition: Under uniform hashing assumption, prob. that the number of keys in a list
  is within a constant factor of N/M is extremely close to 1.
* Pf sketch: Distribution of list size obeys a binomial distribution.
* Consequence: Number of probes for search/insert is proportional to N/M.
    * M too large => too many empty chains.
    * M too small => chains too long.
    * Typical choices: M ~ N/5 => constant-time ops. (Might need array resizing)

implemetation | search | insert | delete | search hit | insert | delete | ordered iteration | key interface |
--------------|:------:|:------:|:------:|:----------:|:------:|:------:|:-----------------:|:-------------:|
red black BST | 2 lg N | 2 lg N | 2 lg N | 1 lg N     | 1 lg N | 1 lg N | yes | `compareTo()` |
separable chaining | lg N | lg N | lg N | 3-5 | 3-5 | 3-5 | no | `equals()` |

## Linear Probing

**Collision Resolution: open addressing**

When a new key collides, find next empty slot, and put it there. The size of the
array has to be bigger than the number of keys.

* `Hash`: Map key to integer `i` between 0 and `M-1`
* `Insert`: Put at table index `i` if free; if not try `i+1`, `i+2`, etc.
* `Search`: Search table index `i`; if occupied but no match, try `i+1`, `i+2`,
	etc.
* `Note`: Array size `M` must be greater than the number of key-value pairs `N`.
	(It's a good idea to keep the at least half empty).

**Linear Probing ST: Java implementation**

```java
public class LinearProbingHashST<Key, Value> {
    private int M = 30000;
    private Key[]   keys = (Key[])   new Object[M];
    private Value[] vals = (Value[]) new Object[M];

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value val) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (key.equals(keys[i]))
                break;
        }
        keys[i] = key;
        vals[i] = val;
    }

    public Value get(Key key) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (key.equals(keys[i]))
                return vals[i];
        }
        return null;
    }

}
```

**Clustering**

* `Cluster`: A contiguous block of items.
* `Observation`: New keys likely to hash into middle of big clusters.

**Knuth's Parking Problem**

* `Question`: What is mean displacement of a car?
* `Half-full`: With `M/2` cars, mean displacement is ~ 3/2
* `Full`: With `M` cars, mean displacement is ~ sqrt(pi*M/8)

**Analysis of linear probing**

Parameters:

* M too large => too many empty array entries.
* M too small => search time blows up.
* Typical choice: alpha = N / M ~ 1/2
		* probes for search hit is about 3/2
		* probes for search miss is about 5/2

implemetation | search | insert | delete | search hit | insert | delete | ordered iteration | key interface |
--------------|:------:|:------:|:------:|:----------:|:------:|:------:|:-----------------:|:-------------:|
red black BST | 2 lg N | 2 lg N | 2 lg N | 1 lg N     | 1 lg N | 1 lg N | yes | `compareTo()` |
separable chaining | lg N | lg N | lg N | 3-5 | 3-5 | 3-5 | no | `equals()` |
linear probing     | lg N | lg N | lg N | 3-5 | 3-5 | 3-5 | no | `equals()` |

## Context

**String Hashing in Java**

String `hashCode()` in Java 1.1
* only examine 8-9 evenly distributed characters
* Benefit: save time in performing arithmetic
* Downside: great potential for bad collision patterns.

```java
public int hashCode() {
    int hash = 0;
    int skip = Math.max(1, s.length()/8);
    for (int i = 0; i < s.length(); i += skip) {
        hash +=  s.charAt(i) + (hash * 37);
    }
}
```

**Algorithmic Complexity Attacks**

Q: Is the uniform hashing assumption important in practice?

A: Obvious situations: aircraft control, nuclear weapons and pacemaker.

A: Suprsing situations: denial-of-service attacks.
* Malicious adversaries learn the hash function and causes a big pile-up.

In this case, we are hoping for randomness. Not like quick sort, we are providing
randomness. Not like red-black tree, performance is guaranteed.

**Separable Chaining v.s. linear probing**

Separable chaining:
* Easier to implement delete
* Performance degrades gracefully
* Clustering less sensitive to poorly designed hash functions

Linear probing:
* Less wasted spaces.
* Better cache performance.

Problems to think about:
* How to delete?
* How to resize?

**Hashing: Variations**

Two-probe hashing (separable-chaining variant)
* Hash to two positions, insert key in shorter of the two chains.
* Reduce the expected length of the longest chain to `log log N`.

Double hashing (linear-probing variant)
* Use linear probing but skip a variable amount, not just 1 each time.
* Effectively eliminates clustering.
* Can allow table to become nearly full.
* More difficult to implement delete.

Cuckoo hashing (linear-probing variant)
* Constant worst time search.

**Hash Table v.s. Balanced Search Trees**

Hash table:
* Simple to code
* No effective alternative for unordered keys.
* Faster for simple keys (a few arithmetic ops versus `log N` comparison).
* Better system support in Java for strings (e.g. cached hash code).

Balanced Search Trees:
* Strong Performance guarantee.
* Support for ordered ST operations.
* Easier to implement `compareTo` correctly than `equals()` and `hashCode()`.

Java system includes:
* red-black tree: `java.util.TreeMap`, `java.util.TreeSet`.
* Hash table: `java.util.HashMap`, `java.util.IdentityHashMap`.

## Symbol Table Application - Sets

[book site code](algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/SET.java.html)

[book site doc](http://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/SET.html)

```java
public SET<Key extends Comparable<Key>>
```

| Return Type    | Method         | Description |
| -------------: | :------------- | :---------- |
|                | `SET()`        | create an empty set |
| `void` | `add(Key key)` | add the key to the set |
| `boolean` | `contains(Key key)` | is the key in the set? |
| `void` | `remove(Key key)` | remove the key from the set |
| `int` | `size()` | return the number of keys in the set |
| `Iterator<Key>` | iterator() | iterate keys through the set |

**Exception Filter**

* Read in a list of words from one file
* Print out all words from standard input that are {in, not in} the list.

## Symbol Table Application - Dictionary Clients

**Dictionary Lookup**

Command-line arguments:
* A CSV file
* Key field
* Value field

Code is simple:

```java
public class LookupCSV {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int keyField = Integer.parseInt(args[1]);
        int valField = Integer.parseInt(args[2]);

        ST<String, String> st = new ST<String, String>();
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            String key = tokens[keyField];
            String val = tokens[valField];
            st.put(key, val);
        }

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (!st.contains(s)) StdOut.println("Not Found");
            else                StdOut.println(st.get(s));
        }
    }
}
```
