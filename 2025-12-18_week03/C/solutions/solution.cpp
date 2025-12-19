#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    long long L;
    cin >> n >> L;

    vector<long long> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    sort(a.begin(), a.end());

    int withoutPermission = 1;   
    long long lastPos = a[0];

    for (int i = 1; i < n; i++) {
        if (a[i] - lastPos >= L) {
            withoutPermission++;
            lastPos = a[i];
        }
    }

    cout << n - withoutPermission << "\n";
    return 0;
}
