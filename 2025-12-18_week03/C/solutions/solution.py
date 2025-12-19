def main():
    import sys
    input = sys.stdin.readline

    n, L = map(int, input().split())
    positions = list(map(int, input().split()))

    positions.sort()

    count_without_permission = 1
    last_position = positions[0]

    for i in range(1, n):
        if positions[i] - last_position >= L:
            count_without_permission += 1
            last_position = positions[i]

    print(n - count_without_permission)


if __name__ == "__main__":
    main()
