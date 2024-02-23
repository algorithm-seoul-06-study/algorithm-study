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
