# Week4
## BOJ11866 요세푸스 문제 0
### 🎈 해결방법 :
1. count 배열에 제거된 수의 값 0 -> 1
2. index를 1씩 올려가면서 다음 수의 count 배열 값이 1이면 그냥 지나가도록
### 💬 코멘트 :
문제 다 풀고 큐가 생각난게 안타깝다

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11866 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		int[] count = new int[N+2];
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		int a = 0;
		// N번 반복
		for (int i = 0; i < N; i++) {
			int cnt = 0;
			// cnt == K가 될 때까지 반복 => 제거된 수를 제외한 카운팅
			while (cnt < K) {
				// 다음 수가 이미 제거된 수면 다음 수로 이동 (가짜 이동)
				if (count[a+1] == 1) {
					a++;
				// 다음 수가 제거되지 않은 수라면 cnt값을 올리고 다음 수로 이동 (진짜 이동)
				} else {
					cnt++;
					a++;
				}
				// a값이 최대값인 N을 넘기면 1로 초기화
				if (a == N + 1) {
					a = 1;
				}
			}
			// K만큼 이동해서 도착한 수의 count값을 올려 제거 표시
			count[a]++;
			// a가 1일땐 N+1의 count값도 올려서 count[a+1]에 적용되도록 설정
			if (a == 1) {
				count[N+1] = 1;
			}
			// sb에 추가
			sb.append(a).append(", ");
		}
		
		sb.delete(sb.length() - 2, sb.length()).append(">");
		System.out.println(sb);
	}
}
```

## BOJ17298 오큰수
### 🎈 해결방법 :
1. 값을 뒤에서부터 Stack에 넣으면서 체크
2. 스택이 비거나 스택 최상단 값이 넣을 값보다 클 때까지 Stack.pop()
3. result 배열에 뒤에서부터 스택 최상단값(or -1) 저장
4. Stack.push() 후 1~3 반복
5. result 배열 앞에서부터 출력

### 💬 코멘트 :
탑아 고맙다~~

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17298 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] input = new int[N];
		st = new StringTokenizer(br.readLine());
		// 입력값 뒤에서부터 input 배열에 저장
		for (int i = N - 1; i >= 0; i--) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		// 결과를 담을 배열 result에 뒤에서부터 저장
		int[] result = new int[N];
		int idx = N - 1;
		
		// 배열 길이만큼 반복
		for (int i = 0; i < result.length; i++) {
			// 스택이 비거나 peek() > input[i] 일 때까지 pop()
			while (!stack.isEmpty() && stack.peek() <= input[i]) {
				stack.pop();
			}
			// 스택 비어있으면 -1 저장
			if (stack.isEmpty()) {
				result[idx--] = -1;
			// 스택 안비어있으면 peek() 저장
			} else {
				result[idx--] = stack.peek();
			}
			// push()해서 스택 채우기
			stack.push(input[i]);			
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < result.length; i++) {
			sb.append(result[i]).append(" ");
		}
		System.out.println(sb);
	}
}
```

## BOJ2263 트리의 순회
### 🎈 해결방법 :
부모에서 시작해서 타고 내려갈 때마다 postorder와 inorder 배열을 고려해서 index 계속 조절하면서 preorder 출력 (글로 설명 못하겠음)

1. inorder에서 부모의 위치를 기준으로 왼쪽 서브트리와 오른쪽 서브트리 크기 활용
2. 오른쪽 자식 타고 내려갈 때마다 index 값 1씩 밀리는거 고려
3. 자식이 하나인 경우 왼쪽 자식으로 가정하고 진행

### 💬 코멘트 :
제출 박치기 계속 하다가 머리 깨질뻔  
변수 떡칠해서 보기 힘들거 미리 죄송..  
풀었는데도 이 풀이가 맞나 싶고  
코드가 정돈되지 않았는데 그만 보고싶음

### 📄 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2263 {
	static String[] arr;
	static Queue<Integer> preorder = new LinkedList<>();
	static int[] inorder;
	static int[] postorder;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		inorder = new int[n];
		postorder = new int[n];
		arr = br.readLine().split(" ");
		for (int j = 0; j < n; j++) {
			inorder[j] = Integer.parseInt(arr[j]);
		}
		arr = br.readLine().split(" ");
		for (int j = 0; j < n; j++) {
			postorder[j] = Integer.parseInt(arr[j]);
		}
		
		preroderQueueOffer(n-1, 0, n-1, 1, 1);
		
		StringBuilder sb = new StringBuilder();
		while (!preorder.isEmpty()) {
			sb.append(preorder.poll()).append(" ");
		}
		System.out.println(sb);
	}
	
	// i : 부모의 postorder에서의 index
	// start : 자식의 inorder에서의 가장 왼쪽 index
	// end : 자식의 inorder에서의 가장 오른쪽 index
	// lr : inorder에서 부모의 index 기준으로 왼쪽 자식으로 내려가면 1, 오론쪽 자식으로 내려가면 0
	// cnt : 오른쪽 자식으로 한 번 갈때마다 배열번호 밀려서 그때마다 1씩 올려줄 변수 cnt
	static void preroderQueueOffer(int i, int start, int end, int lr, int cnt) {
		// target을 부모의 값으로 초기화
		int target = postorder[i];
		// child2 : 오른쪽 자식의 postorder에서의 index
		int child2 = i - 1;
		// child : 왼쪽 자식의 postorder에서의 index
		int child1 = 0;
		// inorder에서 부모의 index 기준 왼쪽 원소 개수
		int left = 0;
		// inorder에서 부모의 index 기준 오른쪽 원소 개수
		int right = 0;
		// inorder에서의 target의 index
		int idx = -1;
		// start부터 end까지 inorder에서 target값 찾기
		for (int j = start; j <= end; j++) {
			if (inorder[j] == target) {
				idx = j;
				left = j - start;
				right = end - j;
				if (lr == 1) {
					child1 = j - cnt;	
				// 오른쪽으로 타고 내려왔을 때마다 ++cnt
				} else {
					child1 = j - ++cnt;
				}
				break;
			}
		}
		
		// preorder에 target값 offer()
		preorder.offer(target);
		// 자식이 하나면 바로 왼쪽으로 타고 내려가기
		if (child1 == child2) {
			if (left >= 1) {
				preroderQueueOffer(child1, idx - left, idx - 1, 1, cnt);				
			}
		// 자식이 둘이면
		} else {
			// left + right <= 2 이면 내려가지 않고 자식값 offer()
			if (left + right <= 2) {
				if (left == 1) {
					preorder.offer(inorder[idx-1]);
				}
				if (right == 1) {
					preorder.offer(inorder[idx+1]);				
				}
				return;
			}
			// 왼쪽 자식 하나뿐이면 타고 내려가기 전에 먼저 offer()
			if (left + right > 2 && left == 1) {
				preorder.offer(inorder[idx-1]);
			}
			// left >= 2 이면 왼쪽에 자식의 자식까지 있다는거니 타고 내려가기
			if (left >= 2) {
				preroderQueueOffer(child1, idx - left, idx - 1, 1, cnt);			
			}
			// right >= 2 이면 오른쪽에 자식의 자식까지 있다는거니 타고 내려가기
			if (right >= 2) {
				preroderQueueOffer(child2, idx + 1, idx + right, 0, cnt);			
			}
			// 오른쪽 자식 하나뿐이면 다 타고 내려간 뒤에 나중에 offer()
			if (left + right > 2 && right == 1) {
				preorder.offer(inorder[idx+1]);
			}
		}
	}
}
```