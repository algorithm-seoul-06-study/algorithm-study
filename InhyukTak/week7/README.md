# Week7
## BOJ1389 케빈 베이컨의 6단계 법칙
### 🎈 해결방법 :
1. 친구 관계를 일차원 배열에 저장해서 짝수 홀수 판단으로 짝을 찾아서 진행
2. 카운트 배열을 활용해서 각 숫자의 케빈 베이컨 수를 결과 배열의 해당 인덱스에 저장

### 💬 코멘트 :
돌아돌아 어렵게 푼 느낌...

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1389 {
	static int N;
	static int[] arr;
	static int[] results;
	static int[] counts;
	static int findCount;
	static Queue<Integer> queue;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new int[M*2];
		
		// 친구 관계를 1차원 배열 arr에 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				arr[i*2+j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 결과 저장 배열
		results = new int[N+1];
		for (int i = 1; i <= N; i++) {
			// count 배열
			counts = new int[N+1];
			// counts[i] 값 -1로 초기화 (0 안겹치게)
			counts[i] = -1; 
			// findCount 0으로 초기화
			findCount = 0;
			// queue 선언 후 i 넣기
			queue = new LinkedList<>();
			queue.offer(i);
			// 함수 실행
			findFriend(1);
			// results[i] 값 1로 초기화 (counts[i] == -1 이라서)
			results[i] = 1; 
			// results[i] 에 counts 값 다 더하기
			for (int j = 1; j < counts.length; j++) {
				results[i] += counts[j]; 
			}
		}
		
		// 결과의 최소값
		int resultMin = Integer.MAX_VALUE;
		// 결과가 최소값인 숫자
		int result = 0;
		for (int i = 1; i < results.length; i++) {
			if (resultMin > results[i]) {
				resultMin = results[i];
				result = i;
			}
		}
		System.out.println(result);
	}
	
	static void findFriend(int k) {
		// 큐 사이즈만큼 반복
		for (int size = queue.size(); size > 0; size--) {
			// 큐 빼기
			int n = queue.poll();
			for (int i = 0; i < arr.length; i++) {
				// arr[i]가 n일 때
				if (arr[i] == n) {
					// n이 짝수고 counts[arr[i+1]] == 0 (처음 만나는 숫자) 이면
					if (i % 2 == 0 && counts[arr[i+1]] == 0) {
						// 짝을 큐에 넣고
						queue.offer(arr[i+1]);
						// 카운트 배열에 k값 넣고
						counts[arr[i+1]] = k;
						// 찾은 인원 +1
						findCount++;
						// n이 홀수고 counts[arr[i-1]] == 0 (처음 만나는 숫자) 이면
					} else if (i % 2 == 1 && counts[arr[i-1]] == 0) {
						// 짝을 큐에 넣고
						queue.offer(arr[i-1]);
						// 카운트 배열에 k값 넣고
						counts[arr[i-1]] = k;
						// 찾은 인원 +1
						findCount++;
					}
				}
				// 찾은 인원이 자기 자신 제외한 N-1 일 경우 함수 종료
				if (findCount == N - 1) {
					return;
				}
			}
		}
		// k를 하나 늘리고 함수 반복
		findFriend(k+1);
	}
}

```

## BOJ6087 레이저 통신
### 🎈 해결방법 :
1. 한 칸씩 이동해서 완전탐색으로 풂 -> 당연히 시간 초과
2. 이동하면서 방향 꺾을 때마다 거울 개수 +1

### 💬 코멘트 :
실패한 코드지만 가진게 없어 이거라도 봐주십쇼

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6087 {
	static char[][] map;
	// C의 위치를 저장할 배열
	static int[][] placeC;
	// 상 좌 하 우
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		placeC = new int[2][2];
		for (int i = 0, idx = 0; i < map.length; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < map[0].length; j++) {
				// C 찾으면 placeC 배열에 저장
				if (map[i][j] == 'C') {
					placeC[idx][0] = i;
					placeC[idx++][1] = j;
				}
			}
		}
		
		// 도착 지점의 C를 최대값으로 설정
		map[placeC[1][0]][placeC[1][1]] = Character.MAX_VALUE;
		// 출발 시 각 방향에 대해서 반복
		for (int i = 0; i < dr.length; i++) {
			// 출발 지점에서 해당 방향으로 함수 실행
			fill(placeC[0][0], placeC[0][1], i, 0);
		}
		// 도착 지점 C 위치의 값을 char -> int 해서 출력
		System.out.println(map[placeC[1][0]][placeC[1][1]] - 48);
		
	}
	
	static void fill(int r, int c, int di, int mirror) {
		// di 방향으로 한 칸 이동
		r += dr[di];
		c += dc[di];
		// 이동할 곳이 경계 조건을 만족하면서 벽이 아니면 그 방향으로 계속 반복
		while(r >= 0 && r < map.length && c >= 0 && c < map[0].length && map[r][c] != '*') {
			// 이동할 곳이 . 이거나 mirror보다 작은 수면
			if (map[r][c] == '.' || map[r][c] > mirror + 48) {
				// 도착 지점의 C에 도달했는지
				if (r == placeC[1][0] && c == placeC[1][1]) {
					// 도달했으면 mirror가 더 작으면 mirror 대입
					if (map[placeC[1][0]][placeC[1][1]] >= mirror + 48) {
						map[placeC[1][0]][placeC[1][1]] = (char) (mirror + 48);
					}
					// 함수 종료
					return;
				}
				// 대입
				map[r][c] = (char) (mirror + 48);
				// 방향 꺾어서 mirror+1 해서 재귀
				fill(r, c, (di+1)%4, mirror+1);
				fill(r, c, (di+3)%4, mirror+1);
			}
			// di 방향으로 한 칸 이동
			r += dr[di];
			c += dc[di];
		}
	}
}
```