# Week9
## BOJ1753 최단경로
### 🎈 해결방법 :
1. 가중치 기준 오름차순 우선순위 큐 사용
2. results[n.v] > results[nowV] + n.w 로 최솟값 갱신

### 💬 코멘트 :
기본적인 다익스트라 학습 문제

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1753 {
	static class Node {
		int v, w;
		
		public Node() {
			
		}

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", w=" + w + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수
		int K = Integer.parseInt(br.readLine()); // 시작 정점 번호
		// 연결 리스트를 활용한 그래프 표현
		List<Node>[] nodes = new ArrayList[V+1];
		for (int i = 0; i <= V; i++) {
			nodes[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			nodes[u].add(new Node(v, w));
		}
		
		boolean[] visited = new boolean[V+1];
		int[] results = new int[V+1];
		// 최대값으로 초기화
		for (int i = 1; i < results.length; i++) {
			results[i] = Integer.MAX_VALUE;
		}
		// 가중치 기준 오름차순의 우선순위 큐
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			public int compare(Node o1, Node o2) {
				return o1.w - o2.w;
			};
		});
		// 시작 노드와 가중치 큐에 넣기
		pq.offer(new Node(K, 0));
		results[pq.peek().v] = pq.peek().w;
		while (!pq.isEmpty()) {
			// 큐에 있는거 빼고
			Node node = pq.poll();
			int nowV = node.v;
			// 방문하지 않은 노드면
			if (visited[nowV] == false) {
				// 방문처리 후
				visited[nowV] = true;
				// 가중치 대소비교 후 작은거 적용
				for (Node n : nodes[nowV]) {
					if (results[n.v] > results[nowV] + n.w) {
						results[n.v] = results[nowV] + n.w;
					}
					// 연결 노드 큐에 넣기
					pq.offer(new Node(n.v, results[n.v]));
				}				
			}
		}
		
		for (int i = 1; i < results.length; i++) {
			if (results[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			} else {
				System.out.println(results[i]);
			}
		}
	}
}

```

## BOJ17281 ⚾
### 🎈 해결방법 :
1. 순열을 통해 타순 확정
2. 타순 확정 후 경기 진행
3. 타자의 타격 결과에 따라 이닝 진행
4. 경기 끝날 때마다 최고점 갱신

### 💬 코멘트 :
두산아 잘 좀 해봐라

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17281 {
	static int I; // 이닝 수
	static int[][] hits; // 타자들 타석 결과 입력 배열
	static boolean[] check; // 순열 만들 때 방문체크
	static int[] lineup; // 타순
	static int batter; // 현재 타자
	static int score; // 점수
	static int maxScore; // 최대 점수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		I = Integer.parseInt(st.nextToken());
		hits = new int[I][9];
		for (int r = 0; r < hits.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < hits[0].length; c++) {
				// 첫번째로 입력받는 타자를 입력 받을 때부터 4번 타순에 고정
				if (c == 0) {
					hits[r][3] = Integer.parseInt(st.nextToken());										
				} else if (c == 3) {
					hits[r][0] = Integer.parseInt(st.nextToken());					
				} else {
					hits[r][c] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		check = new boolean[9];
		// 4번 타자 방문처리
		check[3] = true;
		lineup = new int[9];
		// 4번 타자 고정
		lineup[3] = 3;
		// 4번 타자 확정 지어서 cnt 2부터 시작
		lineup(2, 0);
		
		System.out.println(maxScore);
	}
	
	public static void play(int i) {
		boolean[] base = new boolean[4]; // 베이스 배열
		int out = 0; // 아웃카운트 0으로 초기화
		while (out < 3) { // 3아웃 전까지 반복
			int hit = hits[i-1][lineup[batter]]; // 현재 타순에 맞는 타자의 타격 정보
			if (hit >= 1) { // 타자가 안타를 쳤다면
				base[0] = true; // 홈플레이트 방문처리
				for (int b = base.length - 1; b >= 0; b--) { // 3루 -> 2루 -> 1루 -> 타자 순으로 베이스 이동
					if (base[b] == true) { // 베이스에 주자가 존재하면
						base[b] = false; // 해당 베이스를 비우고 주자 이동
						if (b + hit < 4) {
							base[b+hit] = true; // 이동한 베이스 방문처리
						} else {
							score++; // 홈 도착하면 점수 +1
						}
					}
				}
			} else {
				out++; // 타자가 아웃 당하면 아웃카운트 +1
			}
			batter = (batter + 1) % 9; // 다음 타자
		}

		if (i < I) { // 3아웃 당하면 다음 이닝으로
			play(i + 1);			
		} else { // 이닝 모두 종료돼서 게임 끝나면
			if (maxScore < score) {
				maxScore = score;
			}
		}
	}

	public static void lineup(int cnt, int idx) {
		for (int i = 0; i < 9; i++) {
			// 방문처리 안되어있는 타자부터 앞타순에 배정
			if (check[i] == false) {
				lineup[idx] = i;
				check[i] = true;
				if (cnt < 9) {
					// 타순 확정짓기 전에는 lineup() 메서드 반복
					if (idx == 2) {
						lineup(cnt + 1, idx + 2);					
					} else {
						lineup(cnt + 1, idx + 1);											
					}
				} else {
					// 9번까지 타순 확정지으면 점수, 타자 초기화 후 play() 메서드 실행
					score = 0;
					batter = 0;
					play(1);
				}
				check[i] = false;
			}
		}
	}
}
```