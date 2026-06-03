def solution(info, n, m):
    INF = float("inf")
    dp = [INF] * n # dp[a_sum] = B 흔적 합의 최솟값
    dp[0] = 0

    for a, b in info:
        ndp = [INF] * n # 이 물건까지 반영한 새 상태
        for a_sum in range(n):
            if dp[a_sum] == INF:    # 도달 못한 상태는 무시
                continue
            b_sum = dp[a_sum]

            # (1) 물건을 A에게
            if a_sum + a < n:
                ndp[a_sum + a] = min(ndp[a_sum + a], b_sum)

            # (2) 물건을 B에게
            if b_sum + b < m:
                ndp[a_sum] = min(ndp[a_sum], b_sum + b)
                
        dp = ndp

    answer = INF
    for a_sum in range(n):
        if dp[a_sum] < m:
            answer = min(answer, a_sum)

    return answer if answer != INF else -1