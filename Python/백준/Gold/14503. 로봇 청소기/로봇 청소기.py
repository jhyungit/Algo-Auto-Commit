# 구현
import sys
input = sys.stdin.readline

# 북동남서
dr = [-1,0,1,0]
dc = [0,1,0,-1]

def solution(r,c,d):
    cnt = 1
    room[r][c] = -1

    while True:
        # 현재 칸 청소
        if room[r][c] == 0:
            room[r][c] = -1
            cnt += 1

        moved = False
        # 회전하며 탐색
        for i in range(4):
            # 왼쪽 회전
            d = (d + 3) % 4

            # 청소해야 하면 이동
            if room[r + dr[d]][c + dc[d]] == 0:
                r = r + dr[d]
                c = c + dc[d]
                moved = True
                break
        
        # 움직이지 않았으면 후진 시도 
        if not moved:
            reverseSide = (d + 2) % 4
            nr = r + dr[reverseSide]
            nc = c + dc[reverseSide]

            if room[nr][nc] == 1:
                break
            
            r, c = nr, nc

    return cnt
                
N, M = map(int, input().split())
r,c,d = map(int, input().split())
room = []
for _ in range(N):
    room.append(list(map(int, input().split())))

print(solution(r,c,d))