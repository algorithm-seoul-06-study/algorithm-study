# Week9

## BOJ17281 ⚾

### 🎈 해결방법 :
조합마다 가능한 점수를 구하는 구현 문제

### 💬 코멘트 :
LG 트윈스 파이팅

### 📄 코드
```java
import java.io.IOException;

public class BOJ17281 {
	static int[] lineup = new int[9];;
	static int maxScore = 0;
	static boolean[] fixed = new boolean[9];
	static int[][] hits;

	public static void main(String[] args) throws IOException {
		int N = readInt();
		hits = new int[N][9];
		// 선수1 4번에 고정
		lineup[3] = 0;
		fixed[0] = true;
		// 입력 받기
		for (int n = 0; n < N; n++) {
			for (int i = 0; i < 9; i++) {
				hits[n][i] = readInt();
			}
		}
		getComb(0);
		System.out.println(maxScore);
	}

	static void getComb(int num) {
		// 9번까지 라인업 완성했으면 스코어 구해보기
		if (num == 9) {
			int score = play();
			maxScore = Math.max(score, maxScore);
			return;
		}
		// 4번은 고정이니까 넘어가셈
		if (num == 3) {
			getComb(num + 1);
			return;
		}
		// 선수 2부터 9까지 모든 타순에 넣어보면서 조합 찾기
		for (int i = 1; i < 9; i++) {
			if (!fixed[i]) {
				lineup[num] = i;
				fixed[i] = true;
				getComb(num + 1);
				lineup[num] = 0;
				fixed[i] = false;
			}

		}

	}

	static int play() {
		int score = 0;
		int currHitter = 0;
		boolean[] bases;

		for (int inning = 0; inning < hits.length; inning++) {
			bases = new boolean[3];
			int outCount = 0;
			while (outCount < 3) {
				int currPlayer = lineup[currHitter];
				currHitter = (currHitter + 1) % 9;
				int hit = hits[inning][currPlayer];
				switch (hit) {
				case 0:
					outCount++;
					break;
				case 4:
					score+=homerun(bases);
					break;
				default:
					// 안타인 경우 주자 옮기기
					for (int i = 2; i >= 0; i--) {
						if (bases[i]) {
							// 홈에 들어오면 점수+
							if (i + hit >= 3) {
								score++;
							// 아니면 베이스 이동
							} else
								bases[i + hit] = true;
							// 원래 있던 베이스 지우기
							bases[i] = false;
						}
					}
					// 타자 주자 기록
					bases[hit - 1] = true;
				}
				
			}
		}
		return score;
	}

	static int homerun(boolean[] bases) {
		int score = 0;
		// 베이스 검사 -> 주자 불러들임
		for (int i = 0; i < 3; i++) {
			if (bases[i]) {
				score++;
				bases[i] = false;
			}
		}
		// 본인까지 홈
		return ++score;
	}

	public static int readInt() throws IOException {
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

## BOJ1753 최단경로

### 🎈 해결방법 :

### 💬 코멘트 :
평범한 다익스트라인데 우선순위큐 안 쓰면 시간초과 뜸

### 📄 코드
```java
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ1753 {
	static class Node {
		int v, dis;

		Node(int v, int dis) {
			this.v = v;
			this.dis = dis;
		}
	}

	public static void main(String[] args) throws IOException {
		int V = readInt(), E = readInt();
		int K = readInt();
		List<Node>[] conn = new LinkedList[V + 1];
		for (int i = 0; i < V + 1; i++) {
			conn[i] = new LinkedList<>();
		}
		int[] distance = new int[V + 1];
		Arrays.fill(distance, -1);
		
		// 거리 기준 우선순위큐
		PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> a.dis - b.dis);
		queue.add(new Node(K, 0));

		for (int e = 0; e < E; e++) {
			int u = readInt(), v = readInt(), w = readInt();
			conn[u].add(new Node(v, w));
		}

		while (!queue.isEmpty()) {
			// 출발 노드
			Node start = queue.poll();
			// 이미 갱신했으면 패스
			if (distance[start.v] >= 0)
				continue;

			// 최소 거리 갱신
			distance[start.v] = start.dis;
			// 연결된 노드들 거리 추가해서 큐에 추가
			for (Node c : conn[start.v]) {
				c.dis += distance[start.v];
				queue.add(c);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < V + 1; i++) {
			sb.append((distance[i] < 0 ? "INF" : distance[i]) + "\n");
		}
		System.out.println(sb);
	}

	public static int readInt() throws IOException {
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

## SOF7649 효도여행

### 🎈 해결방법 :

### 💬 코멘트 :
LCS를 마지막에 깡으로 돌리면 시간초과 나서 매열/행마다 값을 저장하는 게 신박했다고 생각합니다

LCS 어떻게 푸는 건지 몰랐으면 못 풀었을듯

### 📄 코드
```python
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

```





