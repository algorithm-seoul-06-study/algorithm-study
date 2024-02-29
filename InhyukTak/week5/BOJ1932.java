package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1932_정수삼각형 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][n];
		int sumMax = -1;
		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				// 위에서 2번째 줄부터
				if (i >= 1) {
					// 왼쪽 변에 있는 요소는 바로 위에 것만 받음
					if (j == 0) {
						arr[i][j] += arr[i-1][j];
					// 오른쪽 변에 있는 요소는 바로 위에 것만 받음
					} else if (j == i) {
						arr[i][j] += arr[i-1][j-1];
					// 가운데 있는 요소는 왼쪽 부모, 오른쪽 부모 중 큰 값 받음
					} else {
						arr[i][j] += Math.max(arr[i-1][j], arr[i-1][j-1]);
					}
					// 맨아래 줄에 도착했을 때 sumMax < arr[i][j] 이면 대입
					if (i == arr.length - 1 && sumMax < arr[i][j]) {
						sumMax = arr[i][j];
					}
				}
			}
		}
		System.out.println(sumMax);
	}
}