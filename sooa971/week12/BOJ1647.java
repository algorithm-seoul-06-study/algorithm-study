import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1647 {
	static int[] p;

	static class Edge implements Comparable<Edge> {
		int a, b, w;

		public Edge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}

	static Edge[] edges;

	public static void main(String[] args) throws IOException {
		// 크루스칼

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");

		int V = Integer.parseInt(line[0]);
		int E = Integer.parseInt(line[1]);

		p = new int[V + 1];
		edges = new Edge[E];

		for (int i = 0; i < E; i++) {
			String[] line2 = br.readLine().split(" ");
			edges[i] = new Edge(Integer.parseInt(line2[0]), Integer.parseInt(line2[1]), Integer.parseInt(line2[2]));
		}

		Arrays.sort(edges);

		for (int i = 1; i < V + 1; i++) {
			p[i] = i;
		}

		int weights = 0;
		int cnt = 0;
		for (int i = 0; i < E; i++) {
			int x = findset(edges[i].a);
			int y = findset(edges[i].b);

			if (x != y) {
				union(x, y);
				weights += edges[i].w;
				cnt++;
			}
			if (cnt == V - 1) {
				weights -= edges[i].w;
				break;
			}
		}

		System.out.println(weights);

	}

	static void union(int x, int y) {
		p[y] = x;
	}

	static int findset(int x) {
		if (p[x] != x) {
			p[x] = findset(p[x]);
		}
		return p[x];
	}

	static void makeset(int x) {
		p[x] = x;
	}
}