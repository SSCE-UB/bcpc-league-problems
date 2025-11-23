N = int(input());
seen = [False] * (N + 1);

for _ in range(N):
    x = int(input());
    if x < 0 | x > 100000 :
        continue;
    
    if 0 <= x <= N:
        seen[x] = True;

for i in range( N + 1 ):
    if not seen[i]:
        print(i);
        break;

