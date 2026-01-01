import sys
from collections import defaultdict

input = sys.stdin.readline

n = int(input())
a = list(map(int, input().split()))

cnt = defaultdict(int)
ans = 0

powers = [1 << i for i in range(1, 32)]  # 2^1 تا 2^31

for x in a:
    for p in powers:
        need = p - x
        if need in cnt:
            ans += cnt[need]
    cnt[x] += 1

print(ans)
