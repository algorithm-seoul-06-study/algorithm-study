import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ10253 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			long a = Integer.parseInt(st.nextToken());
			long b = Integer.parseInt(st.nextToken());
			
			long d = b / a + 1; // 최소 d값
			long result = henry(a, b, d);
			System.out.println(result);
		}
	}
	
	public static long henry(long a, long b, long d) {
		while (true) {
			if (a == 1) { // a가 1이 되면
				return b; // 그 때의 b가 마지막 d
			}
			long G = getG(b, d); // b, d의 최대공약수
			a = a * d / G - b / G; // a 계산 결과
			b = b * d / G; // b 계산 결과
			G = getG(b, a); // b, a의 최대공약수
			a /= G; // 기약분수로 갱신
			b /= G; // 기약분수로 갱신
			d = b / a + 1; // 최소 d값
		}
	}
	
	public static long getG(long b, long d) { // 유클리드 호제법으로 최대공약수 찾기
		long mod = b % d;
		while (mod > 0) {
			b = d;
			d = mod;
			mod = b % d;
		}
		return d;
	}
}
