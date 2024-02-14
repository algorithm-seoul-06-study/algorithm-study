# Week 2
## BOJ9095 1, 2, 3 더하기
### 🎈 해결방법 :

더하기의 로직

1 -> 1 : 1개

2 -> 1+1, 2 : 2개

3 -> 1+1+1, 1+2, 2+1, 3 : 4개

4 -> 1+1+1+1 ... 4 : 7개 = 4 + 2 + 1

5 -> 1+1+1+1+1 ... 5 : 13개 = 7 + 4 + 2 

...

n -> ... n : (n-1의 갯수) + (n-2의 갯수) + (n-3의 갯수)


### 💬 코멘트 :

로직을 찾아내는데 상당한 시간이 걸림

### 📄 코드
```java
import java.util.Scanner;

public class BOJ9095 {
	public static void main(String args[]) {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int i = 0; i < T; i++) 

			int n = sc.nextInt();

			int dp[] = new int[Math.max(4, n + 1)]; // 3까지는 고정된 값을 입력하기 위해 4를 기본값으로 줌.

			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;

			for (int j = 4; j <= n; j++) {
				dp[j] = dp[j - 1] + dp[j - 2] + dp[j - 3];
			}

			System.out.println(dp[n]);
		}

		sc.close();

	}

}
```

## BOJ9935 문자열 폭발
### 🎈 해결방법 :
<!-- 해결 방법 -->

### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->

### 📄 코드
```java
//코드를 넣어주세요
```

