def min_insertion_to_palindorme(s):

    n = len(s)
    rev = s[::-1]
    
    dp =[[0]*(n+1) for i in range(n+1)]
    for i in range(1, n+1):
        for j in range (1, n+1):
            if(s[i-1] == rev[j-1]):
                dp[i][j] = dp[i-1][j-1] + 1
            else:
                dp[i][j] = max(dp[i-1][j], dp[i][j-1])
    lps = dp[n][n]
    return n - lps




s= input("")
print(min_insertion_to_palindorme(s))
