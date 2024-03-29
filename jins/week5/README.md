# Week 5
## BOJ1182  부분수열의 합 문제
### 🎈 해결방법 :

재귀를 통해서 최대 인덱스에 도달했을때,

S와 값이 같으면 카운트

### 💬 코멘트 :

기본적인 재귀 구현

### 📄 코드

``` java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1182 {
	
	static int N,S;
	static int[] arr;
	static int count = 0; 

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		arr = new int[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		countArr(0, 0);
		if(S == 0) {
			count -= 1;
		}
		System.out.println(count);

	}

	public static void countArr(int index, int sum) {

		if(index == N) {
			if(sum ==  S) {
				count++;
			}
			return;
		}
		
		
		countArr(index + 1, sum + arr[index]);
		countArr(index + 1, sum);
			
	}

}

```

## BOJ1932 정수 삼각형 문제
### 🎈 해결방법 :
<!-- 해결 방법 -->

바텀업을 통해서 각 루트의 최대 합을 구할 수 있음

최종적으로 0, 0의 값을 리턴

### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->

로직을 생각하는데 시간이 오래걸림

### 📄 코드
``` java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1932 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        int[][] input = new int[N][N];

        for (int i = 0; i < N; i++) {
        	
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j <= i; j++) {
            	
            	input[i][j] = Integer.parseInt(st.nextToken());
            	
            }
            
        }

        for (int i = N - 2; i >= 0; i--) {
        	
            for (int j = 0; j <= i; j++) {
            	
            	input[i][j] += Math.max(input[i + 1][j], input[i + 1][j + 1]);
            	
            }
            
        }

        System.out.println(input[0][0]);
        
    }
}

```

