package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9251_LCS_td {
	static char[] c1;
	static char[] c2;
	static Integer[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		c1 = br.readLine().toCharArray();
		c2 = br.readLine().toCharArray();
		dp = new Integer[c1.length][c2.length];
		
		System.out.println(lcs(c1.length - 1, c2.length - 1));
		
	}
	
	static int lcs(int x, int y) {
		// index 범위 벗어나면 0 반환
		if (x == -1 || y == -1) {
			return 0;
		}
		// dp[x][y]가 null일 때만 실행
		if (dp[x][y] == null) {
			// 값이 같으면 대각선 왼쪽 위의 값 + 1
			if (c1[x] == c2[y]) {
				dp[x][y] = lcs(x - 1, y - 1) + 1;
			// 값이 다르면 왼쪽이나 위 중 큰 거 가져오기
			} else {
				dp[x][y] = Math.max(lcs(x - 1, y), lcs(x, y - 1));
			}
		}
		// dp[x][y] 반환
		return dp[x][y];
	}
}
