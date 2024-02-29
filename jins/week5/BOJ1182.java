package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1182 {

	static int N, K;
	static int[] arr;
	static int count;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int index = 1; index <= T; index++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			count = 0;

			arr = new int[N];

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			countArr(0, 0);
			System.out.printf("#%d %d\n", index, count);
		}

	}

	public static void countArr(int index, int sum) {

		if (index == N) {
			if (sum == K) {
				count++;
			}
			return;
		}

		countArr(index + 1, sum + arr[index]);
		countArr(index + 1, sum);

	}

}
