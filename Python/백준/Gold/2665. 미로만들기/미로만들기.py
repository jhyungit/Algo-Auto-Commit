# 0-1 BFS

import sys
from collections import deque
input = sys.stdin.readline

INF = float("inf")
dr = [-1,1,0,0]
dc = [0,0,-1,1]

def solution(n, arr):
    dq = deque()
    dist = [[INF] * (n) for _ in range(n)]
    dq.append((0,0,0)) # r,c,cost
    dist[0][0] = 0

    while dq:
        r,c,cost = dq.popleft()

        if r == n-1 and c == n-1:
            return cost
        
        if cost > dist[r][c]:
            continue

        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]

            if nr < 0 or nr >= n or nc < 0 or nc >= n:
                continue
            
            if arr[nr][nc] == '1':
                new_cost = cost
            else:
                new_cost = cost + 1
            
            if new_cost < dist[nr][nc]:
                dist[nr][nc] = new_cost
                if arr[nr][nc] == '1':
                    dq.appendleft((nr,nc,new_cost))
                else:
                    dq.append((nr,nc,new_cost))
                
n = int(input())
arr = []
for i in range(n):
    arr.append(list(input().strip()))

print(solution(n, arr))