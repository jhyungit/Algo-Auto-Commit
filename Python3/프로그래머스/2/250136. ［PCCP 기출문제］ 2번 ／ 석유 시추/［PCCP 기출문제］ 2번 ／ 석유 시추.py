from collections import deque, defaultdict

dr = [-1,1,0,0]
dc = [0,0,-1,1]

def flood_fill(sr, sc, land, cnt):
    area[sr][sc] = cnt
    land[sr][sc] = 0 # 방문체크 대신
    df_dict[cnt] += 1
    can_take[sc].add(cnt)
    
    dq = deque([(sr,sc)])
    while dq:
        cur = dq.popleft()
        r, c = cur
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            # 범위 체크
            if nr < 0 or nr >= h or nc < 0 or nc >= w:
                continue
            # 석유 체크
            if land[nr][nc] == 0:
                continue
            dq.append((nr,nc))
            area[nr][nc] = cnt
            land[nr][nc] = 0
            df_dict[cnt] += 1
            can_take[nc].add(cnt) # 열에 걸치는 석유 지역

def solution(land):
    global area, h, w, df_dict, can_take
    h = len(land)
    w = len(land[0])
    area = [[0] * w for _ in range(h)]
    df_dict = defaultdict(int)
    can_take = [set() for _ in range(w)] # 같은 열에 있는 석유 구역
    
    cnt = 1
    for sr in range(h):
        for sc in range(w):
            if land[sr][sc]: # 석유 있으면
                flood_fill(sr, sc, land, cnt)
                cnt += 1
                
    answer = 0
    for oil_num in can_take:
        temp = 0
        for oil in oil_num:
            temp += df_dict[oil]
        answer = max(answer, temp)
    
    return answer