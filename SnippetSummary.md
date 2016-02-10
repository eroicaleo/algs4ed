
# Merge Sort

## Regular Merge Sort
* `merge`, `sort`, `sort`

```java

```

**Easy to make mistakes:**
* I sometimes assign `aux[k] = a[i++]`, should be `a[k] = aux[i++]`;
* I sometimes do `less(a[j], a[i])`, should be `less(aux[j], aux[i])`;
* I sometimes do `aux[k++] = a[i++]`, should be `a[k] = aux[i++]`;
* `int mid = lo + (lo+hi)/2`, should be `int mid = lo + (hi - lo) / 2`
* One gotcha: we need allocate the aux at the top, not in the recursive
  programming.

## Bottom Up Merge Sort
* `merge`, `sort`, `insertionSort`

```java

```

## Merge Sort X
* `merge`, `sort`, `insertionSort`

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

**Easy to make mistakes:**

* In the `insertionSort`, I do `less(a[j], a[i])`, while it should be
  `less(a[j], a[j-1])`. I do `exch(a, i, j)`, while it should be `exch(a, j, j-1)`
