# Week9
## BOJ1753 최단경로
### 🎈 해결방법 :
<!-- 해결 방법 -->
1. 다익스트라 이용

### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->
1. 평범한 다익스트라인듯 합니다.
2. 원래 있던 코드 썼습니다.

### 📄 코드
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ1753 {
	static class Node implements Comparable<Node> {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}

	static final int INF = 987654321;
	static int V, E;
	static List<Node>[] adjList;
	static int[] dist;
	static int a;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		V = sc.nextInt();
		E = sc.nextInt();
		a = sc.nextInt();
		adjList = new ArrayList[V + 1];
		for (int i = 0; i < V + 1; i++) {
			adjList[i] = new ArrayList<>();
		} 

		dist = new int[V + 1];
		Arrays.fill(dist, INF);

		for (int i = 0; i < E; i++) {
			adjList[sc.nextInt()].add(new Node(sc.nextInt(), sc.nextInt()));
		}

		dijkstra(a);
		for (int i = 1; i < V + 1; i++) {
			if (dist[i] == INF) {
				System.out.println("INF");
				continue;
			}
			System.out.println(dist[i] - dist[a]);
		}
	}

	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V+1];

		dist[start] = a;

		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node curr = pq.poll();

			if (visited[curr.v])
				continue;
			visited[curr.v] = true;

			for (Node node : adjList[curr.v]) {
				if (!visited[node.v] && dist[node.v] > dist[curr.v] + node.w) {
					dist[node.v] = dist[curr.v] + node.w;
					pq.add(new Node(node.v, dist[node.v]));
				}
			}
		}

	}

}
```