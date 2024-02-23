package study.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17298 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] input = new int[N];
		st = new StringTokenizer(br.readLine());
		// 입력값 뒤에서부터 input 배열에 저장
		for (int i = N - 1; i >= 0; i--) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		// 결과를 담을 배열 result에 뒤에서부터 저장
		int[] result = new int[N];
		int idx = N - 1;
		
		// 배열 길이만큼 반복
		for (int i = 0; i < result.length; i++) {
			// 스택이 비거나 peek() > input[i] 일 때까지 pop()
			while (!stack.isEmpty() && stack.peek() <= input[i]) {
				stack.pop();
			}
			// 스택 비어있으면 -1 저장
			if (stack.isEmpty()) {
				result[idx--] = -1;
			// 스택 안비어있으면 peek() 저장
			} else {
				result[idx--] = stack.peek();
			}
			// push()해서 스택 채우기
			stack.push(input[i]);			
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < result.length; i++) {
			sb.append(result[i]).append(" ");
		}
		System.out.println(sb);
	}
}
