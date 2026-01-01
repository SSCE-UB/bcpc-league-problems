#include <bits/stdc++.h>
using namespace std;

struct Ori { int t,b,n,s,w,e; };

static inline Ori up(const Ori& o){ return {o.s,o.n,o.t,o.b,o.w,o.e}; }
static inline Ori down(const Ori& o){ return {o.n,o.s,o.b,o.t,o.w,o.e}; }
static inline Ori leftt(const Ori& o){ return {o.e,o.w,o.n,o.s,o.t,o.b}; }
static inline Ori rightt(const Ori& o){ return {o.w,o.e,o.n,o.s,o.b,o.t}; }

static inline int key3(int t,int n,int w){ return t*36 + n*6 + w; }

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n,m;
    cin >> n >> m;
    vector<string> g(n);
    int sr=-1, sc=-1;
    for(int i=0;i<n;i++){
        cin >> g[i];
        for(int j=0;j<m;j++) if(g[i][j]=='s'){ sr=i; sc=j; }
    }

    vector<Ori> oris;
    unordered_map<int,int> id;
    id.reserve(64);
    id.max_load_factor(0.7f);

    auto add_ori = [&](const Ori& o){
        int k = key3(o.t,o.n,o.w);
        if(id.find(k)!=id.end()) return;
        int idx = (int)oris.size();
        id[k]=idx;
        oris.push_back(o);
    };

    add_ori({6,1,4,3,2,5});
    queue<int> q;
    q.push(0);
    while(!q.empty()){
        int cur=q.front(); q.pop();
        Ori o=oris[cur];
        Ori a[4]={up(o),down(o),leftt(o),rightt(o)};
        for(int i=0;i<4;i++){
            int k=key3(a[i].t,a[i].n,a[i].w);
            if(id.find(k)==id.end()){
                add_ori(a[i]);
                q.push(id[k]);
            }
        }
    }

    int trans[24][4];
    for(int i=0;i<24;i++){
        Ori o=oris[i];
        Ori a0=up(o), a1=down(o), a2=leftt(o), a3=rightt(o);
        trans[i][0]=id[key3(a0.t,a0.n,a0.w)];
        trans[i][1]=id[key3(a1.t,a1.n,a1.w)];
        trans[i][2]=id[key3(a2.t,a2.n,a2.w)];
        trans[i][3]=id[key3(a3.t,a3.n,a3.w)];
    }

    int startOri = id[key3(6,4,2)];

    int total = n*m*24;
    vector<unsigned char> vis(total,0);
    auto vid = [&](int r,int c,int o){ return (r*m+c)*24+o; };

    vector<vector<char>> got(n, vector<char>(m, 0));

    deque<tuple<int,int,int>> dq;
    dq.push_back({sr,sc,startOri});
    vis[vid(sr,sc,startOri)] = 1;

    if(g[sr][sc]>='1' && g[sr][sc]<='6'){
        if(oris[startOri].t == (g[sr][sc]-'0')) got[sr][sc]=1;
    }

    int dr[4]={-1,1,0,0};
    int dc[4]={0,0,-1,1};

    while(!dq.empty()){
        auto [r,c,o]=dq.front(); dq.pop_front();
        for(int k=0;k<4;k++){
            int nr=r+dr[k], nc=c+dc[k];
            if(nr<0||nr>=n||nc<0||nc>=m) continue;
            char ch=g[nr][nc];
            if(ch=='x') continue;
            int no=trans[o][k];
            int idd=vid(nr,nc,no);
            if(vis[idd]) continue;
            vis[idd]=1;
            if(ch>='1' && ch<='6'){
                if(oris[no].t == (ch-'0')) got[nr][nc]=1;
            }
            dq.push_back({nr,nc,no});
        }
    }

    int ans=0;
    for(int i=0;i<n;i++) for(int j=0;j<m;j++) if(got[i][j]) ans++;
    cout << ans << "\n";
    return 0;
}
