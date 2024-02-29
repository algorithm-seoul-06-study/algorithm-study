# Week5
## BOJ1182 부분수열의 합
### 🎈 해결방법 :
1. 원소의 개수를 늘려가며 재귀함수 실행
2. for문을 절반만 돌도록 (totalSum - sum) 활용
### 💬 코멘트 :
이거 풀 때가 행복했지...

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1182 {
	static int[] arr;
	static int totalSum;
	static int result;
	static int N;
	static int S;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		// 배열 입력받음과 동시에 최대합 totalSum 구하기
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			totalSum += arr[i];
		}
		
		// 부분수열이 원래 수열과 같을 때 먼저 비교
		if (totalSum == S) {
			result++;
		}
		
		// N이 1이 아닐 때 check() 함수 실행
		if (N > 1) {
			check(1, 0, 0);			
		}
		
		System.out.println(result);
	}
	
	// a : 부분수열의 원소의 개수
	// b : 시작 index
	// sum : 중간합
	static void check(int a, int b, int sum) {
		for (int i = b; i < arr.length; i++) {
			// 중간합에 arr[i] 더하기
			sum += arr[i];
			// 중간합이 S와 같으면 result++;
			if (sum == S) {
				result++;
			}
			// N이 짝수이면서 원소의 개수가 N/2 일 경우를 제외하고 (최대합 - 중간함)이 S와 같으면 result++;
			if (!(N % 2 == 0 && a == N / 2) && totalSum - sum == S) {
				result++;
			}
			// a < N/2 까지 원소의 개수 하나 늘리고 b는 다음 원소부터 시작하는걸로 반복
			if (a < N / 2) {
				check(a + 1, i + 1, sum);				
			}
			// 중간합 되돌리기
			sum -= arr[i];
		}
	}
```

## BOJ1932 정수삼각형
### 🎈 해결방법 :
1. DP bottom-up 방식 사용
2. 한 줄 입력 받을 때마다 바로 작업
3. 왼쪽 변과 오른쪽 변에 있는 요소는 바로 윗부모의 것만 받음
4. 가운데 있는 요소는 왼쪽 부모, 오른쪽 부모 중 큰 값을 받음
5. 맨아래 줄에 도착했을 때 sumMax < arr[i][j] 이면 대입

### 💬 코멘트 :
처음에 트리로 접근했다가 고난과 역경을 겪음

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1932 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][n];
		int sumMax = -1;
		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				// 위에서 2번째 줄부터
				if (i >= 1) {
					// 왼쪽 변에 있는 요소는 바로 위에 것만 받음
					if (j == 0) {
						arr[i][j] += arr[i-1][j];
					// 오른쪽 변에 있는 요소는 바로 위에 것만 받음
					} else if (j == i) {
						arr[i][j] += arr[i-1][j-1];
					// 가운데 있는 요소는 왼쪽 부모, 오른쪽 부모 중 큰 값 받음
					} else {
						arr[i][j] += Math.max(arr[i-1][j], arr[i-1][j-1]);
					}
					// 맨아래 줄에 도착했을 때 sumMax < arr[i][j] 이면 대입
					if (i == arr.length - 1 && sumMax < arr[i][j]) {
						sumMax = arr[i][j];
					}
				}
			}
		}
		System.out.println(sumMax);
	}
}
```

## BOJ1987 알파벳
### 🎈 해결방법 :
1. 밟은 칸에 해당하는 알파벳을 target 배열에 저장 후 targetSize로 크기와 index 조절하면서 사용
2. 4방탐색 후 target에 걸리지 않으면 이동
3. 칸 밟을 때마다 cnt 늘려가면서 칸 수 카운트
4. cntMax < cnt 이면 대입

### 💬 코멘트 :
처음에 밟은 칸의 알파벳에 해당하는 칸들을 모두 0 으로 바꿔서 했는데  
바뀐게 유지되고 이전 상태로 제대로 돌아오지 않아서 폐기

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1987 {
	static char[][] arr;
	static int cntMax;
	static char[] target;
	static int targetSize;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean flag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		// 이미 밟아서 더이상 갈 수 없는 알파벳 저장하는 배열 target
		target = new char[26];
		// target 배열의 크기와 index를 조절하기 위한 변수 targetSize
		targetSize = 0;
		for (int r = 0; r < arr.length; r++) {
			String str = br.readLine();
			for (int c = 0; c < arr[0].length; c++) {
				arr[r][c] = str.charAt(c);
			}
		}
		
		check(0, 0, 1);
		System.out.println(cntMax);
		
	}
	
	static void move(int r, int c, int cnt) {
		// 4방탐색
		for (int i = 0; i < dc.length; i++) {
			// 배열의 경계 조건
			if (r + dr[i] >= 0 && r + dr[i] < arr.length && c + dc[i] >= 0 && c + dc[i] < arr[0].length) { 
				// 갈 수 있는 곳인지 확인하는 boolean 변수
				flag = true;
				// 이동하려는 곳이 target에 있는지 확인
				for (int j = 0; j < targetSize; j++) {
					// 있으면 flag를 false로 바꿔서 이동하지 않음
					if (arr[r+dr[i]][c+dc[i]] == target[j]) {
						flag = false;
						break;
					}
				}
			// target에 하나도 걸리지 않았다면 그 곳으로 이동 후 check() 함수 실행
			if (flag) 		
				check(r + dr[i], c + dc[i], cnt + 1);
			
			}
		}
		// cntMax < cnt 이면 대입
		if (cntMax < cnt) 
			cntMax = cnt;
		
	}
	
	// 밟은 칸을 target에 저장하는 check() 함수
	// r, c : 현재 위치
	// cnt : 칸 수
	static void check(int r, int c, int cnt) {
		// 밟은 칸 target에 저장하고 targetSize++
		target[targetSize++] = arr[r][c];
		// 이동하는 함수
		move(r, c, cnt);
		// 되돌리기
		targetSize--;
	}
}
```

## BOJ9251 LCS
### 🎈 해결방법 :
1. 규칙 찾고 점화식 세우기 -> 실패
2. 아래 링크로 검색
3. 점화식에 맞게 코딩하기

![img](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbspN8D%2FbtqH8cSDh29%2Fh072G6U0Myk7E7BzErb2f0%2Fimg.png)  

* 점화식  
LCS(Xi, Yj) : LCS(Xi-1, Yj-1) + 1                                  if (Xi == Yj)    
LCS(Xi, Yj) : max( LCS(Xi-1, Yj), LCS(Xi, Yj-1) )         if (Xi != Yj)

### 💬 코멘트 :
도저히 못풀겠어서 참지 못하고 검색했읍니다  
https://st-lab.tistory.com/139  

근데 왜 top-down 방식에서 int 배열 말고 integer 배열을 사용해야 하는지 모르겠음  
int 배열 사용하면 시간 터짐

### 📄 top-down 방식 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9251_td {
	static char[] c1;
	static char[] c2;
	static Integer[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		c1 = br.readLine().toCharArray();
		c2 = br.readLine().toCharArray();
		dp = new Integer[c1.length][c2.length];
		
		System.out.println(lcs(c1.length - 1, c2.length - 1));
		
	}
	
	static int lcs(int x, int y) {
		// index 범위 벗어나면 0 반환
		if (x == -1 || y == -1) {
			return 0;
		}
		// dp[x][y]가 null일 때만 실행
		if (dp[x][y] == null) {
			// 값이 같으면 대각선 왼쪽 위의 값 + 1
			if (c1[x] == c2[y]) {
				dp[x][y] = lcs(x - 1, y - 1) + 1;
			// 값이 다르면 왼쪽이나 위 중 큰 거 가져오기
			} else {
				dp[x][y] = Math.max(lcs(x - 1, y), lcs(x, y - 1));
			}
		}
		// dp[x][y] 반환
		return dp[x][y];
	}
}
```

### 📄 bottom-up 방식 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9251_LCS_bu {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] c1 = br.readLine().toCharArray();
		char[] c2 = br.readLine().toCharArray();
		int[][] dp = new int[c1.length+1][c2.length+1];
		
		for (int i = 1; i <= c1.length; i++) {
			for (int j = 1; j <= c2.length; j++) {
				// 값이 같으면 대각선 왼쪽 위의 값 + 1
				if (c1[i-1] == c2[j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
				// 값이 다르면 왼쪽이나 위 중 큰 거 가져오기
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		System.out.println(dp[c1.length][c2.length]);
		
	}
}
```