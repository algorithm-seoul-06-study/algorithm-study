# Week8
## BOJ5904 Moo 게임
### 🎈 해결방법 :
1. Moo 길이를 먼저 구해서 길이를 통해 N번째 글자를 찾도록 접근
2. S(k) = S(k-1) + moo..o + S(k-1) 점화식을 통해 (앞 / 가운데 / 뒤)로 부분을 쪼개서 생각
3. k값을 줄여가면서 해당하는 부분으로 접근해서 답을 찾음

### 💬 코멘트 :
제출할 때 있어서는 안될 sysout이 있는지 확인해보자...

### 📄 코드
```java
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


```

## BOJ2636 치즈
### 🎈 해결방법 :
1. 1에서 출발해서 0을 통해서 경계에 닿을 수 있는지 BFS로 탐색
2. 단계적으로 진행
3. 시간초과 엔딩

### 💬 코멘트 :
실패한 코드지만 가진게 없어 이거라도 봐주십쇼  
바깥에서부터 달팽이 순회로 안쪽으로 접근하면서 경계에 닿을 수 있는 곳인지 확인하면 시간초과가 안나올까?

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2636 {
	static int[][] map;
	static Stack<Integer> cheese;
	static boolean flag;
	static int removeCnt;
	static Queue<Integer> rq;
	static Queue<Integer> cq;
	static int sr;
	static int sc;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		cheese = new Stack<>();
		int cheeseCnt = 0;
		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < map[0].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					cheeseCnt++;
				}
			}
		}
		cheese.push(cheeseCnt);
		rq = new LinkedList<>();
		cq = new LinkedList<>();
		while (cheese.peek() > 0) {
			removeCnt = 0;
			for (int r = 1; r < map.length - 1; r++) {
				for (int c = 1; c < map[0].length - 1; c++) {
					if (map[r][c] == 1) {
						flag = false;
						sr = r;
						sc = c;
						findSide(r+1, c);
						if (flag) continue;
						findSide(r-1, c);
						if (flag) continue;
						findSide(r, c+1);
						if (flag) continue;
						findSide(r, c-1);
						if (flag) continue;
					}
				}
			}
			cheese.push(cheese.peek() - removeCnt);
			while (!rq.isEmpty()) {
				map[rq.poll()][cq.poll()] = 0;
			}
		}
		System.out.println(cheese.size() - 1);
		cheese.pop();
		System.out.println(cheese.peek());
	}

	private static void findSide(int r, int c) {
		if (map[r][c] == 0) {
			if (r == 0 || r == map.length - 1 || c == 0 || c == map[0].length - 1) {
				flag = true;
				removeCnt++;
				rq.offer(sr);
				cq.offer(sc);
				return;
			}
			map[r][c] = 2;
			findSide(r+1, c);
			map[r][c] = 0;
			if (flag) return;
			map[r][c] = 2;
			findSide(r-1, c);
			map[r][c] = 0;
			if (flag) return;
			map[r][c] = 2;
			findSide(r, c+1);
			map[r][c] = 0;
			if (flag) return;
			map[r][c] = 2;
			findSide(r, c-1);
			map[r][c] = 0;
			if (flag) return;
		}
		
	}
}

```