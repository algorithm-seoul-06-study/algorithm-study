# Week 2
## BOJ11866 요세푸스 문제
### 🎈 해결방법 :

queue에 데이터를 넣고,

K까지 데이터를 poll함

K는 result 배열에 담고

앞에 빠진 데이터는 다시 큐에 add함 

### 💬 코멘트 :

반례를 찾기 힘들었다.

### 📄 코드

``` java
public class BOJ11866 {

	static Queue<Integer> 사람;
	static ArrayList<Integer> result;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();

		Queue<Integer> 사람 = new LinkedList<>();
		List<Integer> result = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			사람.add(i);
		}

		while (!사람.isEmpty()) {
			for (int i = 0; i < K - 1; i++) {
				사람.add(사람.poll());
			}

			result.add(사람.poll());
		}

		System.out.print("<");
		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i));
			if (i < result.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.print(">");

		sc.close();
	}
}
```

## BOJ17298 오큰수
### 🎈 해결방법 :
<!-- 해결 방법 -->

스택을 통해

스택이 비어있으면 -1

peek을 해서 해당수가 입력값보다 크면 peek

peek값이 넣는 수보다 작으면 입력값보다 커질때까지 pop

### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->

나에게 자신감을 준 문제


### 📄 코드
``` java
public class BOJ17298 {

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int A = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] NEG = new int[A];

		for (int i = A - 1; i >= 0; i--) {
			NEG[i] = Integer.parseInt(st.nextToken());
		}

		Stack<Integer> stack = new Stack<>();
		List<Integer> result = new ArrayList<>();

		for (int i = 0; i < A; i++) {

			if (stack.isEmpty()) {
				result.add(-1);
				stack.add(NEG[i]);
			} else if (NEG[i] < stack.peek()) {
				result.add(stack.peek());
				stack.add(NEG[i]);
			} else {
				while (NEG[i] >= stack.peek() && !stack.isEmpty()) {
					stack.pop();
					if (stack.isEmpty()) {
						result.add(-1);
						break;
					} else if (NEG[i] < stack.peek()) {
						result.add(stack.peek());
						break;
					}
				}
				stack.add(NEG[i]);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = result.size() - 1 ; i >= 0 ; i--) {
			sb.append(result.get(i)).append(" ");
		}

		System.out.print(sb);
	}

}
```


## BOJ2263 트리의 순회
### 🎈 해결방법 :
<!-- 해결 방법 -->

후위순회에서 루트값을 찾고

중위순회에서 찾은 루트값의 위치를 찾아

왼쪽 서브트리와 오른쪽 서브트리를 찾음

해당 로직을 재귀하여 트리를 완성하고

전위순회를 진행함


### 💬 코멘트 :
<!-- 문제에 대한 코멘트 작성 -->

구현 실패..ㅎ


### 📄 코드
``` java
public class BOJ2263 {

	class Node {
		int data;
		Node left, right;

		Node(int data) {
			this.data = data;
		}
	}

	public static class Tree {

		Node root;

		Tree() {

		}

		public Tree(int[] inorder, int[] postorder) {
			root = makeTree(inorder, postorder);
		}

		public Node makeTree(int[] inorder, int[] postorder) {

			return null;
		}

		public static void main(String[] args) throws Exception {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			int n = Integer.parseInt(br.readLine());

			int[] inorder = new int[n];
			int[] postorder = new int[n];

			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < n; i++) {
				inorder[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < n; i++) {
				postorder[i] = Integer.parseInt(st.nextToken());
			}

			Tree tree = new Tree(inorder, postorder);

		}

	}

}
```

