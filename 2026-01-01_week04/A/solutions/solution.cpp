#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    cin >> t;

    vector<string> ans;
    ans.reserve(t);

    while (t--) {
        long long x, y, z;
        cin >> x >> y >> z;

        bool ok = ((x & y & ~z) | (x & z & ~y) | (y & z & ~x)) == 0;
        ans.push_back(ok ? "YES" : "NO");
    }

    for (auto &s : ans)
        cout << s << '\n';

    return 0;
}
