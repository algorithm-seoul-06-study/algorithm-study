import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1107 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		String str = null;
		if (M != 0) {
			str = br.readLine().replace(" ", "");			
		}
		
		int result = 0;
		if (N == 100) {
		} else if (Integer.toString(N).length() >= Math.abs(N - 100) || M == 10) {
			result = Math.abs(N - 100);
		} else if (M == 0) {
			result = Integer.toString(N).length();
		} else {
			int lResult = Integer.MAX_VALUE, hResult = Integer.MAX_VALUE;
			outer :
				for (int i = N; i <= 1000000; i++) {
					for (int j = 0; j < M; j++) {
						if (Integer.toString(i).contains(str.substring(j, j+1))) {
							break;
						}
						if (j == M - 1) {
							// System.out.println(i);
							hResult = Integer.toString(i).length() + (i - N);
							if (hResult > Math.abs(N - 100)) {
								hResult = Math.abs(N - 100);
							}
							break outer;
						}
					}
				}
			outer :
				for (int i = N; i >= 0; i--) {
					for (int j = 0; j < M; j++) {
						if (Integer.toString(i).contains(str.substring(j, j+1))) {
							break;
						}
						if (j == M - 1) {
							// System.out.println(i);
							lResult = Integer.toString(i).length() + (N - i);
							if (lResult > Math.abs(N - 100)) {
								lResult = Math.abs(N - 100);
							}
							break outer;
						}
					}
				}
			// System.out.println("l = " + lResult);
			// System.out.println("h = " + hResult);
			result = Math.min(lResult, hResult);
		}
		System.out.println(result);
	}
}
