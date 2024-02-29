# Week 4

## BOJ11866 요세푸스 문제 0

### 🎈 해결방법 :

나머지를 이용하여 값을 구하고 해당 값을 리스트에서 제거 -> 출력

### 💬 코멘트 :

리스트 안 쓰고 배열만 써보려고 했는데 인덱스도 꼬이고 해서 포기했습니다. 출력을 위해서 이런저런 가공을 하는 게 제일 귀찮았던 것 같습니다...

### 📄 코드

```java
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BOJ11866 {
	public static void main(String[] args) throws IOException {
		int N = readInt();
		int K = readInt();
		StringBuilder sb = new StringBuilder();
		List<Integer> people = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			people.add(i + 1);
		}
		int idx = 0;
		sb.append("<");
		for (int i = 0; i < N; i++) {
			idx = (idx + K - 1) % people.size();
			sb.append(people.get(idx)+", ");
			people.remove(idx);
		}

		sb.delete(sb.length() - 2, sb.length());
		System.out.println(sb + ">");
	}

	static int readInt() throws IOException {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		return n;
	}
}

```

## BOJ17298 오큰수

### 🎈 해결방법 :

1. PriorityQueue를 사용하여 앞에서부터 값을 저장하고, 값이 작은 순으로 꺼내 들어오는 값이랑 비교
2. 만약 들어오는 값이 해당 값보다 크다면 값에 해당하는 인덱스의 값을 교체
3. 출력

### 💬 코멘트 :

정석적인 풀이가 아닌 것 같지만... comparable 상속해서 클래스 만드는 게 재밌었음

### 📄 코드

```java
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ17298 {
	public static void main(String[] args) throws IOException {
		int N = readInt();
		StringBuilder sb = new StringBuilder();
		Queue<Comp> queue = new PriorityQueue<>();
		int[] arr = new int[N];
		Arrays.fill(arr, -1);
		for (int i = 0; i < N; i++) {
			int nextNum = readInt();
			while (!queue.isEmpty()) {
				Comp tmp = queue.poll();
				if (nextNum > tmp.value) {
					arr[tmp.idx] = nextNum;
				} else {
					queue.add(tmp);
					break;
				}
			}
			queue.add(new Comp(i, nextNum));
		}
		for (int a : arr) {
			sb.append(a + " ");
		}
		System.out.println(sb);
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

class Comp implements Comparable<Comp> {
	int idx;
	int value;

	Comp(int idx, int value) {
		this.idx = idx;
		this.value = value;
	}

	@Override
	public int compareTo(Comp o) {
		// TODO Auto-generated method stub
		return this.value - o.value;
	}
}
```

## BOJ2263 트리의 순회

### 🎈 해결방법 :

_트리의 루트를 기준으로 왼쪽, 오른쪽 자식의 인덱스를 구해 재귀로 풀려고 했음_
_이런저런 예외 처리를 해주다가 실패_
=> 트리 루트 기준으로 왼쪽 오른쪽 크기를 비교하고,
루트와 서브트리 인오더 기준의 시작, 끝 인덱스를 넘겨줘서 다시 비교

### 💬 코멘트 :

_너무 어려워요_
사이즈 비교로 바꿨더니 금방 풀려서 조금 슬퍼졌음
뭔가 이진탐색 느낌이 나서 신기했습니다

### 📄 코드

```java
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class BOJ2263 {
	static int[] inorder;
	static int[] postorder;

	static int n;
	static Set<Integer> visited = new HashSet<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		n = readInt();
		inorder = new int[n];
		postorder = new int[n];
		for (int i = 0; i < n; i++) {
			inorder[i] = readInt();
		}
		for (int i = 0; i < n; i++) {
			postorder[i] = readInt();
		}

		find(postorder[n - 1], 0, n - 1);
		System.out.println(sb);

	}

	static void find(int rootValue, int start, int end) {
		sb.append(rootValue+" ");

		int inPos = getIdxFromInorder(rootValue);
		int postPos = getIdxFromPostorder(rootValue);

		int lSize = inPos-start;
		int rSize = end - inPos;


		if (lSize>1) {
			find(postorder[postPos-rSize-1], start, inPos-1);
		}else if (lSize==1){
			sb.append(inorder[inPos-1]+" ");
		}

		if (rSize>1) {
			find(postorder[postPos-1], inPos+1, end);
		}else if (rSize == 1) {
			sb.append(inorder[inPos+1]+" ");
		}

	}

	static int getIdxFromInorder(int target) {
		for (int i = 0; i < n; i++) {
			if (inorder[i] == target) {
				return i;
			}
		}
		return -1;
	}

	static int getIdxFromPostorder(int target) {
		for (int i = 0; i < n; i++) {
			if (postorder[i] == target) {
				return i;
			}
		}
		return -1;
	}

	public static int readInt() throws IOException {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		if (c == 13)
			c = System.in.read();
		return n;
	}
}

```
