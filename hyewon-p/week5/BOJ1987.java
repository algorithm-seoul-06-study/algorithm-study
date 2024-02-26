import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1987 {
	static String[] board;
	static boolean[][] visited;
	static int[] alphabets;
	static int R, C;
	static int answer;

	public static void main(String[] args) throws IOException {
		FastReader fr = new FastReader();
		R = fr.nextInt();
		C = fr.nextInt();
		board = new String[R];
		visited = new boolean[R][C];
		alphabets = new int[26];
		for (int r = 0; r < R; r++) {
			board[r] = fr.nextLine();
		}
		find(0, 0);
		System.out.println(answer);
	}

	static void find(int x, int y) {
		if (!check(x, y) || visited[y][x] || alphabets[board[y].charAt(x) - 'A'] > 0) {
			answer = Math.max(sum(alphabets), answer);
			return;
		}
		visited[y][x] = true;
		alphabets[board[y].charAt(x) - 'A']++;
		find(x + 1, y);
		find(x, y + 1);
		find(x - 1, y);
		find(x, y - 1);
		alphabets[board[y].charAt(x) - 'A']--;
		visited[y][x] = false;

	}

	static boolean check(int x, int y) {
		if (x < 0 || x > C - 1) {
			return false;
		}
		if (y < 0 || y > R - 1) {
			return false;
		}
		return true;
	}

	static int sum(int[] list) {
		int total = 0;
		for (int l : list) {
			total += l;
		}
		return total;
	}
}

class FastReader {
	BufferedReader br;
	StringTokenizer st;

	public FastReader() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	String nextLine() {
		try {
			return br.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	int nextInt() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return Integer.parseInt(st.nextToken());
	}

}