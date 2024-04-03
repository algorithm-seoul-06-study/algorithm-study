import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ1753 {
	static class Edge implements Comparable<Edge>	{
		int v,w;

		public Edge(int v, int w) { 
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w-o.w;
		}
	}



	private static boolean[] visited;
	
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		int st = sc.nextInt();
		
		//거리를 저장할 배열
		int[] dist = new int[V+1];
		dist[st] =0;
		
		for(int i=0; i<V+1; i++) {
			if (i!= st) dist[i]=Integer.MAX_VALUE;
		}
		
		//인접리스트
		List<Edge>[] adjlist = new LinkedList[V+1];
		visited= new boolean[V+1];
		
		for(int i=1; i<V+1; i++) {
			adjlist[i]=new LinkedList<>();
		}
		
		for(int i=0; i<E; i++) {
			adjlist[sc.nextInt()].add(new Edge(sc.nextInt(),sc.nextInt()));
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		pq.add(new Edge(st,0));
		
		while(!pq.isEmpty()) {
			//pq에서 최단 경로 뽑기
			Edge curr = pq.poll();
			
			if(visited[curr.v])  continue;
			visited[curr.v]=true;
			
			for(Edge ce : adjlist[curr.v]) {
				if(!visited[ce.v]&&dist[ce.v] > dist[curr.v] + ce.w) {
					dist[ce.v]=dist[curr.v] +ce.w;
					pq.add(new Edge(ce.v,dist[ce.v]));
				}
			}
		}
		
		for(int i =1; i<V+1; i++) {
			if(dist[i]!= Integer.MAX_VALUE)	System.out.println(dist[i]);
			else System.out.println("INF");
		}
		
	}
}
