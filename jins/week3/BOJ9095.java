package week3;

import java.util.Scanner;

public class BOJ9095 {
	public static void main(String args[]) {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {

			int n = sc.nextInt();

			int dp[] = new int[Math.max(4, n + 1)];

			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;

			for (int j = 4; j <= n; j++) {
				dp[j] = dp[j - 1] + dp[j - 2] + dp[j - 3];
			}

			System.out.println(dp[n]);
		}

		sc.close();

	}

}