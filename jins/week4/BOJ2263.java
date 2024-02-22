package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
