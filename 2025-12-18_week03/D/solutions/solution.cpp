#include <bits/stdc++.h>
using namespace std;

static inline int cat(char c) {
    if ('A' <= c && c <= 'Z') return 0; // upper
    if ('a' <= c && c <= 'z') return 1; // lower
    return 2;                           // digit (0-9)
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;
    while (N--) {
        string s;
        cin >> s;
        int n = (int)s.size();

        int cnt[3] = {0, 0, 0};
        int have = 0;
        int ans = INT_MAX;

        int l = 0;
        for (int r = 0; r < n; r++) {
            int t = cat(s[r]);
            if (cnt[t]++ == 0) have++;

            while (have == 3 && (r - l + 1) >= 6) {
                ans = min(ans, r - l + 1);
                int tl = cat(s[l]);
                if (--cnt[tl] == 0) have--;
                l++;
            }
        }

        cout << (ans == INT_MAX ? 0 : ans) << "\n";
    }
    return 0;
}