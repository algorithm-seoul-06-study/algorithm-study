import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int minChange = 64;
		char[][] board = new char[n][m];
		for (int i = 0; i < n; i++) {
			String nextLine = bf.readLine();
			for (int j = 0; j < m; j++) {
				board[i][j] = nextLine.charAt(j);
			}
		}
		for (int y = 0; y <= n - 8; y++) {
			for (int x = 0; x <= m - 8; x++) {
				char color = board[y][x];
				int change = 0;
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						change += (color == board[y + i][x + j] ? 0 : 1);
						color = (color == 'B' ? 'W' : 'B');
					}
					color = (color == 'B' ? 'W' : 'B');
				}
				if (change > 32) {
					change = 64 - change;
				}
				minChange = Math.min(minChange, change);

			}
		}
		System.out.println(minChange);

	}
}