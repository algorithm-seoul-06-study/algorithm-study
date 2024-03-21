import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Info implements Comparable<Info>{
	Info(int next, int weight) {
		this.next=next;
		this.weight=weight;
	}
	int next;
	int weight;
	
	@Override
	public int compareTo(Info o) {
		return this.weight-o.weight;
	}
}
public class BOJ16227 {
	static int[] distance;
	public static void main(String[] args) {
		
		//인덱스가 정점이고, list<info>는 간선의 리스트
		List<List<Info>> list = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		
		int n= sc.nextInt();
		int k = sc.nextInt();
		distance = new int[n+2];
		distance[0] = 0;
		for(int i=1; i<n+2; i++) {
			distance[i]=Integer.MAX_VALUE;
		}
		
		for(int i=0; i<n+2; i++) {
			list.add(new LinkedList<>());
			list.get(i).add(new Info(i,0));
		}
		
		for(int i=0; i<k; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			list.get(i).add(new Info(b,c));
		}
		
		PriorityQueue<Info> pq = new PriorityQueue<>();
		
		pq.offer(list.get(0).get(0));
		
		while(!pq.isEmpty()) {
			int pq.poll()
		}
		
		
	}
}
