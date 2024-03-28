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
