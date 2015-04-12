#include <algorithm>
#include <iostream>
#include <random>
#include <vector>

using namespace std;

void sort(vector<int> &a, size_t lo, size_t hi) {
    // Return condition
    if (hi <= lo)
        return;

    size_t lt = lo, gt = hi, i = lo;
    int v = a[lo];
    while (i <= gt) {
        if (v > a[i])      swap(a[i++], a[lt++]);
        else if (v < a[i]) swap(a[i], a[gt--]);
        else               ++lt;
    }

    sort(a, lo, lt-1);
    sort(a, gt+1, hi);
}

void sort(vector<int> &a) {
    // Shuffle the vector
    auto engine = default_random_engine {};
    shuffle(begin(a), end(a), engine);

    // sort the array
    sort(a, 0, a.size()-1);
}

int main() {
    return 0;
}
