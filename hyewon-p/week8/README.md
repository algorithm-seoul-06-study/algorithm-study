# Week8

## BOJ2636 치즈

### 🎈 해결방법 :
공기 기준으로 사방탐색해서 치즈 찾기 => 다음 라운드에 치즈를 공기로 바꾸고 또 탐색

### 💬 코멘트 :
입력 받는 게 가장 어려웠다고 생각합니다

### 📄 코드
```python
from collections import deque

def sol():
    h, w = map(int, input().split(" "))
    chz = [list(map(int, input().split(" "))) for _ in range(h)]
    visited = [[False]*w for _ in range (h)]

	# 공기인 좌표 큐
    queue = deque([])
	# 다음으로 공기가 될 좌표(C) 큐
    nextRound = deque([(0, 0)])
	# 몇라운드인지
    answer = -1
	# 마지막 C 개수
    chzCount = 0

    while nextRound:
		# 라운드가 시작되면 C를 공기로 바꿈
        queue, nextRound = nextRound, queue
        chzCount = len(queue)
        while queue:
            x1, y1 = queue.popleft()
			# 공기 기준 사방탐색 -> 맞닿은 치즈 찾기
            for x2, y2 in [(1,0),(-1,0),(0,1),(0,-1)]:
                if 0<=x1+x2<w and 0<=y1+y2<h:
					# 중복 검사 방지
                    if not visited[y1 + y2][x1 + x2]:
                        visited[y1 + y2][x1 + x2] = True
						# 공기라면 이번 라운드에 탐색
                        if chz[y1+y2][x1+x2]==0:
                            queue.append((x1+x2, y1+y2))
						# 치즈라면 다음 라운드에 탐색
                        elif chz[y1 + y2][x1 + x2] == 1:
                            nextRound.append((x1+x2, y1+y2))
        answer += 1

    print(str(answer)+"\n"+str(chzCount))

sol()
```

## BOJ5904 Moo 게임

### 🎈 해결방법 :

### 💬 코멘트 :

### 📄 코드
```python
k = 3
n = 0
def sol():
    global k, n
    n = int(input())
    i = 4
    while True:
        if checkLetter(): return
        k+=i
        calChild(i-1)
        i += 1

def calChild(i):
    global k
    if i==3:
        k+=i
        return
    if k < n - 1:
        calChild(i-1)
    if k < n-1:
        k+=i
    if k < n-1:
        calChild(i-1)


def checkLetter():
    if k == n - 1:
        print("m")
        return True
    if k >= n:
        print("o")
        return True
    return False

sol()

```

## BOJ16227 의약품 수송

### 🎈 해결방법 :
다익스트라로 최소 시간을 저장하되, 시간에 대한 남은 청결도(100분 중 얼마나 사용했는지)를 함께 저장해서 청결도보다 더 긴 시간을 가야한다면 씻고 감
### 💬 코멘트 :
두 지점 사이에 길이 하나라는 조건이 없다는 사실을 아셨나요? 저는 그걸 몰라서 10번을 틀렸습니다

### 📄 코드
```python
from collections import deque

n, k = map(int, input().split(" "))
# 거리 저장할 배열
maze = [[2e10]*(n+2) for _ in range(n+2)]
# 간선 저장할 배열
graph = [[] for _ in range(n+2)]
# 최소 시간, 남은 청결도 기록할 배열
record = [(2e10,0) for _ in range(n+2)]
# 출발지 초기화하고 시작
record[0] = (0, 100)
for i in range(k):
    u, v, t = map(int, input().split(" "))
	# 만약 두 지점 사이에 길이 여러개라면 짧은 길로 기록
    maze[u][v] = min(t,maze[u][v])
    maze[v][u] = min(t,maze[v][u])
    graph[u].append(v)
    graph[v].append(u)

# 검사할 장비 큐
queue = deque([0])
while queue:
    u = queue.popleft()
	# 연결된 장비 검사
    for v in graph[u]:
		# 거리가 100을 초과하면 고려 안 해도 됨
        if maze[u][v] <= 100:
			# 씻을 것인가?
            chg = 0
			# 남은 청결도
            leftTime = record[u][1]-maze[u][v]
            if leftTime<0 and u!=0:
                chg = 5
                leftTime = 100-maze[u][v]
			# 만약 목표 장비의 최소 시간이 (현재까지의 최소 시간+앞으로 갈 시간+씻는 시간)보다 크면 갱신
			# 같다면, 남은 청결도가 클 때만 갱신
            if (record[v][0] > record[u][0]+maze[u][v]+chg) or (record[v][0]==record[u][0]+maze[u][v]+chg and leftTime>record[v][1]):
                record[v] = (record[u][0]+maze[u][v]+chg, leftTime)
                queue.append(v)

print(record[-1][0])
```
