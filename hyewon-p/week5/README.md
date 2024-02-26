# Week 5

## BOJ1932 정수삼각형

### 🎈 해결방법 :
다익스트라?인가? 부모 노드 중 큰 값을 저장하고 더해가는 방식
1. 부모 노드 중 큰 값(next 리스트에 저장된)에 실제 노드값을 더해서 rec 배열에 저장
2. next 리스트를 비우고 새로 저장
    1. 다음 줄의 n, n+1번째 인덱스 값이 자식이므로 next 리스트의 해당 인덱스에 현재 값(부모노드) 저장
    2. 이미 저장된 값이 있으면 비교해서 큰 값으로 갱신
3. 리프 노드에 대해 최대값 출력

### 💬 코멘트 :


### 📄 코드
```java
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BOJ1932 {
	public static void main(String[] args) throws IOException {
		int n = readInt();
        // 다음 레벨을 위한 부모 노드 중 최댓값 저장 리스트
		List<Integer> next = new ArrayList<>();
		int max = 0;

		for (int i = 0; i < n; i++) {
            // 부모값에 노드값을 더해서 값을 저장할 배열
			int[] rec = new int[i+1];
			for (int j=0; j<i;j++) {		
				rec[j] = next.get(j) + readInt();
			}
			next.clear();
            // 부모 값 중 최댓값 저장해주기
			for (int j = 0; j < i+1; j++) {
				if (j > 0) {
					next.set(j, Math.max(next.get(j), rec[j]));
				}else {
					next.add(rec[j]);
				}
				next.add(rec[j]);
			}
		}
        // 리프 노드 중 최댓값
		for (int j=0; j<n;j++) {		
			max= Math.max(max, next.get(j) + readInt());
		}
		System.out.println(max);
		

	}

	public static int readInt() throws IOException {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		if(c==13) c= System.in.read();
		return n;
	}
}

```

## BOJ1987 알파벳

### 🎈 해결방법 :
완전탐색+재귀로 돌면서 어떤 알파벳을 지나왔는지 기록하고, 중복되는 알파벳을 만났을 때 기록한 알파벳의 개수를 최댓값과 비교하여 갱신

### 💬 코멘트 :
카운트 배열 + 아스키 코드를 사용해서 알파벳의 개수를 기록했는데 생각보다 쉽게 풀려서 놀랐고 배운 거 잘 써먹어서 뿌듯했음

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1987 {
	static String[] board;
	static boolean[][] visited;
	static int[] alphabets;
	static int R, C;
	static int answer;

	public static void main(String[] args) throws IOException {
		FastReader fr = new FastReader();
		R = fr.nextInt();
		C = fr.nextInt();
		board = new String[R];
		visited = new boolean[R][C];
		alphabets = new int[26];
		for (int r = 0; r < R; r++) {
			board[r] = fr.nextLine();
		}
		find(0, 0);
		System.out.println(answer);
	}

	static void find(int x, int y) {
		if (!check(x, y) || visited[y][x] || alphabets[board[y].charAt(x) - 'A'] > 0) {
			answer = Math.max(sum(alphabets), answer);
			return;
		}
		visited[y][x] = true;
		alphabets[board[y].charAt(x) - 'A']++;
		find(x + 1, y);
		find(x, y + 1);
		find(x - 1, y);
		find(x, y - 1);
		alphabets[board[y].charAt(x) - 'A']--;
		visited[y][x] = false;

	}

	static boolean check(int x, int y) {
		if (x < 0 || x > C - 1) {
			return false;
		}
		if (y < 0 || y > R - 1) {
			return false;
		}
		return true;
	}

	static int sum(int[] list) {
		int total = 0;
		for (int l : list) {
			total += l;
		}
		return total;
	}
}

class FastReader {
	BufferedReader br;
	StringTokenizer st;

	public FastReader() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	String nextLine() {
		try {
			return br.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	int nextInt() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return Integer.parseInt(st.nextToken());
	}

}
```