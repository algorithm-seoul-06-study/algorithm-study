package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1182_부분수열의합 {
	static int[] arr;
	static int totalSum;
	static int result;
	static int N;
	static int S;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		// 배열 입력받음과 동시에 최대합 totalSum 구하기
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			totalSum += arr[i];
		}
		
		// 부분수열이 원래 수열과 같을 때 먼저 비교
		if (totalSum == S) {
			result++;
		}
		
		// N이 1이 아닐 때 check() 함수 실행
		if (N > 1) {
			check(1, 0, 0);			
		}
		
		System.out.println(result);
	}
	
	// a : 부분수열의 원소의 개수
	// b : 시작 index
	// sum : 중간합
	static void check(int a, int b, int sum) {
		for (int i = b; i < arr.length; i++) {
			// 중간합에 arr[i] 더하기
			sum += arr[i];
			// 중간합이 S와 같으면 result++;
			if (sum == S) {
				result++;
			}
			// N이 짝수이면서 원소의 개수가 N/2 일 경우를 제외하고 (최대합 - 중간함)이 S와 같으면 result++;
			if (!(N % 2 == 0 && a == N / 2) && totalSum - sum == S) {
				result++;
			}
			// a < N/2 까지 원소의 개수 하나 늘리고 b는 다음 원소부터 시작하는걸로 반복
			if (a < N / 2) {
				check(a + 1, i + 1, sum);				
			}
			// 중간합 되돌리기
			sum -= arr[i];
		}
	}
}