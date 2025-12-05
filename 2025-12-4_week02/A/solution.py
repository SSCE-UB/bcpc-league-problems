from collections import Counter

t = int(input())

while(t > 0):
    n = int(input())
    r = 0 # sum of removals
    lst = [int(ele) for ele in input().split()]
    freq = Counter(lst)

    for x, cnt in freq.items():
        if x == 0:
            r += cnt
        else:
            if cnt > x:
                r += cnt - x
            elif cnt < x:
                r += cnt
    print(r)

    t -= 1