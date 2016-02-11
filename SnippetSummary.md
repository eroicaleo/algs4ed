
# Merge Sort

## Regular Merge Sort, practiced: 2
* `merge`, `sort`, `sort`

```java
private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
    isSorted(a, lo, mid);
    isSorted(a, mid+1, hi);

    for (int k = lo; k <= hi; k++)
        aux[k] = a[k];

    int i = lo, j = mid + 1;
    for (int k = lo; k <= hi; k++) {
        if      (i > mid)              a[k] = aux[j++];
        else if (j > hi)               a[k] = aux[i++];
        else if (less(aux[j], aux[i])) a[k] = aux[j++];
        else                           a[k] = aux[i++];
    }

    isSorted(a, lo, hi);
}

private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
    if (hi <= lo)
        return;

    int mid = lo + (hi - lo) / 2;
    sort(a, aux, lo, mid);
    sort(a, aux, mid+1, hi);
    merge(a, aux, lo, mid, hi);
}

private static void sort(Comparable[] a) {
    Comparable[] aux = new Comparable[a.length];
    sort(a, aux, 0, a.length-1);
}
```

**Easy to make mistakes:**
* I sometimes assign `aux[k] = a[i++]`, should be `a[k] = aux[i++]`;
* I sometimes do `less(a[j], a[i])`, should be `less(aux[j], aux[i])`;
* I sometimes do `aux[k++] = a[i++]`, should be `a[k] = aux[i++]`;
* `int mid = lo + (lo+hi)/2`, should be `int mid = lo + (hi - lo) / 2`
* One gotcha: we need allocate the aux at the top, not in the recursive
  programming.

## Bottom Up Merge Sort, practiced: 2
* `merge` (same as regular merge sort), `sort`

```java
public static void sort(Comparable[] a) {
    int N = a.length;
    Comparable[] aux = new Comparable[N];
    for (int n = 1; n < N; n = n + n) {
        for (int i = 0; i < N - n; i += n + n) {
            int lo = i;
            int mid = i + n - 1;
            int hi = Math.min(i + n + n - 1, N - 1);
            merge(a, aux, lo, mid, hi);
        }
    }

    assert isSorted(a);
}
```

## Merge Sort X, practiced: 2
* `merge`, `insertionSort`, `sort`

**Easy to make mistakes:**
* In the `insertionSort`, I do `less(a[j], a[i])`, while it should be
  `less(a[j], a[j-1])`. I do `exch(a, i, j)`, while it should be `exch(a, j, j-1)`

```java
public static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
    assert isSorted(src, lo, mid);
    assert isSorted(src, mid+1, hi);

    int i = lo, j = mid + 1;
    for (int k = lo; k <= hi; k++) {
        if (i > mid)                   dst[k] = src[j++];
        else if (j > hi)               dst[k] = src[i++];
        else if (less(src[j], src[i])) dst[k] = src[j++];
        else                           dst[k] = src[i++];
    }

    assert isSorted(dst, lo, hi);
}

private static void insertionSort(Comparable[] a, int lo, int hi){
    for (int i = lo; i <= hi; i++)
        for (int j = i; j > lo && less(a[j], a[j-1]); j--)
            exch(a, j, j-1);
}

public static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
    if (hi <= lo + CUT_OFF) {
        insertionSort(dst, lo, hi);
        return;
    }

    int mid = lo + (hi - lo) / 2;
    sort(dst, src, lo, mid);
    sort(dst, src, mid+1, hi);

    if (!less(src[mid+1], src[mid])) {
        System.arraycopy(src, lo, dst, lo, hi-lo+1);
        return;
    }
    merge(src, dst, lo, mid, hi);
}

public static void sort(Comparable[] a) {
    Comparable[] aux = a.clone();
    sort(aux, a, 0, a.length-1);
    assert isSorted(a);
}
```


# Quick Sort

## Regular Quick Sort + Quick Select, practiced: 1

* `partition`, `sort`, `select`

**Easy to make mistakes:**
* In quick select, I do `if (i < k) lo = i`, should be `if (i < k) lo = i + 1`.
  Otherwise, it can be infinite loop. For example, `lo = 0, hi = 2, a = {4, 8, 11}, k = 1`.
  Then `i = 0` all the time.
* In `partition`, I do `exch(a, 0, j);`, should be `exch(a, 0, j);`
* In `select`, I do `while (lo <= hi)`, should be `while (hi > lo)`, otherwise,
  when `k = lo = hi = 9`, there will be `ArrayIndexOutOfBoundsException` throwed
  by `partition`, because of `while (less(a[++i], v))`.

```java
private static int partition(Comparable[] a, int lo, int hi) {
    int i = lo, j = hi + 1;
    Comparable v = a[lo];

    while (true) {

        while (less(a[++i], v))
            if (i == hi) break;

        while (less(v, a[--j]))
            if (j == lo) break;

        if (i >= j) break;

        exch(a, i, j);
    }

    exch(a, lo, j);
    return j;
}

private static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) return;
    int j = partition(a, lo, hi);
    sort(a, lo, j-1);
    sort(a, j+1, hi);
    assert isSorted(a, lo, hi);
}

public static void sort(Comparable[] a) {
    StdRandom.shuffle(a);
    sort(a, 0, a.length-1);
    assert isSorted(a);
}

public static Comparable select(Comparable[] a, int k) {
    if (k < 0 || k >= a.length) {
        throw new IndexOutOfBoundsException("Selected element out of bounds");
    }
    StdRandom.shuffle(a);
    int lo = 0, hi = a.length - 1;
    while (hi > lo) {
        int i = partition(a, lo, hi);
        if      (i > k) hi = i - 1;
        else if (i < k) lo = i + 1;
        else return a[i];
    }
    return a[lo];
}

```

## Quick Sort 3 Way

**Easy to make mistakes:**

```java
private static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) return;
    int lt = lo, gt = hi;
    Comparable v = a[lo];
    int i = lo;
    while (i <= gt) {
        int cmp = a[i].compareTo(v);
        if      (cmp < 0) exch(a, lt++, i++);
        else if (cmp > 0) exch(a, i, gt--);
        else              i++;
    }

    sort(a, 0, lt-1);
    sort(a, gt+1, hi);
    assert isSorted(a, lo, hi);
}

public static void sort(Comparable[] a) {
    StdRandom.shuffle(a);
    sort(a, 0, a.length-1);
    assert isSorted(a);
}

```
