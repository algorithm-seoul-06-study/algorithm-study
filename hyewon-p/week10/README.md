# Week9

## BOJ12865 평범한 배낭

### 🎈 해결방법 :
무게별 최대 가치를 저장해두고 모든 물품에 대해 매번 갱신하는 dp

### 💬 코멘트 :
비교하는 도중에 자꾸 최대값 덮어씌우는 게 고민이었는데 순회를 뒤에서부터 돌면 된다는 것을 깨닫고 성불했습니다.

### 📄 코드
```java
import java.io.IOException;
import java.util.PriorityQueue;

public class BOJ12865 {
	// 물품 클래스
	static class Item {
		int w, v;
		Item(int w, int v) {
			this.w = w;
			this.v = v;
		}
	}

	public static void main(String[] args) throws IOException {
		// 가치의 최댓값 저장
		// 무게별로 최댓값을 저장할 것이므로...
		int maxValue = 0;
		int N = readInt(), K = readInt();
		// 무게 기준 작은 것부터 뺄 거임
		PriorityQueue<Item> items = new PriorityQueue<>((a,b)->a.w-b.w);
		// 무게별 최대 가치 저장할 배열
		int[] values = new int[K+1];
		for (int n = 0; n < N; n++) {
			Item item = new Item(readInt(), readInt());
			if (item.w>K) continue;
			items.add(item);
		}

		while(!items.isEmpty()) {
			Item item = items.poll();
			// K 안 넘어가는 범위 내에서 뒤에서부터 검사
			for (int w=K-item.w; w>0; w--) {
				if (values[w]>0) {
					// 저장된 것과 자기자신을 조합해서 최대값 갱신
					values[w+item.w] = Math.max(values[w+item.w], item.v+values[w]);
					maxValue =  Math.max(values[w+item.w], maxValue);
					
				}
			}
			// 마지막으로 자기 무게 추가해주기
			// 앞에서 하면 자기 무게를 두번 더할 수 있어서 꼬임
			values[item.w] = Math.max(item.v, values[item.w]);
			maxValue =  Math.max(values[item.w], maxValue);
		}
		System.out.println(maxValue);
	}

	public static int readInt() throws IOException {
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

## BOJ10253 헨리

### 🎈 해결방법 :
1. a/b부터 단위분수를 빼가면서 남은 값을 저장
2. 남은 값의 분모보다 크면서 분자로 나누어지는 값을 다음 단위분수의 분모로 사용
3. 통분해서 빼고, 남은 값은 약분해서 저장
4. 남은 값이 단위분수가 될 때까지 반복

### 💬 코멘트 :
수학 어려움

### 📄 코드
```java
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		int T = readInt();
		for (int t = 0; t < T; t++) {
			int a = readInt(), b = readInt();
			// a/b에서 단위분수를 뺀 나머지 
			int[] curr = new int[] { a, b };
			// 남은 수의 분자가 1이면 그만 할 예정
			while (curr[0]>1) {
				// 단위분수의 분모 구하기
				// 남은 수(curr)의 분모보다 큰 수 구하는 방식
				// 2/5이면 2/8->8을 구하는...
				int lastDeno = (curr[1] + (curr[0]-curr[1]%curr[0])) / curr[0];
				// 단위분수랑 남은 수 통분
				int lcmNum = lcm(curr[1], lastDeno);
				// 빼기
				curr[0] = curr[0]*(lcmNum/curr[1]) - (lcmNum/lastDeno);
				curr[1] = lcmNum;
				// 남은 수 미래를 위해 약분
				int tmp = gcd(curr[0], curr[1]);
				if (tmp>1) {
					curr[0]/=tmp;
					curr[1]/=tmp;
				}
			}
			System.out.println(curr[1]);
		}
	}

	// 최대공약수 구하기
    public static int gcd(int a, int b) {
		if (b==0) return a;
		return gcd(b, a%b);
	}
	
	// 최소공배수 구하기
	public static int lcm(int a, int b) {
		return a*b/gcd(a, b);
	}

	public static int readInt() throws IOException {
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
