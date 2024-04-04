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
