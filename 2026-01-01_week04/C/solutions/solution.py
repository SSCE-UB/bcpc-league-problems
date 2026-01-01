import sys

def main():
    data = sys.stdin.buffer.read().split()
    it = iter(data)
    n = int(next(it))
    s = int(next(it))

    order = [next(it).decode() for _ in range(n)]
    pos = {name: i for i, name in enumerate(order)}

    for _ in range(s):
        op = next(it).decode()
        if op == "insert":
            name = next(it).decode()
            p = int(next(it))
            order.insert(p, name)
            for i in range(p, len(order)):
                pos[order[i]] = i
        elif op == "depart":
            name = next(it).decode()
            i = pos.pop(name)
            order.pop(i)
            for j in range(i, len(order)):
                pos[order[j]] = j
        else:
            name = next(it).decode()
            d = int(next(it))
            i = pos[name]
            bird = order.pop(i)
            ins = i + d
            order.insert(ins, bird)
            start = ins if ins < i else i
            for j in range(start, len(order)):
                pos[order[j]] = j

    sys.stdout.write(" ".join(order) + "\n")

if __name__ == "__main__":
    main()
