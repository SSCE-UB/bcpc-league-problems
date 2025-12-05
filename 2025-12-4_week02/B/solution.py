n, c, o = map(int, input().split())
neutrals = n - c - o
needed = (n // 2) + 1

if c >= needed:
    print(0)
else:
    x = needed - c
    if x <= neutrals:
        print(x)
    else:
        print(-1)