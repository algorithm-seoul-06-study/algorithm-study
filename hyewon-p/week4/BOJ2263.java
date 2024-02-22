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

		int root = postorder[n - 1];
		int inIdx = getIdxFromInorder(root);
		find(n - 1, inIdx);
		System.out.println(sb);

	}

	// 루트값의 postorder에서의 index, inorder에서의 index 전달
	static void find(int postIdx, int inIdx) {
		int rootValue = postorder[postIdx];
		// 루트값 저장
		System.out.print("root: " + rootValue);
//		System.out.print(visited);
		if (visited.contains(rootValue)) {
			System.out.println("nope1");
			return;
		}
		visited.add(rootValue);
		sb.append(rootValue + " ");
		if (inIdx < 1 || inIdx >= n-1) {
			System.out.println("nope2");
			return;
		}

		// 오른쪽 서브트리의 루트값
		int rootR = postorder[postIdx - 1];
		int rootRIdx = getIdxFromInorder(rootR);

		// 왼쪽 서브트리의 루트값
		int rootLIdxP = getIdxFromPostorder(inorder[inIdx + 1]) - 1;
		int rootL = postorder[rootLIdxP];
		int rootLIdx = getIdxFromInorder(rootL);

		System.out.println(" L: " + rootL + " R: " + rootR);
		// 자식이 왼쪽에만 있는 경우
		if (rootR == rootL) {
			find(rootLIdxP, rootLIdx);
		}
		// 자식이 오른쪽에만 있는 경우
		else if (rootLIdxP>postIdx) {
			find(postIdx - 1, rootRIdx);
		} else {
			find(rootLIdxP, rootLIdx);
			find(postIdx - 1, rootRIdx);
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
