from collections import deque
# 잠시 멈춘 건 스택으로 구현 FILO

def minute(plans):
    for subject, start, rt in plans: # rt(running_time)
        h, m = map(int, start.split(":"))
        t = h * 60 + m
        min_plans.append((subject, t, int(rt)))


def solution(plans):
    global min_plans
    min_plans = []
    
    # 시작시각 -> 분으로 변환
    minute(plans)
    
    # 시작시각 기준 정렬
    min_plans.sort(key = lambda x : x[1])
    task = deque(min_plans) # task 정렬된 거 dq로
    
    answer = []
    stopped = []
    
    while task:
        cur = task.popleft()
        subject, start, time = cur # 현재 과목
        
        # 현재가 마지막 과목이면
        if task == deque():
            answer.append(subject)
            break
        
        n_subject, n_start, n_time = task[0]# 다음 과목
        
        if start + time <= n_start:
            # 다음 과목 전에 끝나는 경우
            answer.append(subject)
            # 여유 시간으로 멈춘 과제 재개
            free = n_start - (start + time)
            while stopped and free > 0:
                s_subject, s_remain = stopped.pop()
                if s_remain <= free:
                    answer.append(s_subject)
                    free -= s_remain
                else:
                    stopped.append((s_subject, s_remain - free))
                    free = 0
        else:
            stopped.append((subject, time - n_start + start))
    
    if stopped:
        while stopped:
            answer.append(stopped.pop()[0])
    
    return answer