def op1(lst, l, r, x):
    for i  in range(l - 1, r):
        lst[i] += x
    return lst

def op2(lst, l, r, x):
    for i in range(l -1, r):
        lst[i] -= x
    return lst

def op3(lst, l, r):
    return (max(lst[l - 1 : r]) - min(lst[l - 1 : r]))

n, q = map(int, input().split())

lst = [int(ele) for ele in input().split()]

ans = []

while(q > 0):
    opr = [int(ele) for ele in input().split()]
    
    if opr[0] == 1:
        lst = op1(lst, opr[1], opr[2], opr[3])

    elif opr[0] == 2:
        lst = op2(lst, opr[1], opr[2], opr[3])

    elif opr[0] == 3:
        rst = op3(lst, opr[1], opr[2])
        ans.append(rst)

    q -= 1


for i in ans:
    print(i)
