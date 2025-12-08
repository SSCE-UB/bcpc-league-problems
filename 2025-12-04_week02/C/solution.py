while True:
    n_line = input().strip()
    if not n_line:
        continue
    n = int(n_line)
    if n == 0:
        break
    
    words = [input().strip() for _ in range(n)]
    steps = 0
    
    while True:
        new_words = [word[1:] for word in words]
        
        if any(word == "" for word in new_words):
            break
        
        if len(set(new_words)) != len(new_words):
            break
        
        words = new_words
        steps += 1
    
    print(steps)