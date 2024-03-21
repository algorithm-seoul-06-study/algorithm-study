from collections import deque

def sol():
    h, w = map(int, input().split(" "))
    chz = [list(map(int, input().split(" "))) for _ in range(h)]
    visited = [[False]*w for _ in range (h)]

    queue = deque([])
    nextRound = deque([(0, 0)])
    answer = -1
    chzCount = 0

    while nextRound:
        queue, nextRound = nextRound, queue

        chzCount = len(queue)
        while queue:
            x1, y1 = queue.popleft()
            for x2, y2 in [(1,0),(-1,0),(0,1),(0,-1)]:
                if 0<=x1+x2<w and 0<=y1+y2<h:
                    if not visited[y1 + y2][x1 + x2]:
                        visited[y1 + y2][x1 + x2] = True
                        if chz[y1+y2][x1+x2]==0:
                            queue.append((x1+x2, y1+y2))
                        elif chz[y1 + y2][x1 + x2] == 1:
                            nextRound.append((x1+x2, y1+y2))
        answer += 1

    print(str(answer)+"\n"+str(chzCount))

sol()