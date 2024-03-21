package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ5904 {

	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		// S[0]일 때, 문자열의 총 길이
		int arr = 3;
		// S[k-1]의 마지막 index
		int preArr = 3;
		// index
		int k = 0;


		// n의 범위를 구해주기
		//S(0) = "m o o"
		//S(1) = "m o o m o o o m o o"
		//S(2) = "m o o m o o o m o o m o o o o m o o m o o o m o o "
		while (arr < n) {
			k++;
			arr = 2 * preArr + (1 + 2 + k);
			preArr = arr;
		}

		// 재귀함수에 시작값으로 넣어주기 위해 preArr을 다시 구함
		preArr = (arr - (1 + 2 + k)) / 2;

		char c = ' ';

		// 입력된 숫자가 3 이하일 경우
		if (k == 0) {
			if (n == 1)
				c = 'm';
			else
				c = 'o';
		} else {
			// 3보다 클 경우
			// 범위에 포함되는지 파악
			if (preArr + 1 <= n && n < preArr + (1 + 2 + k)) {
				if (preArr + 1 == n) {
					c = 'm';
				} else
					c = 'o';
			} else
				 // 현재 구간에 속하지 않으면 더 큰 구간을 고려하기 위해 재귀 호출
				c = moo(preArr + 1 + (1 + 2 + k), k - 1, arr);
		}

		System.out.println(c);

	}
	
	//	private static char moo(범위){
	//
	//	//기저조건
	//	if(범위가 3이하인 경우){
	//		return 'm' or 'o';
	//	}else{
	//		// N 이 왼쪽 영역인 경우
	//		return moo(Left);
	//		
	//		// N 이 오른쪽 영역인 경우
	//		return moo(Right);
	//		
	//		// N 이 가운데 영역인 경우
	//		return 'm' or 'o';
	//
	//		}
	//
	//	}

	// 시작 index, k 값, 끝 index
	private static char moo(int pre, int k, int post) {

		// 중간 moo의 크기 구하기
		int index = ((post) / 2);

		// 기저조건
		if (k == 0) {
			if (n == pre)
				return 'm';
			else
				return 'o';
		} else {

			// 왼쪽인 경우
			if (pre <= n && n < pre + index) {
				if (pre == n)
					return 'm';
				return moo(pre, k - 1, pre + index - 1);
				// else return 'o';
			}
			// 오른쪽인 경우
			else if (pre + index + (k + 3) <= n && n <= post) {
				if (pre + index + (k + 3) == n)
					return 'm';
				else
					return moo(pre + index + (k + 3), k - 1, post);
			}
			// 가운데
			else {
				if (pre + index == n)
					return 'm';
				else
					return 'o';
			}
		}

	}

}
