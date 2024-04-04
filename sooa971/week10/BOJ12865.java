import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ12865 {
	static int n, k;
	static int[] weight, value;
	static int[][] dp;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] line = br.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		k = Integer.parseInt(line[1]);
		// 최대값은 k키로
		dp = new int[n + 1][k + 1];
		weight = new int[n + 1];
		value = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			String[] line2 = br.readLine().split(" ");
			weight[i] = Integer.parseInt(line2[0]);
			value[i] = Integer.parseInt(line2[1]);

		}

		// 첫번째 물건 부터 시작
		for (int i = 1; i <= n; i++) {
			for (int w = 1; w <= k; w++) {
				if (w < weight[i])
					dp[i][w] = dp[i - 1][w];
				else {
					dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-weight[i]]+value[i]);

				}

			}
		}
		System.out.println(dp[n][k]);

	}

}
