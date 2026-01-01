import java.io.*;
import java.util.*;

public class solution {
    static final class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream in) { this.in = in; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        String next() throws IOException {
            int c;
            do { c = read(); } while (c <= ' ' && c != -1);
            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char)c);
                c = read();
            }
            return sb.toString();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }

    static int key3(int t, int n, int w) { return t * 36 + n * 6 + w; }

    static int[] up(int t,int b,int n,int s,int w,int e){ return new int[]{s,n,t,b,w,e}; }
    static int[] down(int t,int b,int n,int s,int w,int e){ return new int[]{n,s,b,t,w,e}; }
    static int[] leftt(int t,int b,int n,int s,int w,int e){ return new int[]{e,w,n,s,t,b}; }
    static int[] rightt(int t,int b,int n,int s,int w,int e){ return new int[]{w,e,n,s,b,t}; }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();
        String[] g = new String[n];
        int sr = -1, sc = -1;
        for (int i = 0; i < n; i++) {
            g[i] = fs.next();
            int j = g[i].indexOf('s');
            if (j != -1) { sr = i; sc = j; }
        }

        int[][] oris = new int[24][6];
        HashMap<Integer, Integer> id = new HashMap<>();
        ArrayDeque<Integer> oq = new ArrayDeque<>();

        int[] start = new int[]{6,1,4,3,2,5};
        id.put(key3(start[0],start[2],start[4]), 0);
        oris[0] = start;
        oq.add(0);
        int cnt = 1;

        while (!oq.isEmpty()) {
            int cur = oq.poll();
            int[] o = oris[cur];
            int[][] ns = new int[][]{
                up(o[0],o[1],o[2],o[3],o[4],o[5]),
                down(o[0],o[1],o[2],o[3],o[4],o[5]),
                leftt(o[0],o[1],o[2],o[3],o[4],o[5]),
                rightt(o[0],o[1],o[2],o[3],o[4],o[5])
            };
            for (int i = 0; i < 4; i++) {
                int k = key3(ns[i][0], ns[i][2], ns[i][4]);
                if (!id.containsKey(k)) {
                    id.put(k, cnt);
                    oris[cnt] = ns[i];
                    oq.add(cnt);
                    cnt++;
                }
            }
        }

        int[][] trans = new int[24][4];
        for (int i = 0; i < 24; i++) {
            int[] o = oris[i];
            int[] a0 = up(o[0],o[1],o[2],o[3],o[4],o[5]);
            int[] a1 = down(o[0],o[1],o[2],o[3],o[4],o[5]);
            int[] a2 = leftt(o[0],o[1],o[2],o[3],o[4],o[5]);
            int[] a3 = rightt(o[0],o[1],o[2],o[3],o[4],o[5]);
            trans[i][0] = id.get(key3(a0[0],a0[2],a0[4]));
            trans[i][1] = id.get(key3(a1[0],a1[2],a1[4]));
            trans[i][2] = id.get(key3(a2[0],a2[2],a2[4]));
            trans[i][3] = id.get(key3(a3[0],a3[2],a3[4]));
        }

        int startOri = id.get(key3(6,4,2));

        int total = n * m * 24;
        byte[] vis = new byte[total];
        boolean[][] got = new boolean[n][m];

        ArrayDeque<int[]> dq = new ArrayDeque<>();
        int startId = ((sr * m + sc) * 24 + startOri);
        vis[startId] = 1;
        dq.add(new int[]{sr, sc, startOri});

        char ch0 = g[sr].charAt(sc);
        if (ch0 >= '1' && ch0 <= '6') {
            if (oris[startOri][0] == (ch0 - '0')) got[sr][sc] = true;
        }

        int[] dr = new int[]{-1, 1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};

        while (!dq.isEmpty()) {
            int[] st = dq.poll();
            int r = st[0], c = st[1], o = st[2];
            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k], nc = c + dc[k];
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                char ch = g[nr].charAt(nc);
                if (ch == 'x') continue;
                int no = trans[o][k];
                int idd = ((nr * m + nc) * 24 + no);
                if (vis[idd] != 0) continue;
                vis[idd] = 1;
                if (ch >= '1' && ch <= '6') {
                    if (oris[no][0] == (ch - '0')) got[nr][nc] = true;
                }
                dq.add(new int[]{nr, nc, no});
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) if (got[i][j]) ans++;
        System.out.print(ans);
        System.out.print('\n');
    }
}
