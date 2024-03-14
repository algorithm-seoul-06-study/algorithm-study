import java.io.IOException;

public class BOJ1389 {
	public static void main(String[] args) throws IOException {
		int N = readInt();
		int M = readInt();
		int[][] dp = new int[N + 1][N + 1];

		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < N + 1; j++) {
				dp[i][j] = i == j ? 0 : N * N;
			}
		}

		for (int m = 0; m < M; m++) {
			int a = readInt();
			int b = readInt();
			dp[a][b] = 1;
			dp[b][a] = 1;
		}
		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
					dp[j][i] = Math.min(dp[j][i], dp[j][k] + dp[k][i]);
				}
			}
		}
		int min = N * N;
		int answer = 0;
		for (int i = 1; i < N + 1; i++) {
			int sum = 0;
			for (int j = 1; j < N + 1; j++) {
				sum += dp[i][j];
			}
			if (min > sum) {
				answer = i;
				min = sum;
			}
		}

		System.out.println(answer);

	}

	static int readInt() throws IOException {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		if (c == 13)
			System.in.read();
		return n;
	}
}
