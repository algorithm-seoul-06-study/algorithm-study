import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2636 {
	static int r, c, cnt, cmp, time;
	static int[][] cheese;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] line = br.readLine().split(" ");
		r = Integer.parseInt(line[0]);
		c = Integer.parseInt(line[1]);

		cheese = new int[r][c];

		for (int i = 0; i < r; i++) {
			String[] line2 = br.readLine().split(" ");
			for (int j = 0; j < c; j++) {
				cheese[i][j] = Integer.parseInt(line2[j]);
				if(cheese[i][j]==0) visited[i][j]=true;
			}
		}

		cnt = 1;
		
		while (cnt != 0) {
			cnt = 0;
			count();
			if (cnt == 0) {
				System.out.println(time);
				System.out.println(cmp);
			} else {
				melt();
				cmp = cnt;
				time++;
			}
			//확인용
			for(int[] arr : cheese) {
				System.out.println(Arrays.toString(arr));
			}
		}

	}

	
	//0이면 사방탐색
		//1이고 방문하지 않았으면 cnt++
	
	//1이고 방문 했으면 0으로 바꾸기 break
	//1이고 방문 안했으면 continue
	
	static void count() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (cheese[i][j] == 1) {
					cnt++;

				}
			}
		}
	}

	
	static void search(int sr, int sc) {
		//사방탐색 후 1을 만나면 
		// cnt++
		// 0으로 바꾸기
		
	}
	
	static void melt() {

		// 왼쪽에서
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (cheese[i][j] == 1 && visited[i][j]) {
					break;
					}
				else {
					
				}
			}
		}
		// 아래에서
		for (int j = 0; j < c; j++) {
			for (int i = r - 1; i >= 0; i--) {
				if (cheese[i][j] == 1) {
					cheese[i][j] = 0;
					while (i >= 0 && cheese[i-1][j] == 1) {
						i--;
					}

				}
			}
		}
		// 위에서
		for (int j = 0; j < c; j++) {
			for (int i = 0; i < r; i++) {
				if (cheese[i][j] == 1) {
					cheese[i][j] = 0;
					while (i < r && cheese[i+1][j] == 1) {
						i++;
					}
				}
			}
		}
		// 오른쪽에서
		for (int i = 0; i < r; i++) {
			for (int j = c - 1; j >= 0; j--) {
				if (cheese[i][j] == 1) {
					cheese[i][j] = 0;
					while (j >= 0 && cheese[i][j-1] == 1) {
						j--;
					}
				}
			}
		}

	}

}