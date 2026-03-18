# 2차원 차분 배열 활용

def attack(r1, c1, r2, c2, degree, temp):
    # -        +
    # +        -
    temp[r1][c1] -= degree
    temp[r1][c2+1] += degree
    temp[r2+1][c1] += degree
    temp[r2+1][c2+1] -= degree

def recover(r1, c1, r2, c2, degree, temp):
    # +        -
    # -        +
    temp[r1][c1] += degree
    temp[r1][c2+1] -= degree
    temp[r2+1][c1] -= degree
    temp[r2+1][c2+1] += degree

def solution(board, skill):
    r = len(board)
    c = len(board[0])
    
    temp = [[0] * (c+1) for _ in range(r+1)] # 차분 배열 초기화
    
    for sk in skill:
        typ, r1, c1, r2, c2, degree = sk
        if(typ == 1): # 공격
            attack(r1, c1, r2, c2, degree, temp)
        else: # 회복
            recover(r1, c1, r2, c2, degree, temp)
            
    # 가로 누적
    for i in range(r+1):
        for j in range(1,c+1):
            temp[i][j] += temp[i][j-1]
    
    # 세로 누적
    for j in range(c+1):
        for i in range(1,r+1):
            temp[i][j] += temp[i-1][j]
    
    answer = 0
    
    for i in range(r):
        for j in range(c):
            if board[i][j] + temp[i][j] > 0:
                answer += 1
    
    return answer