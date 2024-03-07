# Week5
## BOJ1992 쿼드트리
### 🎈 해결방법 :
1. 기존 N x N 배열에서 압축한 결과를 (N/2) x (N/2) 부분에 덮어쓰기
2. 좌상, 우상, 좌하, 우하 모두 같은 경우와 아닌 경우로 구분
### 💬 코멘트 :
반례 잡느라 고생함

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1992 {
	static String[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new String[N][N];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().split("");
		}
		
		quadTree(N);
		
		// 최종 결과는 arr[0][0]에 저장됨
		System.out.println(arr[0][0]);
	}
	
	static void quadTree(int N) {
		// 한 변의 길이가 1이면 종료
		if (N == 1)
			return;
		
		// (N/2) * (N/2) 부분에 다시 채우기
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < N / 2; j++) {
				// 좌상, 우상, 좌하, 우하 다 같으면 하나로 압축
				if ((arr[2*i][2*j].equals("0") || arr[2*i][2*j].equals("1")) && arr[2*i][2*j].equals(arr[2*i][2*j+1]) && arr[2*i][2*j].equals(arr[2*i+1][2*j]) && arr[2*i][2*j].equals(arr[2*i+1][2*j+1]))
					arr[i][j] = arr[2*i][2*j];
				// 아니면 괄호 붙여서 문자열로 표현
				else
					arr[i][j] = "(" + arr[2*i][2*j] + arr[2*i][2*j+1] + arr[2*i+1][2*j] + arr[2*i+1][2*j+1] + ")"; 
				
			}
		}
		// N -> N/2 로 재귀 구현
		quadTree(N/2);
	}
}
```

## BOJ1790 수 이어 쓰기 2
### 🎈 해결방법 :
1. N의 자리수를 활용해 일차적으로 접근
2. k번째 위치에 있는 수의 자리수를 찾으면 타겟 넘버 찾기
3. 타겟 넘버의 몇번째 숫자인지 구하기

### 💬 코멘트 :
리팩토링 많이함

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1790 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		// N의 자리수
		int l = Integer.toString(N).length();
		
		check(N, k, l);
	}
	
	static void check(int N, int k, int l) {
		// 한자리부터 N의 자리수만큼 반복
		for (int i = 1; i <= l; i++) {
			// 해당 자리수의 총 문자 개수
			int tmp = (int) (i * Math.pow(10, i-1) * 9);
			// k > tmp 이면 k -= tmp 진행
			if (k > tmp) {
				k -= tmp;
			// k <= tmp 이면 해당 자리수 중에 타겟 넘버가 있다는 뜻
			} else {
				// 몫 일정하게 뽑아내고 나머지 0 ~ (i-1) 로 뽑아내려고 k-1 로 계산
				int q = (k - 1) / i;
				int r = (k - 1) % i;
				// tmp 는 타겟 넘버
				tmp = (int) (Math.pow(10, i-1) + q);
				// N >= tmp 이면 타겟넘버의 r번째 숫자 출력
				if (N >= tmp) {
					System.out.println(Integer.toString(tmp).charAt(r));
					return;
				}
			}
		}
		// N < tmp 이면 k번째 자리 숫자가 없는 경우라 -1 출력
		System.out.println(-1);
	}
}
```

## BOJ2011 암호코드
### 🎈 해결방법 :
1. Bottom-Up 방식 사용
2. 두번째 숫자부터 규칙 적용되도록 dp[]의 길이를 arr.length + 1 로 설정하고 dp[0] = 1 을 넣음
3. 11 ~ 19, 21 ~ 26 이면 dp[i] = dp[i-1] + dp[i-2]
4. 1과 2 다음에 0 나오면 dp[i] = dp[i-2] / 1이나 2가 아닌 수 다음에 0이 나오면 잘못된 암호
5. 보통의 한자리 숫자일 경우 dp[i] = dp[i-1]
6. dp[arr.length] 출력

### 💬 코멘트 :
조합으로 접근했다가 안되는걸 깨닫고 DP로 접근하는데 시간이 좀 걸림  
Top-Down 방식으로 풀다가 시간 터져서 Bottom-Up 방식으로 바꿈  
0 때문에 많이 고생함  

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2011 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] arr = br.readLine().toCharArray();
		// dp[0] = 1 처리 하려고 길이 하나 더 늘림
		int[] dp = new int[arr.length + 1];
		
		// 시작 숫자가 0이 아닐 때 실행
		if (arr[0] != '0') {
			// 초기값 설정
			dp[0] = 1;
			dp[1] = 1;
			for (int i = 2; i <= arr.length; i++) {
				// 11 ~ 19, 21 ~ 26 이면 한자리와 두자리 모두 적용되므로 전 값과 전전 값을 합침
				if ((arr[i-2] == '1' && arr[i-1] >= '1' && arr[i-1] <= '9') || (arr[i-2] == '2' && arr[i-1] >= '1' && arr[i-1] <= '6'))
					dp[i] = dp[i-1] + dp[i-2];
				// 1과 2가 아닌 수 다음에 0이오는 경우 잘못된 암호라 for문 바로 빠져나옴
				else if ((arr[i-2] != '1' && arr[i-2] != '2') && arr[i-1] == '0')
					break;
				// 1과 2 다음에 0 나오면 전전 값으로 돌아감
				else if (arr[i-1] == '0')
					dp[i] = dp[i-2];
				// 보통의 한자리 숫자일 경우 전 값과 같음
				else
					dp[i] = dp[i-1];
				
				if (dp[i] >= 1000000)
					dp[i] %= 1000000;
			}
		}
		
		// 잘못된 암호면 dp[arr.length]에는 초기값인 0이 출력됨
		System.out.println(dp[arr.length]);
	}
}
```