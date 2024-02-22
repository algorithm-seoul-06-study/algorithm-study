package study.week4;

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
	// lr : inorder에서 부모의 index 기준으로 왼쪽 자식으로 내려가면 1, 으론쪽 자식으로 내려가면 0
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
