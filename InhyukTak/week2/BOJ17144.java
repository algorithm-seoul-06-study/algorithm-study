import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17144 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[][][] arr = new int[T+1][R][C];
		int[] ac = new int[2];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				arr[0][r][c] = Integer.parseInt(st.nextToken());
				if (arr[0][r][c] == -1) {
					if (ac[0] == 0) {
						ac[0] = r;
					} else {
						ac[1] = r;
					}
				}
			}
		}
		
		// 상 하 좌 우
		int[] dr1 = {-1, 1, 0, 0};
		int[] dc1 = {0, 0, -1, 1};
		
		// 상 우 하 좌
		int[] dr2 = {-1, 0, 1, 0};
		int[] dc2 = {0, 1, 0, -1};
		
		// 하 우 상 좌
		int[] dr3 = {1, 0, -1, 0};
		int[] dc3 = {0, 1, 0, -1};
		
		for (int i = 0; i < T; i++) {
			// 먼지 확산
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					arr[i+1][r][c] += arr[i][r][c];
					if (arr[i][r][c] >= 5) {
						for (int j = 0; j < dc1.length; j++) {
							if (r + dr1[j] >= 0 && r + dr1[j] < R && c + dc1[j] >= 0 && c + dc1[j] < C && arr[i][r+dr1[j]][c+dc1[j]] != -1) {
								arr[i+1][r+dr1[j]][c+dc1[j]] += arr[i][r][c] / 5;
								arr[i+1][r][c] -= arr[i][r][c] / 5;
							}
						}
					}
				}
			}
			
			// 공기청정기 작동
			// 윗부분
			int r = ac[0] - 1;
			int c = 0;
			int j = 0;
			// arr[i+1][--r][c] = 0;
			while (arr[i+1][r][c] != -1) {
				if (r + dr2[j] < 0 || r + dr2[j] > ac[0] || c + dc2[j] < 0 || c + dc2[j] >= C) {
					j = (j + 1) % 4;
				}
				arr[i+1][r][c] = arr[i+1][r+dr2[j]][c+dc2[j]];
				r += dr2[j];
				c += dc2[j];
				
				if (j == 3 && arr[i+1][r+dr2[j]][c+dc2[j]] == -1) {
					arr[i+1][r][c] = 0;
					break;
				}
			}
			
			// 아랫부분
			r = ac[1] + 1;
			c = 0;
			j = 0;
			while (arr[i+1][r][c] != -1) {
				if (r + dr3[j] < ac[1] || r + dr3[j] >= R || c + dc3[j] < 0 || c + dc3[j] >= C) {
					j = (j + 1) % 4;
				}
				arr[i+1][r][c] = arr[i+1][r+dr3[j]][c+dc3[j]];
				r += dr3[j];
				c += dc3[j];
				
				if (j == 3 && arr[i+1][r+dr3[j]][c+dc3[j]] == -1) {
					arr[i+1][r][c] = 0;
					break;
				}	
			}
		}
		
		int sum = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				sum += arr[T][r][c];
			}
		}
		System.out.println(sum + 2);
	}
}
