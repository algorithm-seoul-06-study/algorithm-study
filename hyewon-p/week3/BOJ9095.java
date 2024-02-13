import java.io.IOException;

public class BOJ9095 {
	static int n;
	static int answer;

	public static void main(String[] args) throws IOException {
		int T = readInt();
		for (int t = 0; t < T; t++) {
			n = readInt();
			answer = 0;
			combination(new int[3], 0);
			System.out.println(answer);
		}
	}

	// 재귀로 모든 조합 경우의 수를 구함
	static void combination(int[] comb, int sum) {
		// 만약 조합의 모든 수의 합이 목표 숫자라면
		// 답+1, return
		if (sum == n) {
			answer++;
			return;
		}
		// 1,2,3 전부에 대해 검사
		for (int i = 0; i < 3; i++) {
			// 목표 숫자 - 현재 합을 1,2,3으로 나누었을 때
			// 나눌 수 있다면 조합에 포함시키고 다음으로
			if ((n - sum) / (i + 1) > 0) {
				comb[i]++;
				combination(comb, sum + i + 1);
				// comb 사용 후 돌려놓기
				comb[i]--;
			}
		}
		return;
	}
	
	// 입출력
	static int readInt() throws IOException {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		if (c == 13) System.in.read();
		return n;
	}
}