import java.util.Scanner;
import java.util.Stack;

public class BOJ5904 {
	// S(k)의 길이를 저장할 스택
	static Stack<Integer> lens = new Stack<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		// S(0)의 길이 3을 스택에 저장
		lens.push(3);
		int k = 0;
		// 가장 마지막에 저장한 S(k)의 길이 > N 될 때 까지 반복
		while (lens.peek() < N) {
			// S(k)의 길이 = S(k-1)의 길이 * 2 + (k + 3)
			// k값 1씩 올려주면서 스택에 S(k)의 길이 순차적으로 저장
			lens.push(lens.peek() * 2 + ++k + 3);
		}
		// k일 때, N번째 글자를 찾는 메서드
		find(N, k);
	}
	
	static void find(int tmp, int k) {
		// S(k)의 길이 스택에서 빼기
		lens.pop();
		// k == 0 이면 
		if (k == 0) {
			// tmp == 1 이면 "m" 출력
			if (tmp == 1) {
				System.out.println("m");
			// tmp != 1 이면 "o" 출력
			} else {
				System.out.println("o");
			}
		// k != 0 이면
		} else {
			// S(k-1)의 길이를 len 으로 선언
			int len = lens.peek();
			// tmp가 가운데에 위치하면
			if (tmp > len && tmp <= len + (k + 3)) {
				// tmp -= len 계산 후
				tmp -= len;
				// tmp == 1 이면 "m" 출력
				if (tmp == 1) {
					System.out.println("m");
				// tmp != 1 이면 "o" 출력
				} else {
					System.out.println("o");
				}
			// tmp가 앞이나 뒤에 위치하면
			} else {
				// tmp가 뒤에 위치하면
				if (tmp > len + (k + 3)) {
					// tmp -= len + (k + 3) 계싼
					tmp -= len + (k + 3);
				}
				// tmp == 1 이면 "m" 출력
				if (tmp == 1) {
					System.out.println("m");
				// tmp != 1 이면 계산된 tmp와 k-1 가지고 재귀호출
				} else {
					find(tmp, k - 1);
				}
			}
		}
	}
}
