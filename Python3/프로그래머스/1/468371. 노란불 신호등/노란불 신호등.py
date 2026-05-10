from math import gcd # 최대공약수
from functools import reduce # 누적계산

# 두 수의 최소공배수 구하기
def lcm(a,b):
    return a * b // gcd(a,b)

def is_all_yellow(lights, time):
    return all(light[(time - 1) % len(light)] == 'Y' for light in lights)

def solution(signals):
    lights = ['G'*g + 'Y'*y + 'R'*r for g,y,r in signals] # ['GGYRR', 'GGGGGYR'] 형태
    periods = [len(light) for light in lights] # 각 주기
    
     # 모든 주기의 최소공배수
    limit = reduce(lcm, periods)
    
    time = 1
    while time <= limit:
        if is_all_yellow(lights, time):
            return time
        time += 1
    return -1
