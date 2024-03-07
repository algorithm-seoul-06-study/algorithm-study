package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1992 {

	static int[][] arr;
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}

		QuadTree(0, 0, N);
		System.out.println(sb);

	}

	private static void QuadTree(int i, int j, int n) {

		if (isPossible(i, j, n)) {
			sb.append(arr[i][j]);
			return;
		}

		int size = n / 2;

		sb.append("(");

		QuadTree(i, j, size);
		QuadTree(i, j + size, size);
		QuadTree(i + size, j, size);
		QuadTree(i + size, j + size, size);

		sb.append(")");

	}

	private static boolean isPossible(int i, int j, int n) {

		int result = arr[i][j];

		for (int x = i; x < i + n; x++) {
			for (int y = j; y < j + n; y++) {

				if (result != arr[x][y]) {
					return false;
				}

			}
		}

		return true;
	}

}