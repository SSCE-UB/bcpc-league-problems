#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, q;
    cin >> n >> q;

    vector<long long> lst(n);
    for (int i = 0; i < n; i++) {
        cin >> lst[i];
    }

    vector<long long> ans;

    while (q--) {
        int op;
        cin >> op;

        if (op == 1) {
            int l, r;
            long long x;
            cin >> l >> r >> x;
            for (int i = l - 1; i < r; i++) {
                lst[i] += x;
            }
        }
        else if (op == 2) {
            int l, r;
            long long x;
            cin >> l >> r >> x;
            for (int i = l - 1; i < r; i++) {
                lst[i] -= x;
            }
        }
        else if (op == 3) {
            int l, r;
            cin >> l >> r;
            long long mx = lst[l - 1];
            long long mn = lst[l - 1];
            for (int i = l - 1; i < r; i++) {
                mx = max(mx, lst[i]);
                mn = min(mn, lst[i]);
            }
            ans.push_back(mx - mn);
        }
    }

    for (long long x : ans) {
        cout << x << '\n';
    }

    return 0;
}
