# Week 8
## BOJ2636 치즈
### 🎈 해결방법 :

1. BFS의 visited를 통해서 공기부분과 치즈부분틀 true, false로 구분시킴

2. 탐색을 하며 공기와 치즈에 맞닿는부분에서는 치즈를 감소시킴

3. bfs로 모든 좌표가 탐색이되면 탐색 종료 -> 반복


### 💬 코멘트 :

bfs 템플릿을 활용하면 정말 정석적으로 풀수있는문제였다.

다만 bfs라고 단번에 판단하기 힘들었고,

다른 방식으로 도전함에 시간을 많이 소모함.


### 📄 코드

```

public class BOJ2636 {

	static int[] dx = { 0, 1, 0, -1 }; // 상하좌우 이동을 위한 x 좌표 변화량
	static int[] dy = { 1, 0, -1, 0 }; // 상하좌우 이동을 위한 y 좌표 변화량
	
	static boolean[][] visited; // 방문 여부를 저장하는 배열
	static int[][] board; // 치즈 판의 상태를 저장하는 배열
	
	static int n, m, cheese; // 치즈 판의 크기

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new int[n][m];
		cheese = 0;

		// 총 치즈의 갯수 세기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 1)
					cheese++;
			}
		}

		int count = 0; // 이전 시간대의 치즈 개수
		int time = 0; // 치즈가 모두 녹는데 걸리는 시간

		// 치즈가 모두 녹을 때까지 반복
		while (cheese != 0) {
			
			// 현재 치즈 갯수
			count = cheese;
			
			// 녹는시간 추가
			time++;
			// 시간별 치즈판 초기화
			visited = new boolean[n][m];
			
			bfs();
			
		}

		System.out.println(time);
		System.out.println(count);
		
	}

	// BFS
	public static void bfs() {
		
		Queue<int[]> start = new LinkedList<>();
		
		// 출발 좌표
		start.offer(new int[] { 0, 0 });
		
		// 출발 지점 방문 표시
		visited[0][0] = true;

		while (!start.isEmpty()) {
			
			int[] now = start.poll();

			// 상하좌우 이동
			for (int i = 0; i < 4; i++) {
				
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				// 다음 위치가 범위 내에 있고, 아직 방문하지 않았다면
				if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny]) {
					
					visited[nx][ny] = true; // 방문 표시
					
					if (board[nx][ny] == 0) { // 치즈가 아닌 경우
						
						start.offer(new int[] { nx, ny }); // 큐에 추가하여 탐색 계속
						
					} else { // 치즈가 있는 경우
						
						// 치즈 개수 감소
						cheese--;
						
						// 치즈 제거
						board[nx][ny] = 0;
						
					}
					
				}
				
			}
			
		}
		
	}
}


```

## BOJ2636 치즈
### 🎈 해결방법 :

문자열이 길어지는 규칙을 보면

S(0) = "m o o"
S(1) = "m o o m o o o m o o"
S(2) = "m o o m o o o m o o m o o o o m o o m o o o m o o "
...
 
S[k] = S[k-1] + moo + o(k) + S[k-1]로 길어짐.

---

입력된 숫자(N)가 10 정도 된다고 가정했을 경우,

수많은 moo 들을 크게

왼쪽 moo(Left) / 가운데 moo(Middle) / 오른쪽 moo(Right) 들로 나누어 보았다.

 
예시 > S(1) = "m o o / m o o o / m o o"

왼쪽 moo(Left) / 가운데 moo(Middle) / 오른쪽 moo(Right)


위와 같이 나누어 생각한 후 입력된 N의 범위가

어디에 해당하는지 수를 세어 판별하고 범위로 재귀호출해준다.

---

점화식

S[k+1] = S[k] + (4+k) + S[k];

(4+k)인 이유?

예시 > S(1) = "m o o / m o o o / m o o"

(1(m의 개수) + 2(o의 기본 개수) + k+1) ⇒ k+4



### 💬 코멘트 :


### 📄 코드

```
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
```