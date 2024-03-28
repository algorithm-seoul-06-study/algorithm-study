import sys
sys.setrecursionlimit(5000)

graph = []
visited = []
maxRoute = 0
S = ""
lcsGraph = []

def sol():
    global graph, visited, S, lcsGraph
    N, M = map(int, input().split(" "))
    S = input()
    graph = [[] for _ in range(N+1)]
    lcsGraph = [[0]*(N+1) for _ in range(len(S)+1)]
    visited = [False for _ in range(N+1)]
    for n in range(N-1):
        u, v, c = input().split(" ")
        graph[int(u)].append((int(v), c))
        graph[int(v)].append((int(u), c))
    getRoutes(1, "")
    print(maxRoute)

def getRoutes(node, route):
    global maxRoute, visited, lcsGraph
    visited[node] = True
    if len([a for a in graph[node] if not visited[a[0]]]) == 0:
        maxRoute = max(lcsGraph[-1][len(route)], maxRoute)
        return
        
    for n in graph[node]:
        if not visited[n[0]]:
            fillLCS(node, route, n)
            getRoutes(n[0], route+n[1])
            lcsGraph[len(S)][len(route)] = 0

def fillLCS(node, route, n):
    global lcsGraph
    for i in range(len(S)):
        if S[i] == n[1]:
            lcsGraph[i+1][len(route)+1] = lcsGraph[i][len(route)] + 1
        else:
            lcsGraph[i+1][len(route)+1] = max(lcsGraph[i][len(route)+1], lcsGraph[i+1][len(route)])
            
sol()