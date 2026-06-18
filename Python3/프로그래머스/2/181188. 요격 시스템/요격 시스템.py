from collections import deque

def solution(targets):
    answer = 0
    targets.sort(key = lambda x : x[1])
    dq = deque(targets)
    
    while dq:
        cur = dq.popleft()
        s,e = cur
        
        cnt = 0
        for ns, ne in dq:
            if ns < e:
                cnt += 1
                continue
            else:
                break
        while cnt:
            dq.popleft()
            cnt -= 1

        answer += 1
        
    return answer