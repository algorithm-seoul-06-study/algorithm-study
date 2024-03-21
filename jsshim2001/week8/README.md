# Week8
## BOJ2636 치즈
### 🎈 해결방법 :
<!-- 해결 방법 -->
1. 치즈 그대로 받는 arr와 그 반전인 visit 2차원 배열 생성
2. 가장자리는 무조건 0이므로 0,0부터 탐색
3. visit을 통해 탐색하여 겉부분을 모두 false로 바꿈 & 끝나면 visit Update
4. 치즈의 개수를 매번 세어 0일 때 break

### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->
1. 다른 방법 시도 (일종의 squeeze하여 island에 대한 고민 해결, but 구덩이 같은 구조는 해결 못함) -> 그래서 아예 방법을 바꿈
2. 생각보다 할만했음

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2636 {
	static int n;
	static int k;
	static boolean[][] arr;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]); // 행
		k = Integer.parseInt(str[1]); // 열
		arr = new boolean[n][k]; // 비교할 2차원 배열
		visit = new boolean[n][k]; // 탐색할 2차원 배열
		int cnt=0; // 얼마나 걸렸는지
		
		for (int i = 0; i < n; i++) { // 비교할 2차원 배열의 반전 = 탐색할 2차원 배열 
			// As 탐색할 곳은 치즈가 아닌 곳, 비교할 곳은 치즈인 곳 (사방탐색)
			String[] strarr = br.readLine().split(" ");
			for (int j = 0; j < k; j++) {
				if (strarr[j].equals("1")) {
					arr[i][j] = true;
					visit[i][j] = false;
				} else {
					visit[i][j] = true;
				}

			}
		}
		
		// 한시간 후 없어질 것을 고려하여 countingCheese()로 초기화
		int result=countingCheese();
		while(true) {
			findBoundary(0, 0); // 0,0은 무조건 false인 부분 (치즈 기준)
			cnt++; // visit 함수 update -> 탐색 과정에서 다 바뀌었기 때문
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < k; j++) {
					if (arr[i][j]) {
						visit[i][j]=false;
					} else {
						visit[i][j]=true;

					}
				}
			}
			
			if(countingCheese()==0) { // cheese 숫자 세기
				break;
			} else {
				result=countingCheese();
			}
			
		}
		System.out.println(cnt);
		System.out.println(result);
	}

	static void findBoundary(int startR, int startC) { // 시작하는 좌표 startR, startC (사실상 0,0임)
		Queue<Integer[]> queue = new LinkedList<>();
		queue.add(new Integer[] { startR, startC });

		while (!queue.isEmpty()) { // 탐색 진행
			Integer[] current = queue.poll();
			int r = current[0];
			int c = current[1];
			if (!(0 <= r && r < n && 0 <= c && c < k) || !visit[r][c]) { // 배열 벗어난 곳이나, 이미 visit한 곳이면 가지 않음
				continue;
			}
			
			// 사방탐색을 하여 치즈인 부분을 false로 만듦 -> 겉부분만 바뀜 Island 내부는 탐색 진행 x
			if (0 < r && arr[r - 1][c]) {
				arr[r - 1][c] = false;
			}
			if (r < n - 1 && arr[r + 1][c]) {
				arr[r + 1][c] = false;
			}
			if (0 < c && arr[r][c - 1]) {
				arr[r][c - 1] = false;
			}
			if (c < k - 1 && arr[r][c + 1]) {
				arr[r][c + 1] = false;
			}
			
			// 현 좌표 false로 바꿈
			visit[r][c] = false;

			// 상화좌우 탐색
			queue.add(new Integer[] { r + 1, c });
			queue.add(new Integer[] { r - 1, c });
			queue.add(new Integer[] { r, c + 1 });
			queue.add(new Integer[] { r, c - 1 });

		}
	}

	static int countingCheese() { // Cheese 개수 세는 Method
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < k; j++) {
				if (arr[i][j]) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}

```

## BOJ5904 MOO 게임
### 🎈 해결방법 :
<!-- 해결 방법 -->
1. 각 단계별 글자의 개수를 구함, 또한 가운데 MOOO 문자 개수 역시 p로 배열화
2. while(True)로  배열과 비교함 ex) 11인 경우 arr에서 11일 때 걸리므로 그 전 배열인 3을 빼줌, 그 후 남는 값이 8, 이때 배열 p와 비교하여 p보다 크면 p를 빼줌 p보다 작으면 첫번째는 m 나머지는 o
2. 모두 없어질 때까지 반복

### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->
1. 복잡하지만 금방 배열만 잘 짜면 금방 풀 수 있음
2. out: break 유용하게 이용하면 좋은듯함

### 📄 코드
```java
import java.util.Arrays;
import java.util.Scanner;

public class BOJ5904 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[29];
		arr[0] = 3;
		int[] p = new int[29]; // 이 뒤는 10^9이 넘어감
		for (int i = 0; i < 28; i++) { // 수열의 크기를 할당
			arr[i + 1] = 2 * arr[i] + (i + 4);
			p[i]=i+4; // p는 가운데 MOOOOO의 길이
		}
		
		out: while(true) {
			for (int i = 1; i < 28; i++) {
				if (n < arr[i]) { // 그 수열보다 큰 순간이 나온다면 확인
					if(n>arr[i-1]) { // 크면 빼줌
						n-=arr[i-1];
					}
					if(n<=p[i-1]) { // 그 값이 가운데 MOOO의 길이보다 작으면 out:break
						if(n==1) {
							System.out.println("m");
						} 
						else {
							System.out.println("o");
						}
						break out;
					} else { // 그게 아니라면 빼줌
						n-=p[i-1];
						
						break;
					}
				}
			}
		}
		

	}

}

```
... 문제 수 만큼 작성 ...