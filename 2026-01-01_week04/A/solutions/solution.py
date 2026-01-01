def solve():
    import sys
    input = sys.stdin.readline

    t = int(input())
    answers = []

    for _ in range(t):
        x, y, z = map(int, input().split())
        ok = ((x & y & ~z) | (x & z & ~y) | (y & z & ~x)) == 0
        answers.append("YES" if ok else "NO")

    sys.stdout.write("\n".join(answers))

solve()