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
