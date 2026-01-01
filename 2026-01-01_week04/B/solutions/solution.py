import sys
from collections import deque

def roll_up(t, b, n, s, w, e):
    return s, n, t, b, w, e

def roll_down(t, b, n, s, w, e):
    return n, s, b, t, w, e

def roll_left(t, b, n, s, w, e):
    return e, w, n, s, t, b

def roll_right(t, b, n, s, w, e):
    return w, e, n, s, b, t

def main():
    data = sys.stdin.buffer.read().split()
    if not data:
        return
    it = iter(data)
    n = int(next(it))
    m = int(next(it))
    grid = []
    sr = sc = -1
    for i in range(n):
        row = next(it).decode()
        grid.append(row)
        j = row.find('s')
        if j != -1:
            sr, sc = i, j

    ori_list = []
    key_to_id = {}

    def key(t, n_, w):
        return t * 36 + n_ * 6 + w

    def add_ori(t, b, n_, s, w, e):
        k = key(t, n_, w)
        if k in key_to_id:
            return
        idx = len(ori_list)
        key_to_id[k] = idx
        ori_list.append((t, b, n_, s, w, e))

    add_ori(6, 1, 4, 3, 2, 5)

    q = deque([0])
    while q:
        oid = q.popleft()
        t, b, nn, ss, w, e = ori_list[oid]
        for fn in (roll_up, roll_down, roll_left, roll_right):
            nt, nb, nnn, nss, nw, ne = fn(t, b, nn, ss, w, e)
            k = key(nt, nnn, nw)
            if k not in key_to_id:
                add_ori(nt, nb, nnn, nss, nw, ne)
                q.append(key_to_id[k])

    trans = [[0]*4 for _ in range(24)]
    for oid, (t, b, nn, ss, w, e) in enumerate(ori_list):
        nt = roll_up(t, b, nn, ss, w, e)
        trans[oid][0] = key_to_id[key(nt[0], nt[2], nt[4])]
        nt = roll_down(t, b, nn, ss, w, e)
        trans[oid][1] = key_to_id[key(nt[0], nt[2], nt[4])]
        nt = roll_left(t, b, nn, ss, w, e)
        trans[oid][2] = key_to_id[key(nt[0], nt[2], nt[4])]
        nt = roll_right(t, b, nn, ss, w, e)
        trans[oid][3] = key_to_id[key(nt[0], nt[2], nt[4])]

    start_k = key(6, 4, 2)
    start_ori = key_to_id[start_k]

    total_states = n * m * 24
    vis = bytearray(total_states)
    def vid(r, c, o):
        return (r * m + c) * 24 + o

    got = [[False]*m for _ in range(n)]
    dq = deque()
    dq.append((sr, sc, start_ori))
    vis[vid(sr, sc, start_ori)] = 1

    if '1' <= grid[sr][sc] <= '6':
        if ori_list[start_ori][0] == int(grid[sr][sc]):
            got[sr][sc] = True

    dr = (-1, 1, 0, 0)
    dc = (0, 0, -1, 1)

    while dq:
        r, c, o = dq.popleft()
        top = ori_list[o][0]
        for kdir in range(4):
            nr = r + dr[kdir]
            nc = c + dc[kdir]
            if nr < 0 or nr >= n or nc < 0 or nc >= m:
                continue
            ch = grid[nr][nc]
            if ch == 'x':
                continue
            no = trans[o][kdir]
            idx = vid(nr, nc, no)
            if vis[idx]:
                continue
            vis[idx] = 1
            if '1' <= ch <= '6':
                if ori_list[no][0] == int(ch):
                    got[nr][nc] = True
            dq.append((nr, nc, no))

    ans = 0
    for i in range(n):
        for j in range(m):
            if got[i][j]:
                ans += 1
    sys.stdout.write(str(ans) + "\n")

if __name__ == "__main__":
    main()
