# Week10
## BOJ12865 평범한 배낭
### 🎈 해결방법 :
1. 물품 개수와 가방 허용 무게에 관한 정보를 통해 가중치 저장
2. 점화식 찾아서 DP 구현

### 💬 코멘트 :
오랜만에 DP 푸니까 어려웠다

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ12865 {
	static int[][] bags; // 가방 입력값 배열 (무게, 가치)
	static int[][] dp; // dp 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 물품의 수
		int K = Integer.parseInt(st.nextToken()); // 최대 무게
		bags = new int[N+1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			bags[i][0] = Integer.parseInt(st.nextToken());
			bags[i][1] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N+1][K+1];
		DP(N, K);
		System.out.println(dp[N][K]);
	}
	
	public static int DP(int n, int k) {
		if (n == 0) { // 기저 조건
			return 0;
		}
		if (dp[n][k] > 0) { // dp 배열값 존재하면 그거 사용
			return dp[n][k];
		}
		if (k - bags[n][0] < 0) { // 넣을 무게가 가방의 허용 무게를 넘기면
			dp[n][k] = DP(n-1, k); // 그 전 dp 배열의 값을 저장
		} else { // 넣을 무게가 가방의 허용 무게 안이면
			dp[n][k] = Math.max(DP(n-1, k), bags[n][1] + DP(n-1, k-bags[n][0])); // DP(n-1, k), bags[n][1] + DP(n-1, k-bags[n][0]) 둘 중 큰 값을 저장
		}
		return dp[n][k];
	}
}
```

## BOJ10253 헨리
### 🎈 해결방법 :
1. d = b / a + 1 로 d의 최소값 찾기
2. 유클리드 호제법 사용해서 최대공약수 찾기
3. 최대공약수 활용해서 계산

### 💬 코멘트 :
간만에 수학 문제 푼듯

### 📄 코드
```java
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
```