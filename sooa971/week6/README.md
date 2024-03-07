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


## BOJ1790 알고푸는 알고리즘
### 🎈 해결방법 :
<!-- 해결 방법 -->
prefixSum
### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->
화가납니다 화가나
of course 제 스스로에게요
### 📄 코드
```java
package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ1790 {
	public static void main(String[] args) {
		long[] prefixSum = new long[11];
		long[] sum = new long[11];
		sum[10] = 1;

		for (int i = 1; i < 10; i++) {
			sum[i] = (long) ((long) 9 * Math.pow(10, i - 1));
			prefixSum[i] = sum[i] * i + prefixSum[i - 1];
		}
		prefixSum[10] = prefixSum[9] + 10;
//		System.out.println(Arrays.toString(sum));
//		System.out.println(Arrays.toString(prefixSum));

		Scanner sc = new Scanner(System.in);

		long n = sc.nextLong();
		long k = sc.nextLong();

		// n이 1500이면 1-9 / 10-99 / 100-999 / 1000-1500
		// n이 총 몇자리인지sum[1]*1 sum[2]*2 ,,, 몫이 0일 때는 수-(Math.pow(10,i-1)+1)*i
		// 몫이 0이 될 때 갯수는 그 수 - 해당 인덱스 10 pow한 거

		long nlen = 0;

		long n2 = n;
		for (int i = 1; i < 11; i++) {
			if (n2 / 10 != 0) {
				n2 /= 10;
				nlen += sum[i] * i;
			} else {
				nlen += ((n - Math.pow(10, i - 1)) + 1) * i;
				break;
			}
		}



		int idx = 0;

		if (k > nlen) {
			System.out.println("-1");
		} else {
			for (int i = 1; i < 11; i++) {
				if (k <= prefixSum[i] && k > prefixSum[i - 1]) {
					idx = i;
					break;
				}
			}


			// 몇자리 수에 범위에 걸쳐있는지가 idx-1
			
			// 몇자리 수에 걸쳐있는지 확인 후 그 prefixSum[idx] 부터 수를 세기 위해서 
			// idx부터 몇번째 수인지 센다
			//1.해당 idx에서 몇번째인지 kk에 저장
			long kk = k - prefixSum[idx - 1];
			//2.idx-1이 몇자리 수인지 나타내기 때문에 kk를 idx-1로 나누면 해당 해당 자리수의 몇번째 숫자인지 알 수 있다. 즉 그 숫자 마지막자리로 부터 나머지 만큼 이동 하면됨
			long kres = (long) ((kk / idx) + Math.pow(10, idx - 1));
			//3.나머지 저장
			long kmod = kk % idx;
			//4.나머지가 0이면 그냥 바로 제일 끝 수 출력
			int ans = 0;
//			System.out.println(kk);
//			System.out.println(idx);
//			System.out.println(kres);
//			System.out.println(kmod);
			if (kmod == 0) {
				
				ans = (int) (kres-1) % 10;
			} else {
				//5. 나머지가 0이 아니면 다음 숫자로 넘어가서 이동후  출력
				ans = (int) ((kres) / (Math.pow(10, idx- kmod))) % 10;

			}

			System.out.println(ans);

		}
	}
}
```