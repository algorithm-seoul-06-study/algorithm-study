import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2636 {
	static int n;
	static int k;
	static boolean[][] arr;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]); // 행
		k = Integer.parseInt(str[1]); // 열
		arr = new boolean[n][k]; // 비교할 2차원 배열
		visit = new boolean[n][k]; // 탐색할 2차원 배열
		int cnt=0; // 얼마나 걸렸는지
		
		for (int i = 0; i < n; i++) { // 비교할 2차원 배열의 반전 = 탐색할 2차원 배열 
			// As 탐색할 곳은 치즈가 아닌 곳, 비교할 곳은 치즈인 곳 (사방탐색)
			String[] strarr = br.readLine().split(" ");
			for (int j = 0; j < k; j++) {
				if (strarr[j].equals("1")) {
					arr[i][j] = true;
					visit[i][j] = false;
				} else {
					visit[i][j] = true;
				}

			}
		}
		
		// 한시간 후 없어질 것을 고려하여 countingCheese()로 초기화
		int result=countingCheese();
		while(true) {
			findBoundary(0, 0); // 0,0은 무조건 false인 부분 (치즈 기준)
			cnt++; // visit 함수 update -> 탐색 과정에서 다 바뀌었기 때문
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < k; j++) {
					if (arr[i][j]) {
						visit[i][j]=false;
					} else {
						visit[i][j]=true;

					}
				}
			}
			
			if(countingCheese()==0) { // cheese 숫자 세기
				break;
			} else {
				result=countingCheese();
			}
			
		}
		System.out.println(cnt);
		System.out.println(result);
	}

	static void findBoundary(int startR, int startC) { // 시작하는 좌표 startR, startC (사실상 0,0임)
		Queue<Integer[]> queue = new LinkedList<>();
		queue.add(new Integer[] { startR, startC });

		while (!queue.isEmpty()) { // 탐색 진행
			Integer[] current = queue.poll();
			int r = current[0];
			int c = current[1];
			if (!(0 <= r && r < n && 0 <= c && c < k) || !visit[r][c]) { // 배열 벗어난 곳이나, 이미 visit한 곳이면 가지 않음
				continue;
			}
			
			// 사방탐색을 하여 치즈인 부분을 false로 만듦 -> 겉부분만 바뀜 Island 내부는 탐색 진행 x
			if (0 < r && arr[r - 1][c]) {
				arr[r - 1][c] = false;
			}
			if (r < n - 1 && arr[r + 1][c]) {
				arr[r + 1][c] = false;
			}
			if (0 < c && arr[r][c - 1]) {
				arr[r][c - 1] = false;
			}
			if (c < k - 1 && arr[r][c + 1]) {
				arr[r][c + 1] = false;
			}
			
			// 현 좌표 false로 바꿈
			visit[r][c] = false;

			// 상화좌우 탐색
			queue.add(new Integer[] { r + 1, c });
			queue.add(new Integer[] { r - 1, c });
			queue.add(new Integer[] { r, c + 1 });
			queue.add(new Integer[] { r, c - 1 });

		}
	}

	static int countingCheese() { // Cheese 개수 세는 Method
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < k; j++) {
				if (arr[i][j]) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}