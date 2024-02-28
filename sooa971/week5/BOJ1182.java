import java.util.Arrays;
import java.util.Scanner;

public class BOJ1182 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int s = sc.nextInt();

		int[] arr = new int[n];
		int[] nArr = new int[1 << n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		for (int i = 1; i < (1 << n); i++) {
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) > 0) {
					nArr[i-1] += arr[j];
				}

			}
		}


		int cnt = 0;
		for (int i = 0; i < (1 << n)-1; i++) {
			if (nArr[i] == s) {
				cnt++;
			}
		}

		System.out.println(cnt);
	}
}
