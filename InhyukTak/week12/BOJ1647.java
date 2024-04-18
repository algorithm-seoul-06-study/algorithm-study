import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1647 {
	static class Node {
		int ed, w; // ed : 끝점, w : 유지비

		public Node(int ed, int w) {
			this.ed = ed;
			this.w = w;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 연결 리스트
		List<Node>[] adjList = new ArrayList[N+1];
		for (int i = 1; i < N+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int r = 0; r < M; r++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			// 무향 그래프
			adjList[A].add(new Node(B, C));
			adjList[B].add(new Node(A, C));
		}
		
		// 유지비 기준 오름차순 우선순위 큐
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.w - o2.w;
			}
		});
		// 방문처리 배열
		boolean[] visited = new boolean[N+1];
		
		int cnt = 1; // 방문처리 개수
		int sum = 0;
		int remove = 0;
		visited[1] = true; // 시작점 1 방문처리
		pq.addAll(adjList[1]); // 1을 시작점으로 하는 모든 노드 정보 pq에 넣기
		
		while (cnt < N) {
			Node node = pq.poll();
			// 이미 해당 정점이 방문한 점이라면 continue
			if (visited[node.ed]) {
				continue;
			}
			
			sum += node.w; // 해당 도로가 가지고 있는 유지비를 더함
			visited[node.ed] = true; // 방문처리
			cnt++; // 방문처리 수 +1
			if (remove < node.w) { // 마지막에 끊을 가장 큰 유지비를 업데이트
				remove = node.w;
			}
			
			// 끝점을 시작점으로 하는 모든 노드 정보 pq에 넣기
			pq.addAll(adjList[node.ed]);
		}
		
		System.out.println(sum - remove);
	}
}
