package codingtest_BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1018 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		char[][] arr = new char[m][n];
		for (int r = 0; r < m; r++) {
			String str = br.readLine();
			for (int c = 0; c < n; c++) {
				arr[r][c] = str.charAt(c);
			}
		}
		
		char[][] bChess = new char[8][8];
		char[][] wChess = new char[8][8];
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if ((r + c) % 2 == 0) {
					bChess[r][c] = 'B';
					wChess[r][c] = 'W';
				} else {
					bChess[r][c] = 'W';
					wChess[r][c] = 'B';
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		int bCnt = 0;
		int wCnt = 0;
		outer :
		for (int i = 0; i < m - 8 + 1; i++) {
			for (int j = 0; j < n - 8 + 1; j++) {
				for (int r = 0; r < 8; r++) {
					for (int c = 0; c < 8; c++) {
						if (arr[i+r][j+c] != bChess[r][c]) bCnt++;
						if (arr[i+r][j+c] != wChess[r][c]) wCnt++;
					}
				}
			if (min > Math.min(wCnt, bCnt)) {
				min = Math.min(wCnt, bCnt);
			}
			if (min == 0) {
				break outer;
			}
			bCnt = 0;
			wCnt = 0;
			}
		}
		System.out.println(min);
	}
}
