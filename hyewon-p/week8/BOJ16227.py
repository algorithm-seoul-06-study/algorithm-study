from collections import deque

n, k = map(int, input().split(" "))
maze = [[2e10]*(n+2) for _ in range(n+2)]
graph = [[] for _ in range(n+2)]
record = [(2e10,0) for _ in range(n+2)]
record[0] = (0, 100)
for i in range(k):
    u, v, t = map(int, input().split(" "))
    maze[u][v] = min(t,maze[u][v])
    maze[v][u] = min(t,maze[v][u])
    graph[u].append(v)
    graph[v].append(u)
queue = deque([0])
while queue:
    u = queue.popleft()
    for v in graph[u]:
        if maze[u][v] <= 100:
            chg = 0
            leftTime = record[u][1]-maze[u][v]
            if leftTime<0 and u!=0:
                chg = 5
                leftTime = 100-maze[u][v]
            if (record[v][0] > record[u][0]+maze[u][v]+chg) or (record[v][0]==record[u][0]+maze[u][v]+chg and leftTime>record[v][1]):
                record[v] = (record[u][0]+maze[u][v]+chg, leftTime)
                queue.append(v)

print(record[-1][0])