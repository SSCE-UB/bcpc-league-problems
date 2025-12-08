import sys

input = sys.stdin.readline

t = int(input().strip())
for _ in range(t):
    s = input().strip()

    cntP = s.count('P')
    cntA = s.count('A')

    cond1 = cntP >= cntA

    max_run_L = 0
    cur = 0
    for ch in s:
        if ch == 'L':
            cur += 1
            if cur > max_run_L:
                max_run_L = cur
        else:
            cur = 0
    cond2 = max_run_L <= 2

    if cond1 and cond2:
        print("Balanced")
    else:
        print("Unbalanced")
