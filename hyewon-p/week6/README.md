# Week 6

## BOJ1992 쿼드트리

### 🎈 해결방법 :
재귀를 통해 네 구역을 검사하고, 압축이 가능하다면 압축해서 저장
저장된 스택에서 4개의 배열을 빼서 -> 배열의 크기가 1인가? -> 내용물이 다 같은가? 검사
압축 안되면 괄호를 추가해서 저장

### 💬 코멘트 :
1. 정말 억울합니다 저의 아름다운 파이썬 코드를 먼저 봐주세요
```python
li = []

def sol():
    N = int(input())
    global li
    for i in range(N):
        li.append(input())
    print(press(0,0,N))

def press(x, y, size):
    global li
    if size==1:
        return li[y][x]
    s = press(x, y, size//2)+press(x+size//2, y, size//2)+press(x, y+size//2, size//2)+press(x+size//2, y+size//2, size//2)
    if len(s)==4 and (sum([ int(i) for i in s])==0 or sum([int(i) for i in s])==4):
        return s[0]
    return "("+s+")"

sol()
```
제목에 트리가 들어가니까 트리를 만들어서 풀면 됐겠다 하는 생각은 다른 사람 코드 보고서야 하게 되었습니다.

그림이 프렉탈인데 당연히 재귀로 합쳐야하는 거 아닌가

2. String에 크기 제한이 있어서 답을 전부 하나의 문자열에 저장하면 출력을 못합니다.
저도 알고 싶지 않았는데요...

### 📄 코드
```java
import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ1992 {
	static String[] nums;
	static Deque<char[]> zipped;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		zipped = new ArrayDeque<>();
		// 입력받은 숫자들 저장
		nums = new String[N];
		for (int n = 0; n < N; n++) {
			nums[n] = br.readLine();
		}

		zip(0, 0, N);
		StringBuilder sb = new StringBuilder();
		while (!zipped.isEmpty()) {
			sb.append(zipped.poll());
		}

		System.out.println(sb);
	}

	// 재귀를 통해 네 구역을 검사하고, 압축이 가능하다면 압축해서 zipped에 저장
	// 검사는 zipped에서 4개의 배열을 빼서 -> 배열의 크기가 1인가? -> 내용물이 다 같은가? 검사
	// 압축 안되면 괄호를 추가해서 저장
	static void zip(int x, int y, int size) {
		// 크기 1이면 그냥 추가 후 리턴
		if (size == 1) {
			zipped.add(new char[] { nums[y].charAt(x) });
			return;
		}

		// 네 구역 검사
		zip(x, y, size / 2);
		zip(x + size / 2, y, size / 2);
		zip(x, y + size / 2, size / 2);
		zip(x + size / 2, y + size / 2, size / 2);

		// zipped의 뒤 4개의 배열은 위 검사의 결과물이므로...
		Deque<char[]> tmpLi = new ArrayDeque<>();
		int[] count = new int[2];
		// 하나씩 빼서 검사
		for (int i = 0; i < 4; i++) {
			char[] tmp = zipped.pollLast();
			// 압축이 다 돼서 길이가 1이라면
			if (tmp.length == 1) {
				// 0/1 카운트
				count[tmp[0] - '0']++;
			}
			tmpLi.addLast(tmp);
		}

		// 만약 1이 4개거나 0이 4개면 괄호 없이 하나만 zipped에 추가
		if (count[0] == 4 || count[1] == 4) {
			zipped.addLast(tmpLi.pollLast());
		} else {
			// 아니라면 괄호 추가하고 1차원으로 펴서 zipped에 추가
			StringBuilder tt = new StringBuilder();
			tt.append('(');
			while (!tmpLi.isEmpty()) {
				for (char tm : tmpLi.pollLast()) {
					tt.append(tm);
				}
			}
			tt.append(')');
			char[] a = new char[tt.length()];
			tt.getChars(0, tt.length(), a, 0);
			zipped.add(a);
		}

	}

}

```

## BOJ2011 암호코드

### 🎈 해결방법 :
dp로 배열에 저장
1. i-1에 한칸 띄고 붙여서 추가해도 되는 경우 i-1만큼 증가
2. i-1에 띄지 않고 바로 붙여서 추가해도 되는 경우 i-2만큼 증가

### 💬 코멘트 :
심심풀이 땅콩으로 알맞은 난이도가 아니었나 싶습니다

저장할 때 나머지 연산 안 해줘서 엄청 틀리긴 했지만

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2011 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] letters = br.readLine().toCharArray();

		// 만약 첫번째 숫자가 0이라면 성립하지 않으므로 리턴
		if (letters[0] == '0') {
			System.out.println(0);
			return;
		}
		
		// 저장할 배열
		long[] dp = new long[letters.length];
		for (int i = 0; i < dp.length; i++) {
			// 1개라면 경우의 수가 1
			if (i == 0) {
				dp[i] = 1;
			// 2개일 때 오류 나지 않도록 따로 계산
			}else if(i==1) {
				if(Integer.parseInt(letters[i - 1] + "" + letters[i]) <= 26) dp[i]++;
				if(letters[i]!='0') dp[i]++;
			// 만약 0이 연속으로 나오거나,
			// 0 다음 수를 띄어서 쓸 수 없는 경우 리턴
			}else {
				if (letters[i] == '0'
						&& (letters[i - 1] == '0' || Integer.parseInt(letters[i - 1] + "" + letters[i]) > 26)) {
					System.out.println(0);
					return;
				}

				// 앞 글자에 띄어서 붙일 수 있는지
				if (letters[i] != '0') {
					dp[i] += dp[i - 1];
				}

				// 앞 글자에 바로 붙일 수 있는지
				if (Integer.parseInt(letters[i - 1] + "" + letters[i]) <= 26 && letters[i - 1] != '0') {
					dp[i] += dp[i - 2];
				}
				
				dp[i] %= 1000000;
			}
		}

		System.out.println(dp[dp.length - 1] % 1000000);
	}
}
```

## BOJ1790 수 이어 쓰기 2

### 🎈 해결방법 :
앞에서부터 한번에 더해나가는 느낌으로... 나머지로 인덱스를 잡아서 확인

### 💬 코멘트 :
패턴을 알아내는 게 전부였던 수학 문제

### 📄 코드
```java
import java.io.IOException;

public class BOJ1790 {
	public static void main(String[] args) throws IOException {
		int N = readInt();
		long k = readInt();

		// 완전히 채워지는 자릿수만큼의 새로운 수 길이 확인
		long limit = 1;
		// 자릿수
		int i = 0;

		while (true) {
			// 새로운 수의 길이 더해나감
			long tmp = i * (long) Math.pow(10, i - 1) * 9;
			// 목표 인덱스를 넘어가면 탈출
			if (limit + tmp > k) {
				break;
			}
			limit += tmp;
			i++;
		}

		// 앞 자릿수까지의 길이 + 남는 만큼을 더하면 어떤 숫자의 일부인지 알 수 있음
		long num = (long) (Math.pow(10, i-1) + (k - limit) / i);
		if (num > N) {
			System.out.println(-1);
		} else {
			// 숫자에서 원하는 인덱스의 수 출력
			System.out.println((num + "").charAt((int) (k - limit) % i));
		}
	}

	static int readInt() throws IOException {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		if (c == 13)
			System.in.read();
		return n;
	}
}

```