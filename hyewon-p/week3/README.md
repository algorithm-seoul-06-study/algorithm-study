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
3. (성공) stack에 저장 => 저장한 연속적인 문자의 길이가 폭발 문자열의 길이와 같으면 그만큼을 검사하고 일치할 때만 제거

### 💬 코멘트 :
~~메모리초과+시간초과의 굴레에 빠져 해결하지 못했습니다...~~
인혁님의 아이디어를 참고해서 드디어 풀었습니다(감사합니다)


### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ9935 {
	static String bomb;

	public static void main(String[] args) throws IOException {
		Deque<Character> stack = new ArrayDeque<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		bomb = br.readLine();
		
		int targetIdx = 0;
		for (char letter : str.toCharArray()) {
			// 만약 목표 문자(폭발 문자열에서 targetIdx에 해당하는 문자)와 검사하는 문자가 같거나
			// 폭발 문자열의 첫글자인 경우 targetIdx 갱신
			if (bomb.charAt(targetIdx) == letter || bomb.charAt(0) == letter) {
				targetIdx = bomb.charAt(targetIdx) == letter ? targetIdx + 1 : 1;
			}
			stack.add(letter);
			
			if (targetIdx == bomb.length()) {
				// 단어가 폭발 문자열과 일치하는지 확인
				check(stack);
				// targetIdx 다시 정해주기
				if (stack.isEmpty()) {
					targetIdx = 0;
				} else {
					// 이번 폭발 전에 마지막으로 stack에 넣었던 문자 검사
					int nextIdx = bomb.lastIndexOf(stack.peekLast());
					targetIdx = nextIdx == -1 ? 0 : (nextIdx + 1) % bomb.length();
				}

			}
		}
		
		// stack 내용 출력
		StringBuilder sb = new StringBuilder();
		if (stack.isEmpty()) {
			sb.append("FRULA");
		} else {
			while (!stack.isEmpty()) {
				sb.append(stack.pollFirst());
			}
		}
		System.out.println(sb);
	}

	// 단어 검사 함수
	static void check(Deque<Character> stack) {
		// 임시로 배열을 만들어서 stack에서 빼오는 문자 저장
		char[] tmp = new char[bomb.length()];
		for (int i = bomb.length() - 1; i >= 0; i--) {
			if (stack.peekLast() == bomb.charAt(i)) {
				tmp[i] = stack.pollLast();
			} else {
				// 폭발 문자열과 일치하지 않는 경우라면 tmp 내부 문자 돌려놓고 return
				for (char t: tmp) {
					if (t>0) {
						stack.add(t);						
					}
				}
				return;
			}
		}
	}
}

```