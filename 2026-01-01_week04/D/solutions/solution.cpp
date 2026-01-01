#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    vector<long long> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];

    unordered_map<long long, long long> cnt;
    long long ans = 0;

    for (long long x : a) {
        for (int i = 1; i <= 31; i++) {
            long long p = 1LL << i;
            long long need = p - x;
            if (cnt.count(need)) {
                ans += cnt[need];
            }
        }
        cnt[x]++;
    }

    cout << ans << "\n";
    return 0;
}
