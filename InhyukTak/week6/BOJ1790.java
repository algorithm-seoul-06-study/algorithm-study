package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1790_수이어쓰기2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int l = Integer.toString(N).length();
		
		check(N, k, l);
	}
	
	static void check(int N, int k, int l) {
		for (int i = 1; i <= l; i++) {
			int tmp = (int) (i * Math.pow(10, i-1) * 9);
			if (k > tmp) {
				k -= tmp;
			} else {
				int q = k / i;
				int r = k % i;
				tmp = (int) (Math.pow(10, i-1) + q);
				if ((N >= tmp - 1 && r == 0) || (N >= tmp && r != 0)) {
					String str = "";
					if (r == 0) {
						str = Integer.toString(tmp-1);
						System.out.println(str.charAt(str.length() - 1));
					} else {
						str = Integer.toString(tmp);
						System.out.println(str.charAt(r - 1));
					}
					return;
				}
			}
		}
		System.out.println(-1);
	}
}
