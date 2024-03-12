from collections import deque

def sol():
	# 입력 받기~
    W, H = map(int, input().split(" "))
	# 지도 저장할 배열
    maze = []
	# 거울 갯수 저장할 배열
	# 최소값으로 갱신할 예정이므로 무한으로 초기화
    dp = [[[2e10, 2e10] for i in range(W)]for j in range(H)]
    start = []
    queue = deque()
    for i in range(H):
        line = list(input())
        for j in range(W):
			# 칸들 좌표 저장
            if line[j] == "C":
                start.append((j, i))
        maze.append(line)
	# 시작 지점 큐에 넣고 출발
    queue.append(start[0])
    dp[start[0][1]][start[0][0]] = [0, 0]

	while(queue):
        x, y = queue.popleft()
		# 사방탐색
        for dir in ((0,1),(0,-1),(1,0),(-1,0)):
            newX, newY = x+dir[0], y+dir[1]
			# 지도 안에 있는 위치인지 확인
            if 0<=newX < W and 0<=newY<H:
				# 좌우
                if dir[0]!=0 and maze[newY][newX]!="*":
                    if dp[newY][newX][0]>min(dp[y][x][0], dp[y][x][1]+1):
                        dp[newY][newX][0] = min(dp[y][x][0], dp[y][x][1]+1)
                        queue.append((newX, newY))
				# 상하
                elif dir[1]!=0 and maze[newY][newX]!="*":
                    if dp[newY][newX][1]>min(dp[y][x][0]+1, dp[y][x][1]):
                        dp[newY][newX][1] = min(dp[y][x][0]+1, dp[y][x][1])
						queue.append((newX, newY))
   
    print(min(dp[start[1][1]][start[1][0]]))

sol()