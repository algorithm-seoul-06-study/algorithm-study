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
