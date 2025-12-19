def fractured_ledger(N):
    # محاسبه phi برای همه اعداد تا N با sieve
    phi = list(range(N + 1))
    for i in range(2, N + 1):
        if phi[i] == i:  # i عدد اول است
            for j in range(i, N + 1, i):
                phi[j] -= phi[j] // i

    # مجموع phi ها
    total_phi = sum(phi[1:])

    # مجموع phi برای اعداد فرد
    odd_phi = sum(phi[i] for i in range(1, N + 1, 2))

    # فرمول نهایی
    return 2 * total_phi - 1 + odd_phi


# ورودی
N = int(input())
print(fractured_ledger(N))
