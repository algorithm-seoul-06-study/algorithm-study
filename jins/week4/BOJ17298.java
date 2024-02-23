package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

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
