import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Map {
	static int mr, mc;

	Map(int mr, int mc) {
		this.mr = mr;
		this.mc = mc;
	}
}

public class BOJ9328 {
	static int r, c;
	static char[][] map;
	static Queue<Map> queue = new LinkedList<>();
	static List<Character> key = new ArrayList<>();
	static int cnt;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			cnt = 0;
			String[] line = br.readLine().split(" ");
			r = Integer.parseInt(line[0]);
			c = Integer.parseInt(line[1]);

			map = new char[r][c];
			visited = new boolean[r][c];

			for (int i = 0; i < r; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < c; j++) {
					if (map[i][j] == '*') {
						visited[i][j] = true;
					} else if (i == 0 || i == r - 1 || j == 0 || j == c - 1) {
						queue.offer(new Map(i, j));
					}
				}
			}
			System.out.println(queue.size());

			char[] carr = br.readLine().toCharArray();
			if (carr[0] != 0) {
				for (int i = 0; i < carr.length; i++) {
					key.add(carr[i]);
				}
			}
			while (!queue.isEmpty()) {
				Map mp = queue.poll();
				bfs(mp.mr, mp.mc);
			}

			System.out.println(cnt);
			for (char[] carra : map) {
				System.out.println(Arrays.toString(carra));
			}
		}
	}

	public static void bfs(int br, int bc) {
		// 1. 본인visited 처리를 한다
		// 2. 상하좌우를 queue에 넣는다.
		// 3. 가져온 좌표의 내용에 따라 함수를 실행한다.
		
		if (search(br, bc)) {
			for (int i = 0; i < 4; i++) {
				if (br + dr[i] >= 0 && br + dr[i] < r && bc + dc[i] >= 0 && bc + dc[i] < c
						&& !visited[br + dr[i]][bc + dc[i]]) {
					queue.offer(new Map(br + dr[i], bc + dc[i]));
				}
			}
		}

		while (!queue.isEmpty()) {
			Map m = queue.poll();
			if (!visited[m.mr][m.mc])
				bfs(m.mr, m.mc);

		}
	}

	public static boolean search(int sr, int sc) {
		char c = map[sr][sc];

		if (c == '*') {
			visited[sr][sc] = true;
			return false;
		} else if (c == '.') {
//			visited[sr][sc] = true;
			return true;
		} else if (c - 'a' >= 0 && c - 'a' <= 26) { // 소문자
			key.add(c);
			map[sr][sc] = '.';
//			visited[sr][sc] = true;
			return true;
		} else if (c - 'A' >= 0 && c - 'A' <= 26) {
			for (int i = 0; i < key.size(); i++) {
				if (c + 32 == key.get(i)) {
					// visit처리
					map[sr][sc] = '.';
//					visited[sr][sc] = true;
					return true;
				}
			}
//			queue.offer(new Map(sr, sc));
			return false;
		} else {
			cnt++;
			map[sr][sc] = '.';
			visited[sr][sc] = true;
			return true;
		}

	}

	// 탐색하는 함수
	// 1. '*' return
	// 2. '.' visited 처리 후 bfs(큐삽입)
	// 3. is 소문자 -> keylist에 add 후 .로 바꾸고 visited처리 후 bfs(큐삽입)
	// 4. is capital
	// -> 열쇠 있으면 .로바꾸고 visited 처리 후 큐삽입 후 bfs,
	// -> 없으면(열쇠가 비었어도) 큐삽입후 리턴
	// 5. '$' ->

	// BFS

}
