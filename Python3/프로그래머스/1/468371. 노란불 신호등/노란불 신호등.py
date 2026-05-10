def solution(signals):
    answer = 0
    light = []
    
    for signal in signals:
        temp = ""
        temp += 'G' * signal[0]
        temp += 'Y' * signal[1]
        temp += 'R' * signal[2]
        temp *= 1_000_000
        light.append(list(temp))
        
    time = 1

    while time < 3_000_000:
        check = set()
        for l in light:
            check.add(l[time])
        time += 1
        if check == set('Y'):
            return time

    return -1