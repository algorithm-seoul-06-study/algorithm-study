# Week + 주차
## BOJ1018 체스판 다시 칠하기
### 🎈 해결방법 :
Black으로 시작하는 8x8 체스판 제작  
White로 시작하는 8x8 체스판 제작  
세팅해놓은 체스판으로 돌면서 가장 적은 수 찾기  

### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1018 {
	public static void main(String[] args) throws IOException {
        // 입력값 세팅
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		char[][] arr = new char[m][n];
		for (int r = 0; r < m; r++) {
			String str = br.readLine();
			for (int c = 0; c < n; c++) {
				arr[r][c] = str.charAt(c);
			}
		}
		
        // Black 체스판 && White 체스판 제작
		char[][] bChess = new char[8][8];
		char[][] wChess = new char[8][8];
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if ((r + c) % 2 == 0) {
					bChess[r][c] = 'B';
					wChess[r][c] = 'W';
				} else {
					bChess[r][c] = 'W';
					wChess[r][c] = 'B';
				}
			}
		}
		
        // 체스판 다 대보면서 최소값 찾기
		int min = Integer.MAX_VALUE;
		int bCnt = 0;
		int wCnt = 0;
		outer :
		for (int i = 0; i < m - 8 + 1; i++) {
			for (int j = 0; j < n - 8 + 1; j++) {
				for (int r = 0; r < 8; r++) {
					for (int c = 0; c < 8; c++) {
						if (arr[i+r][j+c] != bChess[r][c]) bCnt++;
						if (arr[i+r][j+c] != wChess[r][c]) wCnt++;
					}
				}
			if (min > Math.min(wCnt, bCnt)) {
				min = Math.min(wCnt, bCnt);
			}
			if (min == 0) {
				break outer;
			}
			bCnt = 0;
			wCnt = 0;
			}
		}
		System.out.println(min);
	}
}

```


## BOJ1107 리모컨
### 🎈 해결방법 :
N과 M을 String으로 변경해서 N에 M이 하나도 포함되지 않은 수를 순차적으로 탐색  
많은 예외 상황들 하나하나 찾아가며 구현  

### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1107 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		String str = null;
		if (M != 0) {
			str = br.readLine().replace(" ", "");			
		}
		
		int result = 0;
		if (N == 100) {
		} else if (Integer.toString(N).length() >= Math.abs(N - 100) || M == 10) {
			result = Math.abs(N - 100);
		} else if (M == 0) {
			result = Integer.toString(N).length();
		} else {
			int lResult = Integer.MAX_VALUE, hResult = Integer.MAX_VALUE;
			outer :
				for (int i = N; i <= 1000000; i++) {
					for (int j = 0; j < M; j++) {
						if (Integer.toString(i).contains(str.substring(j, j+1))) {
							break;
						}
						if (j == M - 1) {
							// System.out.println(i);
							hResult = Integer.toString(i).length() + (i - N);
							if (hResult > Math.abs(N - 100)) {
								hResult = Math.abs(N - 100);
							}
							break outer;
						}
					}
				}
			outer :
				for (int i = N; i >= 0; i--) {
					for (int j = 0; j < M; j++) {
						if (Integer.toString(i).contains(str.substring(j, j+1))) {
							break;
						}
						if (j == M - 1) {
							// System.out.println(i);
							lResult = Integer.toString(i).length() + (N - i);
							if (lResult > Math.abs(N - 100)) {
								lResult = Math.abs(N - 100);
							}
							break outer;
						}
					}
				}
			// System.out.println("l = " + lResult);
			// System.out.println("h = " + hResult);
			result = Math.min(lResult, hResult);
		}
		System.out.println(result);
	}
}
```


## BOJ2156 포도주 시식
### 🎈 해결방법 :
해당 번호의 포도주를 마시지 않았을 때의 최대값을 계속 찾는 방식으로 진행  

### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2156 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
        
		int[] exc = new int[n];
		if (n == 1) {
			System.out.println(arr[0]);
		} else if (n == 2) {
			System.out.println(arr[0] + arr[1]);
		} else if (n == 3) {
			System.out.println(arr[0] + arr[1] + arr[2] - Math.min(Math.min(arr[0], arr[1]), arr[2]));
		} else if (n >= 4) {
			exc[0] = 0;
			exc[1] = arr[0];
			exc[2] = arr[0] + arr[1];			
			for (int i = 3; i < exc.length; i++) {
				exc[i] = Math.max(Math.max(exc[i-1], arr[i-1] + exc[i-2]), arr[i-1] + arr[i-2] + exc[i-3]);
			}			
			System.out.println(Math.max(Math.max(exc[n-1], arr[n-1] + exc[n-2]), arr[n-1] + arr[n-2] + exc[n-3]));
		}
		
	}
}
```


## BOJ17144 미세먼지 안녕!
### 🎈 해결방법 :
3차원 배열 사용  
확산된 먼지를 다음 배열에 저장  
공기청정기 윗부분 작동  
공기청정기 아랫부분 작동  

### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17144 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[][][] arr = new int[T+1][R][C];
		int[] ac = new int[2];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				arr[0][r][c] = Integer.parseInt(st.nextToken());
				if (arr[0][r][c] == -1) {
					if (ac[0] == 0) {
						ac[0] = r;
					} else {
						ac[1] = r;
					}
				}
			}
		}
		
		// 상 하 좌 우
		int[] dr1 = {-1, 1, 0, 0};
		int[] dc1 = {0, 0, -1, 1};
		
		// 상 우 하 좌
		int[] dr2 = {-1, 0, 1, 0};
		int[] dc2 = {0, 1, 0, -1};
		
		// 하 우 상 좌
		int[] dr3 = {1, 0, -1, 0};
		int[] dc3 = {0, 1, 0, -1};
		
		for (int i = 0; i < T; i++) {
			// 먼지 확산
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					arr[i+1][r][c] += arr[i][r][c];
					if (arr[i][r][c] >= 5) {
						for (int j = 0; j < dc1.length; j++) {
							if (r + dr1[j] >= 0 && r + dr1[j] < R && c + dc1[j] >= 0 && c + dc1[j] < C && arr[i][r+dr1[j]][c+dc1[j]] != -1) {
								arr[i+1][r+dr1[j]][c+dc1[j]] += arr[i][r][c] / 5;
								arr[i+1][r][c] -= arr[i][r][c] / 5;
							}
						}
					}
				}
			}
			
			// 공기청정기 작동
			// 윗부분
			int r = ac[0] - 1;
			int c = 0;
			int j = 0;
			// arr[i+1][--r][c] = 0;
			while (arr[i+1][r][c] != -1) {
				if (r + dr2[j] < 0 || r + dr2[j] > ac[0] || c + dc2[j] < 0 || c + dc2[j] >= C) {
					j = (j + 1) % 4;
				}
				arr[i+1][r][c] = arr[i+1][r+dr2[j]][c+dc2[j]];
				r += dr2[j];
				c += dc2[j];
				
				if (j == 3 && arr[i+1][r+dr2[j]][c+dc2[j]] == -1) {
					arr[i+1][r][c] = 0;
					break;
				}
			}
			
			// 아랫부분
			r = ac[1] + 1;
			c = 0;
			j = 0;
			while (arr[i+1][r][c] != -1) {
				if (r + dr3[j] < ac[1] || r + dr3[j] >= R || c + dc3[j] < 0 || c + dc3[j] >= C) {
					j = (j + 1) % 4;
				}
				arr[i+1][r][c] = arr[i+1][r+dr3[j]][c+dc3[j]];
				r += dr3[j];
				c += dc3[j];
				
				if (j == 3 && arr[i+1][r+dr3[j]][c+dc3[j]] == -1) {
					arr[i+1][r][c] = 0;
					break;
				}	
			}
		}
		
		int sum = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				sum += arr[T][r][c];
			}
		}
		System.out.println(sum + 2);
	}
}
```

