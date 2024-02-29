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

        // 알파벳 배열 보드 저장
		board = new String[R];
        // 보드의 칸에 방문했었는지 기록할 배열
		visited = new boolean[R][C];
        // 방문했던 알파벳 기록할 카운트배열
		alphabets = new int[26];
		for (int r = 0; r < R; r++) {
			board[r] = fr.nextLine();
		}

		find(0, 0);
		System.out.println(answer);
	}

    // 재귀로 상하좌우 모든 칸을 순회
	static void find(int x, int y) {
        // 만약 칸을 벗어나거나, 방문했었거나, 중복 알파벳이라면
        // answer 갱신
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

        // 재귀이므로 값 돌려놓는 거 중요
		alphabets[board[y].charAt(x) - 'A']--;
		visited[y][x] = false;

	}

    // 보드 안에 있는 건지 검사
	static boolean check(int x, int y) {
		if (x < 0 || x > C - 1) {
			return false;
		}
		if (y < 0 || y > R - 1) {
			return false;
		}
		return true;
	}

    // 배열 합 구하는 함수
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