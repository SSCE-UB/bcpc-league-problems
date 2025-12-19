import sys

def cat(c: str) -> int:
    o = ord(c)
    if 65 <= o <= 90:   # A-Z
        return 0
    if 97 <= o <= 122:  # a-z
        return 1
    return 2            # 0-9

def solve_one(s: str) -> int:
    n = len(s)
    cnt = [0, 0, 0]
    have = 0
    ans = 10**9
    l = 0

    for r, ch in enumerate(s):
        t = cat(ch)
        if cnt[t] == 0:
            have += 1
        cnt[t] += 1

        while have == 3 and (r - l + 1) >= 6:
            ans = min(ans, r - l + 1)
            tl = cat(s[l])
            cnt[tl] -= 1
            if cnt[tl] == 0:
                have -= 1
            l += 1

    return 0 if ans == 10**9 else ans

def main():
    data = sys.stdin.read().strip().split()
    if not data:
        return
    N = int(data[0])
    out = []
    idx = 1
    for _ in range(N):
        s = data[idx]
        idx += 1
        out.append(str(solve_one(s)))
    sys.stdout.write("\n".join(out))

if __name__ == "__main__":
    main()