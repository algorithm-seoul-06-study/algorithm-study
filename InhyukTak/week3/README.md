# Week3
## BOJ9095 1, 2, 3 더하기
### 🎈 해결방법 :
1. DP문제 파악
2. 점화식 작성 : f(n) = f(n-3) + f(n-2) + f(n-1)
3. 재귀로 구현

n을 1, 2, 3의 합으로 나타내는 방법의 수를 구할 때
n-3, n-2, n-1의 방법들에 각각 +3, +2, +1을 해주면 n의 방법이 된다.
### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->

### 📄 코드
```java
package algorithm_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9095 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			System.out.println(ott(n));
			
		}
	}
	
	// OneTwoThree
	public static int ott(int n) {
		if (n == 1) {
			return 1;
		} else if (n == 2) {
			return 2;
		} else if (n == 3) {
			return 4;
		} else {
			return ott(n-1) + ott(n-2) + ott(n-3);			
		}
	}
}
```

## BOJ9935 문자열 폭발
### 🎈 해결방법 :
1. result Stack에 문자 하나씩 담음
2. 폭탄배열 끝문자와 스택 최상단이 같으면 check() 실행
3. result Stack에서 하나씩 꺼내면서 비교 후 tmp Stack에 임시 보관
4. 폭탄배열이 맞으면 tmp Stack만 비우고, 폭탄배열이 아니면 tmp Stack을 result Stack에 담음
5. 최종 result Stack을 StringBuilder에 넣어서 reverse 후 출력

### 💬 코멘트 :
나름 보이어-무어(아닐수도 있음)를 구현해서 ArrayList로 풀었는데 시간초과가 나와서 다른 방법을 찾다가 Stack 활용해서 해결

### 📄 코드
```java
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
		
		// result 스택에 무조건 푸쉬 후 폭탄배열 끝문자와 스택 최상단이 같으면 check() 실행
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
```
### 📄 시간초과 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ9935x {
	static List<Character> text = new ArrayList<>(); // 문자열 배열
	static List<Character> boom = new ArrayList<>(); // 폭발패턴 배열
	static int idx; // 폭발패턴 문자열의 끝자리와 맞추는 문자열 배열 위치
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 문자열 입력 및 저장
		String str = br.readLine();
		for (int i = 0; i < str.length(); i++) {
			text.add(str.charAt(i));
		}
		// 패턴 입력 및 저장
		str = br.readLine();
		for (int i = 0; i < str.length(); i++) {
			boom.add(str.charAt(i));
		}
		// 시작 idx 설정
		idx = boom.size() - 1;
		// idx가 text 길이를 넘어갈 때까지 반복
		while (idx < text.size()) {
			// text의 idx번째 문자와 폭발패턴 끝문자가 같으면
			if (text.get(idx).equals(boom.get(boom.size() - 1))) {
				// 기존 idx와 check()를 실행해서 그 반환값을 비교하고 idx로 설정
				if (idx == (idx = check(idx))) {
					// 같으면 폭발패턴을 발견하지 못한것이므로 skip() 실행
					idx = skip(idx);
				}
			} else {
				// text의 idx번째 문자와 폭발패턴 끝문자가 다르면 skip() 실행
				idx = skip(idx);
			}
		}
		
		// text 비어있으면 모든 문자열이 폭발한거니 "FRULA" 출력
		if (text.size() == 0) {
			System.out.println("FRULA");
		} else {			
			StringBuilder sb = new StringBuilder();
			// text에 있는 값들 sb에 순차적으로 저장 및 출력
			for (int i = 0; i < text.size(); i++) {
				sb.append(text.get(i));
			}
			System.out.println(sb);
		}
	}
	
	static int skip(int idx) {
		// 폭발패턴 끝자리와 매칭되는 문자가 폭발패턴 중에 있으면 그정도만 skip
		for (int i = boom.size() - 2; i >= 0; i--) {
			if (text.get(idx) == boom.get(i)) {
				return idx + boom.size() - 1 - i;
			}
		}
		// 없으면 폭발패턴 길이만큼 skip
		return idx + boom.size();
	}
	
	static int check(int idx) {
		// 폭발배열 뒤에서부터 같은지 확인
		for (int i = 0; i < boom.size(); i++) {
			// 다르면 그냥 빠져나옴
			if (text.get(idx - i) != boom.get(boom.size() - 1 - i)) {
				return idx;
			}
		}
		// 같으면 폭발패턴 문자열 지우고 (폭발패턴 길이 - 1)만큼 앞으로
		for (int i = idx; i > idx - boom.size(); i--) {
			text.remove(i);
		}
		return idx - boom.size() + 1;
	}
}

```