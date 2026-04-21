from collections import deque
# 0 양, 1 늑대

def dfs(graph, info, node, sheep, wolf, candidates):
    # 양 일 때
    if info[node] == 0:
        sheep += 1
    else:
        wolf += 1
    
    if sheep <= wolf:
        return 0
    
    next_candidates = [c for c in candidates if c != node] + graph[node]
    
    best = sheep
    for next_node in next_candidates:
        best = max(best, dfs(graph, info, next_node, sheep, wolf , next_candidates))
        
    return best

def solution(info, edges):
    answer = 0
    
    graph = [[] for _ in range(len(info))]
    
    for u,v in edges:
        graph[u].append(v)
        
    return dfs(graph, info, 0, 0, 0, [0]) # graph, info, node, sheep, wolf, candidates