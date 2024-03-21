import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2636 {
	static int[][] map;
	static Stack<Integer> cheese;
	static boolean flag;
	static int removeCnt;
	static Queue<Integer> rq;
	static Queue<Integer> cq;
	static int sr;
	static int sc;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		cheese = new Stack<>();
		int cheeseCnt = 0;
		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < map[0].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					cheeseCnt++;
				}
			}
		}
		cheese.push(cheeseCnt);
		rq = new LinkedList<>();
		cq = new LinkedList<>();
		while (cheese.peek() > 0) {
			removeCnt = 0;
			for (int r = 1; r < map.length - 1; r++) {
				for (int c = 1; c < map[0].length - 1; c++) {
					if (map[r][c] == 1) {
						flag = false;
						sr = r;
						sc = c;
						findSide(r+1, c);
						if (flag) continue;
						findSide(r-1, c);
						if (flag) continue;
						findSide(r, c+1);
						if (flag) continue;
						findSide(r, c-1);
						if (flag) continue;
					}
				}
			}
			cheese.push(cheese.peek() - removeCnt);
			while (!rq.isEmpty()) {
				map[rq.poll()][cq.poll()] = 0;
			}
		}
		System.out.println(cheese.size() - 1);
		cheese.pop();
		System.out.println(cheese.peek());
	}

	private static void findSide(int r, int c) {
		if (map[r][c] == 0) {
			if (r == 0 || r == map.length - 1 || c == 0 || c == map[0].length - 1) {
				flag = true;
				removeCnt++;
				rq.offer(sr);
				cq.offer(sc);
				return;
			}
			map[r][c] = 2;
			findSide(r+1, c);
			map[r][c] = 0;
			if (flag) return;
			map[r][c] = 2;
			findSide(r-1, c);
			map[r][c] = 0;
			if (flag) return;
			map[r][c] = 2;
			findSide(r, c+1);
			map[r][c] = 0;
			if (flag) return;
			map[r][c] = 2;
			findSide(r, c-1);
			map[r][c] = 0;
			if (flag) return;
		}
		
	}
}
