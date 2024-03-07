package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2011_암호코드 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] arr = br.readLine().toCharArray();
		// dp[0] = 1 처리 하려고 길이 하나 더 늘림
		int[] dp = new int[arr.length + 1];
		
		// 시작 숫자가 0이 아닐 때 실행
		if (arr[0] != '0') {
			// 초기값 설정
			dp[0] = 1;
			dp[1] = 1;
			for (int i = 2; i <= arr.length; i++) {
				// 11 ~ 19, 21 ~ 26 이면 한자리와 두자리 모두 적용되므로 전 값과 전전 값을 합침
				if ((arr[i-2] == '1' && arr[i-1] >= '1' && arr[i-1] <= '9') || (arr[i-2] == '2' && arr[i-1] >= '1' && arr[i-1] <= '6'))
					dp[i] = dp[i-1] + dp[i-2];
				// 1과 2가 아닌 수 다음에 0이오는 경우 잘못된 암호라 for문 바로 빠져나옴
				else if ((arr[i-2] != '1' && arr[i-2] != '2') && arr[i-1] == '0')
					break;
				// 1과 2 다음에 0 나오면 전전 값으로 돌아감
				else if (arr[i-1] == '0')
					dp[i] = dp[i-2];
				// 보통의 한자리 숫자일 경우 전 값과 같음
				else
					dp[i] = dp[i-1];
				
				if (dp[i] >= 1000000)
					dp[i] %= 1000000;
			}
		}
		
		// 잘못된 암호면 dp[arr.length]에는 초기값인 0이 출력됨
		System.out.println(dp[arr.length]);
	}
}
