package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9251_LCS_bu {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] c1 = br.readLine().toCharArray();
		char[] c2 = br.readLine().toCharArray();
		int[][] dp = new int[c1.length+1][c2.length+1];
		
		for (int i = 1; i <= c1.length; i++) {
			for (int j = 1; j <= c2.length; j++) {
				// 값이 같으면 대각선 왼쪽 위의 값 + 1
				if (c1[i-1] == c2[j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
				// 값이 다르면 왼쪽이나 위 중 큰 거 가져오기
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		System.out.println(dp[c1.length][c2.length]);
		
	}
}
