#include <iostream>
#include <random>
#include <vector>

using namespace std;

void merge(vector<int> &a, vector<int> &aux, int lo, int mid, int hi) {
    for (int k = lo; k <= hi; ++k) {
        aux[k] = a[k];
    }

    int i = lo, j = mid+1;
    for (int k = lo; k <= hi; ++k) {
        if (j > hi)               a[k] = aux[i++];
        else if (i > mid)         a[k] = aux[j++];
        else if (aux[j] < aux[i]) a[k] = aux[j++];
        else                      a[k] = aux[i++];
    }

    return;
}

void sort(vector<int> &a, vector<int> &aux, int lo, int hi) {
    if (hi <= lo) return;

    int mid = lo + (hi-lo)/2;
    sort(a, aux, lo, mid);
    sort(a, aux, mid+1, hi);

    merge(a, aux, lo, mid, hi);
}

void sort(vector<int> &a) {
    
    vector<int> aux(a);
    sort(a, aux, 0, a.size()-1);
}

int main() {

    std::random_device rd;
    std::mt19937 mt(rd());
    uniform_int_distribution<int> dist(0, 5);

    vector<int> a(10, 0);
    for (size_t i = 0; i < a.size(); ++i) {
        a[i] = dist(mt);
        cout << a[i] << " ";
    }
    cout << endl;

    sort(a);

    for (size_t i = 0; i < a.size(); ++i) {
        cout << a[i] << " ";
    }
    cout << endl;
}
