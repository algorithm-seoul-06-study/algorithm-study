# Week 10
## BOJ12865 알고 푸는 알고리즘
### 🎈 해결방법 :
냅색 오늘 배운 방법
### 💬 코멘트 :
냅색을 제대로 알면 그대로 품

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ12865 {
	static int n, k;
	static int[] weight, value;
	static int[][] dp;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] line = br.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		k = Integer.parseInt(line[1]);
		// 최대값은 k키로
		dp = new int[n + 1][k + 1];
		weight = new int[n + 1];
		value = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			String[] line2 = br.readLine().split(" ");
			weight[i] = Integer.parseInt(line2[0]);
			value[i] = Integer.parseInt(line2[1]);

		}

		// 첫번째 물건 부터 시작
		for (int i = 1; i <= n; i++) {
			for (int w = 1; w <= k; w++) {
				if (w < weight[i])
					dp[i][w] = dp[i - 1][w];
				else {
					dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-weight[i]]+value[i]);

				}

			}
		}
		System.out.println(dp[n][k]);

	}

}

```

## BOJ10253 알고 푸는 알고리즘
### 🎈 해결방법 :
1/2부터 반복해서 빼주는 게 핵심 이었다고 생각
기약분수로 만드는 것도
### 💬 코멘트 :
잠깐이나마 고딩으로 돌아간 것 같아 끔 행복했습니당

### 📄 코드
```java
import java.util.Scanner;

public class BOJ10253 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		for(int t=0; t<tc; t++) {
			double a=sc.nextDouble();
			double b=sc.nextDouble();
			
			//제일큰 분수가 1/2라서 2부터 시작
			double mod =2;
			
			//분자가 1이 될 때 까지 반복
			while(a!=1) {
				double ab = a / b;
				//1/2부터 시작해서 뺐을 때 0보다 크면 빼주고 분자 분모 재 설정
				if(ab-(1/mod)>0) {
					a=a*mod - b;
					b=b*mod;
				}
				// 기약분수 만드는 곳
				for(int i =(int)a; i>1; i--) {
					if(a%i==0&&b%i==0) {
						a/=i;
						b/=i;
					}
				}					
				mod+=1;
			}
			
			System.out.println((int)b);
		}
	}
}

```