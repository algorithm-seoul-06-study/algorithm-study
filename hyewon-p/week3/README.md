# Week 3
## BOJ9095 1, 2, 3 더하기
### 🎈 해결방법 :
재귀를 통해 모든 경우의 수를 구함

### 💬 코멘트 :
처음에는 재귀로 조합을 구해서 조합을 가지고 순열을 검사할 생각이었는데 조합에서 중복을 제거하지 않으면 그것이 모든 경우의 수...라는 것을 깨달았습니다. 조합을 재귀로 직접 구현하는 것이 좋은 경험이었던 것 같습니다.

### 📄 코드
```java
import java.io.IOException;

public class Main {
	static int n;
	static int answer;

	public static void main(String[] args) throws IOException {
		int T = readInt();
		for (int t = 0; t < T; t++) {
			n = readInt();
			answer = 0;
			combination(0);
			System.out.println(answer);
		}
	}

	// 재귀로 모든 조합 경우의 수를 구함
	static void combination(int sum) {
		// 만약 조합의 모든 수의 합이 목표 숫자라면
		// 답+1, return
		if (sum == n) {
			answer++;
			return;
		}
		// 1,2,3 전부에 대해 검사
		for (int i = 0; i < 3; i++) {
			// 목표 숫자 - 현재 합을 1,2,3으로 나누었을 때
			// 나눌 수 있다면 다음으로
			if ((n - sum) / (i + 1) > 0) {
				combination(sum + i + 1);
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
```

## BOJ9935 문자열 폭발
### 🎈 해결방법 :
시도해본 방법
1. 앞에서부터 하나씩 검사하여 폭발하는 글자면 스택에 저장하고, 단어가 완성되면 꺼내기
2. 반복해서 돌면서 문자열이 일치하면 지워나가기

### 💬 코멘트 :
메모리초과+시간초과의 굴레에 빠져 해결하지 못했습니다...

### 📄 코드
```java
import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BOJ9935 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String letters = br.readLine();
		String bomb = br.readLine();
		Deque<List<Character>> stack = new ArrayDeque<>();

		boolean printed = false;

		int i = 0;
		int visited = 0;
		while (i < letters.length()) {
			int idx = i;
			// 폭발 문자열에 해당 인덱스 문자가 존재하는지 검사
			int letterIdx = bomb.indexOf(letters.charAt(i));
			if (letterIdx > -1) {
				// 존재한다면 첫번째 글자로 맞춤
				idx -= letterIdx;
			}
			
			// 검사하지 않은 이전의 문자열 중에
			// 폭발 문자열의 첫글자와 일치하는 가장 앞글자 찾기
			while (true) {
				// 만약 검사하려는 시작 인덱스가 0보다 작거나 이미 검사했던 경우
				if (idx - 1 < 0 || idx - 1 < visited) {
					break;
				}
				// 검사하려는 첫번째 글자보다 전에 폭발 문자열이 있는지 검사
				letterIdx = bomb.indexOf(letters.charAt(idx - 1));
				if (letterIdx < 0) {
					break;
				}
				idx -= letterIdx + 1;
			}
			
			// 찾은 글자 인덱스 앞의 폭발하지 않는 문자열들 출력
			if (visited < idx) {
				System.out.print(letters.substring(visited, idx));
				printed = true;
			}
			
			// 폭발 문자열에 해당하는 문자를 저장해둔 스택이 존재하거나
			// 현재 글자가 폭발 문자열의 첫글자와 일치한다면 검사를 시작
			while (!stack.isEmpty() || letters.charAt(idx) == bomb.charAt(0)) {
				// 첫글자라면 스택에 리스트 추가
				if (letters.charAt(idx) == bomb.charAt(0)) {
					List<Character> tmp = new ArrayList<>();
					tmp.add(letters.charAt(idx));
					stack.add(tmp);
					idx++;
				}
				// 현재 인덱스의 문자가 마지막으로 저장했던 스택의 폭발 문자 다음 문자인 경우 스택에 추가
				if (letters.charAt(idx) == bomb.charAt(stack.peekLast().size())) {
					List<Character> tmp = stack.pollLast();
					tmp.add(letters.charAt(idx));
					// 폭발 문자열이 완성되면 스택에서 제거
					if (tmp.size() < bomb.length()) {
						stack.add(tmp);
					}
					idx++;
				} else {
					// 폭발 문자열이 완성되지 않았으므로 스택 비우기
					while (!stack.isEmpty()) {
						for (char s : stack.poll()) {
							System.out.print(s);
							printed = true;
						}
					}
					;
					break;
				}
				i = idx;
			}
			// 현재 인덱스까지 검사했음을 기록
			visited = idx;
			// 인덱스 점프 
			i += bomb.length();
		}
		
		// 문자열이 끝났는데도 스택이 남아있으면 출력
		while (!stack.isEmpty()) {
			for (char s : stack.poll()) {
				System.out.print(s);
				printed = true;
			}
		}
		
		// 검사하려는 인덱스가 문자열을 넘어간 경우의 처리
		if (visited < letters.length()) {
			String leftover = letters.substring(visited, letters.length());
			if (!leftover.equals(bomb)) {
				System.out.println(leftover);
				printed = true;
			}
		}
		
		// 출력한 적 없을 때
		if (!printed)
			System.out.println("FRULA");
	}

}
```