# Week6
## BOJ2011알고 푸는 알고리즘
### 🎈 해결방법 :
<!-- 해결 방법 -->
1. 한글자만 읽을 수 있는 상황
2. 두글자만 읽을 수 있는 상황
3. 모두 가능한 상황
을 나눠서 dp로 저장해가며 품~
### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->
와인문제
계단문제
비슷한듯
### 📄 코드
```java
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2011 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] line = br.readLine().split("");
		int len = line.length;

		int[] cho1 = new int[len];
		int[] cho2 = new int[len];

		int[] arr = new int[len];

		boolean flag = true;
		//암호가 없을 때 에러
		if (len==0) {
			flag=false;
		}
		for (int i = 0; i < len; i++) {
			arr[i] = Integer.parseInt(line[i]);
			//첫 단어부터 0이면 에러
			if(i==0&&arr[i]==0) {
				flag = false;
			}
			//그 이후 앞 단어가 1또는2가 아닌데 0일 경우 에러 
			else if (i>0&&!(arr[i-1]==1 || arr[i-1]==2)&&arr[i] == 0) {
				flag = false;
			}

		}

		// 1개 선택 : 앞 인덱스 두개 더하기
		// 2개 선택 : 앞 앞 인덱스 두개 더하기
		if (flag) {
			if (len == 1) {
				System.out.println(1);
			} else if (len == 2) {
				System.out.println(possible1(arr[1]) + possible2(arr[0], arr[1]));
			} else {
				cho1[0] = 1;
				cho1[1] = possible1(arr[1]);
				cho2[0] = 0;
				cho2[1] = possible2(arr[0], arr[1]);

				for (int i = 2; i < len; i++) {
					cho1[i] = ((cho1[i - 1] + cho2[i - 1]))*possible1(arr[i])% 1000000;
					cho2[i] = ((cho1[i - 2] + cho2[i - 2])*possible2(arr[i - 1], arr[i]) )% 1000000;
				}
				
				System.out.println((cho1[len - 1] + cho2[len - 1])% 1000000);
			}

			

		} else {
			System.out.println(0);
		}
	}

	
	// 1개 선택 가능한지 확인하는 메서드
	static int possible1(int n) {
		if (n!=0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	// 2개 선택 가능한지 확인하는 메서드
	static int possible2(int l, int r) {
		
		if (l!=0 && l * 10 + r <= 26) {
			return 1;
		} else {
			return 0;
		}
	}
}

```

## BOJ1922 알고푸는 알고리즘
### 🎈 해결방법 :
<!-- 해결 방법 -->
다를 때마다 네분할
같으면 숫자 출력
숫자가 다를 때마다 "("추가
### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->

### 📄 코드
```java
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1992 {
	static int[][] video;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		video = new int[n][n];
		for (int i = 0; i < n; i++) {
			String[] line = br.readLine().split("");
			for (int j = 0; j < n; j++) {
				video[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		
		quad(0, n, 0, n);

	}
	
	static boolean check(int rStart, int rEnd, int cStart, int cEnd) {
		
		
		int n = video[rStart][cStart];
		
		for (int i = rStart; i < rEnd; i++) {
			for (int j = cStart; j < cEnd; j++) {
				
				int k = video[i][j];
				if (n != k) {
					
					return false;
						
					}

				}
			}
		
		return true;
		}
		
	
	
	static void quad(int rStart, int rEnd, int cStart, int cEnd) {
		
		int rMid = (rStart + rEnd) / 2;
		int cMid = (cStart + cEnd) / 2;
		
		if(check(rStart, rEnd, cStart, cEnd)) {
			System.out.print(video[rStart][cStart]);
			
		}
		
		else {
			System.out.print("(");
			quad(rStart, rMid, cStart, cMid);
			quad(rStart, rMid, cMid, cEnd);
			quad(rMid, rEnd, cStart, cMid);
			quad(rMid, rEnd, cMid, cEnd);
			System.out.print(")");
		}
		

	}
}

```

... 문제 수 만큼 작성 ...