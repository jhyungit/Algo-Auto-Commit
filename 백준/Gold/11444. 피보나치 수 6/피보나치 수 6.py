import sys
input = sys.stdin.readline

MOD = 1_000_000_007

# 2 X 2 행렬 두 개 곱하는 함수
def multiply(a, b):
    return [
        [(a[0][0]*b[0][0] + a[0][1]*b[1][0]) % MOD,
         (a[0][0]*b[0][1] + a[0][1]*b[1][1]) % MOD],
        [(a[1][0]*b[0][0] + a[1][1]*b[1][0]) % MOD,
         (a[1][0]*b[0][1] + a[1][1]*b[1][1]) % MOD]
    ]


def power(mat, exp):
    result = [[1,0],
              [0,1]]

    while exp > 0:
        if exp % 2:
            if exp % 2 == 1: # 지수가 홀수면 하나 떼서 곱함
                result = multiply(result, mat)
        mat = multiply(mat, mat)
        exp //= 2

    return result

n = int(input())
# 피보나치 행렬
base = [[1,1],
        [1,0]]

res = power(base, n)
print(res[0][1])