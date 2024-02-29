# Week4
## BOJ1182 부분수열의 합
### 🎈 해결방법 :
<!-- 해결 방법 -->
1. 부분 수열 구함
2. 그 합이 특정 수일 때 cnt 올려줌
3. 시작 값이 0이라, 한번 더 처리

### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->
1. 부분 수열 배워서 EZ 했음
2. 그래도 하는 방법 여러개 알아둬야할듯

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
		for(int i=0;i<n;i++) {
			arr[i]=sc.nextInt();
		}

		int cnt =0;
		
		for (int i = 0; i < (1 << n); i++) { // 비트마스킹으로 부분수열 구하기
			int sum=0;
			for (int j = 0; j < n; j++) { 
				if ((i & (1 << j)) > 0) {
					sum+=arr[j];
				}
			}
			if(sum==s) {
				cnt++;
			}
		}
		if(s==0) { // 시작 값이 0이라 처리 한 번 해줌
			System.out.println(cnt-1);
		} else {
			System.out.println(cnt);
		}

	}
}

```

## BOJ1932 정수 삼각형
### 🎈 해결방법 :
<!-- 해결 방법 -->
1. 배열을 삼각형으로 받기
2. 삼각형의 최하단부터 i와 i+1을 비교하여 큰 값을 위에 삼각형에 더해줌
3. 꼭대기 값이 Maximum값과 동일하기 때문에 출력

### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->
1. 뭐 같은 DP 인줄 알았지만, 내 아래죠? 
2. EZ

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BOJ1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int sum = (n * n + 1) / 2; 
        int[][] arr = new int[n][]; 
        
        for (int i = 0; i < n; i++) {
            arr[i] = new int[i + 1]; // 삼각형 형태로 배열 만들기
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < arr[i].length; j++) { // 배열에 할당
                arr[i][j] = Integer.parseInt(line[j]);
            }
        }
        
        for(int i=n-1;i>0;i--) { // 아래 배열부터 양쪽에 있는 값을 비교해서 큰 값을 상위 배열에 더하기
        	for(int j=0;j<arr[i].length-1;j++) {
        		arr[i-1][j]+=Math.max(arr[i][j],arr[i][j+1]);
        	}
        }
        
        System.out.println(arr[0][0]); // 맨 꼭대기가 가장 클 때
    }
}

```

## BOJ9251 LCS
### 🎈 해결방법 :
<!-- 해결 방법 -->
1. True/False table 만들기
2. Resulttable의 1st row와 1st column 채우기
3. 특정 부분에서 true/false 판별하여 true일 경우 대각선 값+1 할당, 아닐 경우 위 아래 비교해서 큰 값 할당

### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->
1. 진짜 탈모 오는 줄
2. 이런 문제 좀 연습해야할듯

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ9251 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		boolean[][] table = new boolean[str1.length()][str2.length()];

		for (int i = 0; i < str1.length(); i++) { // 같은거 다른거 true/false table 만들기
			for (int j = 0; j < str2.length(); j++) {
				if (str1.charAt(i) == str2.charAt(j)) {
					table[i][j] = true;
				} else {
					table[i][j] = false;
				}
			}
		}

		int[][] resulttable = new int[str1.length()][str2.length()]; // table과 같은 크기의 resulttable
		int cnt = 0;
		for (int i = 0; i < str2.length(); i++) { // 첫 번째 row 채우기
			if (table[0][i]) {
				cnt = 1;
			}
			resulttable[0][i] = cnt;
		}

		cnt = 0;
		for (int i = 0; i < str1.length(); i++) { // 첫 번째 column 채우기
			if (table[i][0]) {
				cnt = 1;
			}
			resulttable[i][0] = cnt;
		}
		
		for (int i = 1; i < str1.length(); i++) { // table 위치에서 true일 경우 대각선 값+1, 그렇지 않으면 위나 옆에 값 중 큰 값을 가져옴
			for (int j = 1; j < str2.length(); j++) {
				if (table[i][j]) {
					resulttable[i][j] = resulttable[i - 1][j - 1]+1;
				} else {
					resulttable[i][j] = Math.max(Math.max(resulttable[i][j - 1], resulttable[i - 1][j]),
							resulttable[i - 1][j - 1]);
				}
			}
		}

		System.out.println(resulttable[str1.length() - 1][str2.length() - 1]); // table 가장 우하단의 값이 가장 큼
	}
}


```
... 문제 수 만큼 작성 ...