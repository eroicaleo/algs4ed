#include <algorithm>
#include <iostream>
#include <random>
#include <vector>

using namespace std;

void sort(vector<int> &a, int lo, int hi) {
    // Return condition
    if (hi <= lo)
        return;

    int lt = lo, gt = hi, i = lo;
    int v = a[lo];
    while (i <= gt) {
        if (v > a[i])      swap(a[i++], a[lt++]);
        else if (v < a[i]) swap(a[i], a[gt--]);
        else               ++i;
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

    // Generate random number
    vector<int> a(10);

    for (auto &i : a) {
        i = rand() % 5;
    }
    for (auto i : a) {
        cout << i << " ";
    }
    cout << endl;

    sort(a);
    for (auto i : a) {
        cout << i << " ";
    }
    cout << endl;
    return 0;
}
