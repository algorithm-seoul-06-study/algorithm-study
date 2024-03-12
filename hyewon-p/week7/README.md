# Week7

## BOJ9328 열쇠

### 🎈 해결방법 :
열 수 없는 문이라면 나중으로 미뤄가며 검사... 메모리 초과가 떠서 큐에 저장하기 전에 조건을 이것저것 검사해줘야 했다

### 💬 코멘트 :
코드가 너무 길어서 힘들다

### 📄 코드
```java
import java.util.*;
import java.io.*;

public class BOJ9328 {
	static char[][] maze;
	static int h, w;
	static HashMap<Character, Boolean> keys;
	static Queue<Coor> queue;
	static int docCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			// 변수 초기화
			queue = new LinkedList<>();
			docCount = 0;
			keys = new HashMap<>();

			// 인풋 받는 내용이 너무 길어서 분리
			getInput(br);

			// 방문했었는지 기록할 배열
			boolean[][] visited = new boolean[h][w];
			
			int answer = 0;
			// 움직이지 못하고 턴 넘기는 경우 카운트
			// 만약 큐를 다 돌았는데 모든 경우에 움직이지 못한다면, break
			int limit = 0;
			while (!queue.isEmpty()) {
				// 문서 다 찾았으면 탈출
				if (docCount == answer) break;
				// 다 돌았는데 한번도 움직이지 못함
				if (limit == queue.size() + 1) break;

				Coor curr = queue.poll();

				// 만약 현재 위치가 문이 아니고 방문했었다면 패스
				if (!checkIfDoor(curr.x, curr.y) && visited[curr.y][curr.x]) continue;

				// 방문 표시
				visited[curr.y][curr.x] = true;

				// 만약 현재 위치가 열쇠라면 hashmap에 기록
				if (checkIfKey(curr.x, curr.y)) {
					keys.put(maze[curr.y][curr.x], true);
				// 만약 현재 위치가 문이라면
				} else if (checkIfDoor(curr.x, curr.y)) {
					// 열쇠가 존재하는 문인가?
					if (checkIfValidDoor(curr.x, curr.y)) {
						// 열쇠가 존재는 하나 아직 얻지 못함
						if (!keys.get(Character.toLowerCase(maze[curr.y][curr.x]))) {
							// 미루자
							limit++;
							queue.add(curr);
							continue;
						}
					// 열쇠가 애초에 없으면 버림
					} else {
						continue;
					}
				// 현재 자리가 문서인 경우
				} else if (maze[curr.y][curr.x] == '$') {
					answer++;
				}

				// 움직였으면 초기화
				limit = 0;

				// 사방을 탐색
				for (int[] dir : new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } }) {
					// 이동할 수 없거나, 방문한 적 있는 곳이라면 패스
					if (!checkIfMoveable(curr.x+dir[0], curr.y+dir[1]) || visited[curr.y + dir[1]][curr.x + dir[0]])
						continue;
					// 만약 문이고 열쇠가 없다면 방문 표시하고 패스
					if (checkIfDoor(curr.x + dir[0], curr.y + dir[1])
							&& !checkIfValidDoor(curr.x + dir[0], curr.y + dir[1])) {
						visited[curr.y + dir[1]][curr.x + dir[0]] = true;
						continue;
					}
					// 모든 조건을 통과했다면 다음 목적지 후보로 큐에 추가
					queue.add(new Coor(curr.x + dir[0], curr.y + dir[1]));
				}

			}
			System.out.println(answer);
		}

	}

	static boolean checkIfMoveable(int x, int y) {
		if (!checkIfInMaze(x, y)) return false;
		if (maze[y][x] == '*') return false;
		return true;
	}

	static boolean checkIfInMaze(int x, int y) {
		if (y < 0 || y >= h)
			return false;
		if (x < 0 || x >= w)
			return false;
		return true;
	}

	static boolean checkIfKey(int x, int y) {
		if (maze[y][x] >= 'a' && maze[y][x] <= 'z') {
			return true;
		}
		return false;
	}

	static boolean checkIfDoor(int x, int y) {
		if (maze[y][x] >= 'A' && maze[y][x] <= 'Z') {
			return true;
		}
		return false;

	}

	// 문의 소문자가 hashmap에 존재하는지 검사
	static boolean checkIfValidDoor(int x, int y) {
		if (keys.containsKey(Character.toLowerCase(maze[y][x]))) {
			return true;
		}
		return false;
	}

	static void getInput(BufferedReader br) throws IOException {
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		maze = new char[h][w];

		for (int y = 0; y < h; y++) {
			String line = br.readLine();
			for (int x = 0; x < w; x++) {
				if (line.charAt(x) == '$') {
					docCount++;
				}
				// hashmap에 맵에 존재하는 열쇠들을 모두 등록
				if (line.charAt(x) >= 'a' && line.charAt(x) <= 'z') {
					keys.put(line.charAt(x), false);
				}

				maze[y][x] = line.charAt(x);

				// 출발지 찾아서 큐에 등록
				if ((y == 0 || x == 0 || y == h - 1 || x == w - 1) && (line.charAt(x) != '*')) {
					queue.add(new Coor(x, y));
				}
			}
		}

		// 가지고 있는 열쇠라면 true로 기록
		for (char a : br.readLine().toCharArray()) {
			keys.put(a, true);
		}
	}
}

class Coor {
	int x;
	int y;

	Coor(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
```


## BOJ6087 레이저 통신

### 🎈 해결방법 :
모든 좌표에 대해 가로로 레이저가 지나갈 때, 세로로 레이저가 지나갈 때 거울을 몇개 사용한 상태인지 누적 갯수를 저장해둠
현재 좌표 [r1,c1], 다음 좌표 [r2, c2]라고 생각하면
1. 위아래로 이동한다면 r2 = r1+1(가로로 진행하다가 세로로 꺾임), c1(세로로 계속해서 진행), r2 중 최소
2. 좌우로 이동한다면 c2 = c1+1(세로로 진행하다가 가로로 꺾임), r1(가로로 계속해서 진행), c2 중 최소
3. 값을 갱신하게 된다면 좌표를 큐에 추가해서 또 그 좌표부터 탐색

### 💬 코멘트 :
이번주는 코드가 다 너무 길어서 번역을 포기했습니다

### 📄 코드
```python
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
```

## BOJ1389 케빈 베이컨의 6단계 법칙

### 🎈 해결방법 :
1. bfs
   - 모든 노드(사람)에 대해서 검사해서 총합을 구하고 최소 비교하는 방식
2. dp(플로이드-워셜)
   - 모든 관계에 대해서 중간에 사람 하나씩 끼워넣고 최소값 구한 뒤 합의 최소 비교하는 방식

### 💬 코멘트 :
BFS로 풀다가 완전탐색 하기 싫어서 dp로 풀 방법 없나? 하고 고민을 한참 했는데요

그래프로 그려보고 dp 검색 해보니까 플로이드-워셜 알고리즘을 쓸 수 있을 것 같아서 써봤습니다

근데 저번에는 보니까 그냥 BFS 완탐으로 풀었길래 그 코드도 리팩토링해서 같이 첨부함

### 📄 코드 - bfs
```java
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ1389 {
	public static void main(String[] args) throws IOException {
		int N = readInt();
		int M =readInt();
		// 연결 정보 저장할 리스트
		List<Integer>[] relations = new LinkedList[N+1];
		Queue<int[]> queue = new LinkedList<>();
		for (int n = 1; n < N + 1; n++) {
			relations[n] = new LinkedList<>();
		}

		// 모든 연결된 노드를 기록
		for (int m = 0; m < M; m++) {
			int a = readInt();
			int b = readInt();

			relations[a].add(b);
			relations[b].add(a);

		}
		int min = M*M;
		int ans = 0;
		// 모든 출발 노드에 대해 단계 합 구하기
		for (int i=1; i<N+1; i++) {
			// 방문한 노드 기록
			List<Integer> visited = new LinkedList<>();
			int total = 0;
			queue.offer(new int[] {i,0});
			visited.add(i);
			// 모든 노드를 방문할 때까지 반복
			while (!queue.isEmpty()) {
				int[] next = queue.poll();
				// 단계를 총합에 추가
				total+=next[1];
				// 연결된 노드 중 방문 안 한 거 전부 큐에 추가
				for (int r: relations[next[0]]) {
					if (!visited.contains(r)) {
						// [노드, 단계]
						queue.offer(new int[] {r, next[1]+1});
						visited.add(r);
					}
				}
			}
			// 최소 갱신
			if (min>total) {
				min = total;
				ans = i;
			}
		}

		System.out.println(ans);
	}
	
	static int readInt() throws IOException {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		if (c == 13)
			System.in.read();
		return n;
	}

}

```

### 📄 코드 - 플로이드워셜
```java
import java.io.IOException;

public class BOJ1389 {
	public static void main(String[] args) throws IOException {
		int N = readInt();
		int M = readInt();
		// 최소값 저장할 배열
		int[][] dp = new int[N + 1][N + 1];

		// 일단 최대로 초기화
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < N + 1; j++) {
				dp[i][j] = i == j ? 0 : N * N;
			}
		}

		// 만약 노드끼리 연결되어 있다면 거리는 1
		for (int m = 0; m < M; m++) {
			int a = readInt();
			int b = readInt();
			dp[a][b] = 1;
			dp[b][a] = 1;
		}

		// 중간에 k 끼우고, i부터 j까지 갈 때 거리를 구함(직접 아는 사이가 아닌 경우 다른 사람을 거쳐가기 위함)
		// i~k + k~j
		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
					dp[j][i] = Math.min(dp[j][i], dp[j][k] + dp[k][i]);
				}
			}
		}
		int min = N * N;
		int answer = 0;
		// 모든 노드에 대한 거리의 합 비교
		for (int i = 1; i < N + 1; i++) {
			int sum = 0;
			for (int j = 1; j < N + 1; j++) {
				sum += dp[i][j];
			}
			if (min > sum) {
				answer = i;
				min = sum;
			}
		}

		System.out.println(answer);

	}

	static int readInt() throws IOException {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		if (c == 13)
			System.in.read();
		return n;
	}
}

```