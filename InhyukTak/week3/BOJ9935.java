package algorithm_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9935 {
	static char[] text; // 문자열 배열
	static char[] boom; // 폭발패턴 배열
	static Stack<Character> result; // 출력될 문자열 스택
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 문자열 입력 및 저장
		String str = br.readLine();
		text = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			text[i] = str.charAt(i);
		}
		// 패턴 입력 및 저장
		str = br.readLine();
		boom = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			boom[i] = str.charAt(i);
		}
		// 스택 초기화
		result = new Stack<>();
		
		// result 스택에 무조건 푸쉬 후 폭탄배열 끝문자와 스택 최상딘이 같으면 check() 실행
		for (int i = 0; i < text.length; i++) {
			result.push(text[i]);
			if (result.peek() == boom[boom.length - 1]) {
				check();
			}
		}
		
		// result 비어있으면 모든 문자열이 폭발한거니 "FRULA" 출력
		if (result.isEmpty()) {
			System.out.println("FRULA");
		} else {
			StringBuilder sb = new StringBuilder();
			// result에 있는 값들 pop()으로 다 꺼내면서 sb에 저장
			while (!result.isEmpty()) {
				sb.append(result.pop());
			}
			// 문자열 반대로 되어있어서 뒤집기
			sb.reverse();
			System.out.println(sb);
		}
	}
	
	static void check() {
		// 중간 저장 스택
		Stack<Character> temp = new Stack<>();
		// 패턴 끝문자부터 시작해서 앞으로 가면서 비교
		for (int i = boom.length - 1; i >= 0; i--) {
			// result가 비어있지 않거나 값이 같으면 result에서 꺼내서 temp에 넣기
			if (!result.isEmpty() && boom[i] == result.peek()) {
				temp.push(result.pop());
			} else {
				// 아니면 temp에 있는거 다시 다 꺼내서 result에 다시 넣고 check() 종료
				while (!temp.isEmpty()) {
					result.push(temp.pop());
				}
				return;
			}
		}
		// 폭발문자열 찾았으면 temp에 있는거 다 비우고 check() 종료
		while (!temp.isEmpty()) {
			temp.pop();
		}
	}
}
