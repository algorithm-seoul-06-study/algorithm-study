package study.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11866 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		int[] count = new int[N+2];
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		int a = 0;
		// N번 반복
		for (int i = 0; i < N; i++) {
			int cnt = 0;
			// cnt == K가 될 때까지 반복 => 제거된 수를 제외한 카운팅
			while (cnt < K) {
				// 다음 수가 이미 제거된 수면 다음 수로 이동 (가짜 이동)
				if (count[a+1] == 1) {
					a++;
				// 다음 수가 제거되지 않은 수라면 cnt값을 올리고 다음 수로 이동 (진짜 이동)
				} else {
					cnt++;
					a++;
				}
				// a값이 최대값인 N을 넘기면 1로 초기화
				if (a == N + 1) {
					a = 1;
				}
			}
			// K만큼 이동해서 도착한 수의 count값을 올려 제거 표시
			count[a]++;
			// a가 1일땐 N+1의 count값도 올려서 count[a+1]에 적용되도록 설정
			if (a == 1) {
				count[N+1] = 1;
			}
			// sb에 추가
			sb.append(a).append(", ");
		}
		
		sb.delete(sb.length() - 2, sb.length()).append(">");
		System.out.println(sb);
	}
}
