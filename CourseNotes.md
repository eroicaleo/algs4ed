Notes on Algorithms
===================

# Table of Contents

* [Lecture 6 Hash Tables](#lecture-6-hash-tables)
    * [Hash Functions](#hash-functions)
    * [separable chaining](#separable-chaining)
    * [linear probing](#linear-probing)
    * [context](#context)

# <a id="lecture-6-hash-tables"></a>Lecture 6 Hash Tables

## <a id="hash-functions"></a>Hash Functions

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

## <a id="separable-chaining"></a>separable chaining
## <a id="linear-probing"></a>linear probing
## <a id="context"></a>context
