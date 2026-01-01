#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, s;
    cin >> n >> s;

    vector<string> order(n);
    unordered_map<string, int> pos;
    pos.reserve(n + s + 10);
    pos.max_load_factor(0.7f);

    for (int i = 0; i < n; i++) {
        cin >> order[i];
        pos[order[i]] = i;
    }

    for (int q = 0; q < s; q++) {
        string op;
        cin >> op;

        if (op == "insert") {
            string name;
            int p;
            cin >> name >> p;
            order.insert(order.begin() + p, name);
            for (int i = p; i < (int)order.size(); i++) pos[order[i]] = i;
        } else if (op == "depart") {
            string name;
            cin >> name;
            int i = pos[name];
            pos.erase(name);
            order.erase(order.begin() + i);
            for (int j = i; j < (int)order.size(); j++) pos[order[j]] = j;
        } else {
            string name;
            int d;
            cin >> name >> d;
            int i = pos[name];
            string bird = order[i];
            order.erase(order.begin() + i);
            int ins = i + d;
            order.insert(order.begin() + ins, bird);
            int start = min(i, ins);
            for (int j = start; j < (int)order.size(); j++) pos[order[j]] = j;
        }
    }

    for (int i = 0; i < (int)order.size(); i++) {
        if (i) cout << ' ';
        cout << order[i];
    }
    cout << '\n';
    return 0;
}
