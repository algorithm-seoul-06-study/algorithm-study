# Week + 주차
## BOJ1182 알고 푸는 알고리즘
### 🎈 해결방법 :
<!-- 해결 방법 -->
부분집합 수업듣고 고대로 품
### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->
비트마스킹으로 품
부분집합의 모든 경우의 수를 다 저장해서
그중에 s가 걸리면 cnt++
### 📄 코드
```java
import java.util.Arrays;
import java.util.Scanner;

public class BOJ1182 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int s = sc.nextInt();

		int[] arr = new int[n];
		int[] nArr = new int[1 << n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		//비트마스킹~
		for (int i = 1; i < (1 << n); i++) {
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) > 0) {
					nArr[i-1] += arr[j];
				}

			}
		}

		
		int cnt = 0;
		for (int i = 0; i < (1 << n)-1; i++) {
			if (nArr[i] == s) {
				cnt++;
			}
		}

		System.out.println(cnt);
	}
}

```

## BOJ1932 알고 푸는 알고리즘
### 🎈 해결방법 :
<!-- 해결 방법 -->
더하기~~
### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->
껌이쥐
### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1932 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[][] tri = new int[n][n];

		for (int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j <= i; j++) {

				tri[i][j] = Integer.parseInt(line[j]);

			}
		}
		int max = -1;
		//트리 깊이가 2이상일 때
		if (n > 1) {
			//일단 뎁스2의 노드에는 값이 정해져 있으니 저장
			tri[1][0] += tri[0][0];
			tri[1][1] += tri[0][0];
			
			//그 다음부터는 그냥 다음 뎁스의 i인덱와 i-1인덱스 중에 큰 거를 더함
			for (int i = 2; i < n; i++) {
				for (int j = 0; j <= i; j++) {
					if (j == 0) {
						tri[i][j] += tri[i - 1][j];
					} else {
						tri[i][j] += Math.max(tri[i - 1][j], tri[i - 1][j - 1]);
					}
				}

			}
			//마지막 노드들에서 가장 큰거 찾음
			for (int i = 0; i < n; i++) {

				if (max < tri[n - 1][i]) {
					max = tri[n - 1][i];
				}
			}

		} 
		//트리 깊이가 1일때
		else {
			max = tri[0][0];
		}
		System.out.println(max);
	}
}
```

... 문제 수 만큼 작성 ...
