from itertools import combinations

def check(candi, q, ans):
    
    for i, num_arr in enumerate(q):
        cnt = 0
        for num in num_arr:
            if num in candi:
                cnt += 1
        if cnt != ans[i]: # 같지 않으면
            return 0

    return 1 # 충족하면

def solution(n, q, ans):
    answer = 0
    
    # 가장 많이 맞는 거 저장
    max_cnt = max(ans)
    max_idx = 0
    
    # 가장 많이 맞는 거의 인덱스 저장
    for i, cnt in enumerate(ans):
        if cnt == max_cnt:
            max_idx = i
    
    # 후보와 개수 저장
    max_set = set(q[max_idx])
    other_set = set(i for i in range(1,n+1) if i not in max_set)
    other_cnt = 5-max_cnt
    
    # 후보 체크
    for m in combinations(max_set, max_cnt):
        for o in combinations(other_set, other_cnt):
            answer += check(set(m+o), q, ans)
    
    return answer