# 우승자에게 불리하게
# 우승자가 나중에 쏘기
# k점 화살 같을 경우 2등이 가져감

def solution(n, info):
    answer = [0] * 11
    max_diff = 0
    
    # 현재 점수칸, 남은 화살 수, 라이언 점수 배열
    def dfs(idx, left_arrow, lion_arr):
        nonlocal max_diff, answer
        
        # 종료 조건
        if idx == 11 or left_arrow == 0:
            temp = lion_arr[:]
            # 남은 화살 처리
            temp[10] += left_arrow # 마지막에 모두 더해줌(가장 낮은 점수를 더 많이 맞힌 경우를 return 조건)
            
            l_score, p_score = 0, 0
            # 점수 차 계산 -> 갱신
            for i in range(11):
                if temp[i] > info[i]:
                    l_score += 10 - i
                elif info[i] > 0:
                    p_score += 10 - i
            
            diff = l_score - p_score
            
            if diff > max_diff:
                max_diff = diff
                answer = temp[:]
            elif diff == max_diff and diff > 0:
                for i in range(10, -1, -1):
                    if temp[i] > answer[i]: # 현재가 더 낮은 점수 많이 쐈으면
                        answer = temp[:]
                        break
                    elif temp[i] < answer[i]: # 이미 더 낮은 점수 많이 쏨
                        break
            return
            
        
        # 1. 0발 쏘기)
        dfs(idx+1, left_arrow, lion_arr)
        
        # 2. 어피치 + 1발 쏘기)
        need = info[idx] + 1
        if left_arrow >= need:
            lion_arr[idx] = need
            dfs(idx+1, left_arrow - need, lion_arr)
            lion_arr[idx] = 0
            
    dfs(0, n, [0] * 11)
    return answer if max_diff > 0 else [-1]