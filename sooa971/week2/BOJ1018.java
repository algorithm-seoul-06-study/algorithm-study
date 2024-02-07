import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1018 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		

		//체스판 만들기
		String[][] chess1 = new String[8][8];
		String[][] chess2 = new String[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i % 2 == 0) {
					if (j % 2 == 0) {
						chess1[i][j] = "B";
						chess2[i][7 - j] = "B";
					} else {
						chess1[i][j] = "W";
						chess2[i][7 - j] = "W";
					}
				} else {
					if (j % 2 == 1) {
						chess1[i][j] = "B";
						chess2[i][7 - j] = "B";
					} else {
						chess1[i][j] = "W";
						chess2[i][7 - j] = "W";
					}
				}
			}
		}
		
		//보드 받기
		String[][] board = new String[n][m];
		
		for(int i=0; i<n; i++) {
			board[i]=br.readLine().split("");
		}
		

		int min = 65;

		
		for(int i=0; i<n-7; i++) {
			for(int j=0; j<m-7; j++) {
				
				int cnt1 = 0;
				int cnt2 =0;
				int mid =0;
				for(int q =0; q<8; q++) {
					for(int k =0; k<8; k++) {
						if (!board[i+q][j+k].equals(chess1[q][k])) {
							cnt1 ++;
						}
						if (!board[i+q][j+k].equals(chess2[q][k])) {
							cnt2 ++;
						}
						
					}
					
				}
				mid = Math.min(cnt1, cnt2);
				min = Math.min(mid, min);
			}
		}
		System.out.println(min);

	}
}