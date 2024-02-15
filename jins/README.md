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

1. 문자열을 캐릭터로 쪼개 배열에 담고, 인덱스의 위치를 카운팅함

2. 캐릭터가 담길때마다 폭발물이 연속해서 담겻는지 확인을 함

3. 폭발물이면 해당 길이만큼 인덱스를 감소

4. 모든 문자열을 담고나면 인덱스 길이만큼 배열을 String형으로 바꾼 후 출력

### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->

문제에는 

문자열이 폭발 문자열을 포함하고 있는 경우에, 모든 폭발 문자열이 폭발하게 된다. 남은 문자열을 순서대로 이어 붙여 새로운 문자열을 만든다.

라고 적혀있어서
stack을 통해 폭발물사이에 글자가 끼일때 같이삭제하려고했으나 구현실패..

단순하게 문제에 접근하니까 해결이됨



### 📄 코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ9935 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 문자열
		String str = br.readLine();
		// 폭발물
		String bomb = br.readLine();

		// 폭발물 제거 후의 문자열을 저장할 배열
		char[] result = new char[str.length()];

		// result 배열 인덱스
		int index = 0;

		for (int i = 0; i < str.length(); i++) {
			// 현재 문자를 result 배열에 저장
			result[index] = str.charAt(i);
			// 폭발물 체크, 폭발물이 범위만큼 문자 및 인덱스 감소
			if (isBomb(result, index, bomb))
				index -= bomb.length();
			// result 배열의 인덱스를 증가하여 다음 문자를 저장할 위치로 이동
			index++;
		}

		StringBuilder answer = new StringBuilder("");

		for (int i = 0; i < index; i++) {
			answer.append(result[i]);
		}

		System.out.println((answer.length() == 0) ? "FRULA" : answer);
	}

	private static boolean isBomb(char[] result, int index, String bomb) {
		
		// 문자열이 폭발물 길이보다 짧으면 X
		if (index < bomb.length() - 1)
			return false;

		// 폭발물 체크
		for (int i = 0; i < bomb.length(); i++) {
			if (bomb.charAt(i) != result[index - bomb.length() + 1 + i])
				return false;
		}

		return true;

	}

}
```

