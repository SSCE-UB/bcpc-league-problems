#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;

    vector<int> phi(N + 1);
    for (int i = 1; i <= N; i++)
        phi[i] = i;

    for (int i = 2; i <= N; i++) {
        if (phi[i] == i) { // i عدد اول است
            for (int j = i; j <= N; j += i) {
                phi[j] -= phi[j] / i;
            }
        }
    }

    long long total_phi = 0;
    long long odd_phi = 0;

    for (int i = 1; i <= N; i++) {
        total_phi += phi[i];
        if (i & 1) odd_phi += phi[i];
    }

    long long answer = 2 * total_phi - 1 + odd_phi;
    cout << answer << "\n";

    return 0;
}
