package aStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ17298 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Stack<Integer> stack = new Stack<>();
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		Stack<Integer> result = new Stack<>();
		
		int[] arr = new int[n];
		String[] line = br.readLine().split(" ");
		
		for(int i=n-1; i>=0; i--) {
			int k = Integer.parseInt(line[i]);
			
			if(stack.isEmpty()) {
				result.push(-1);
				stack.push(k);
			} else {
				while(!stack.isEmpty()&&k>=stack.peek()) {
					stack.pop();
				}
				if(stack.isEmpty()) {
					result.push(-1);
					stack.push(k);
				} else {
					result.push(stack.peek());
					stack.push(k);
				}
			}
		}
		while(!result.isEmpty()) {
			sb.append(result.pop()+" ");
		}
		
		System.out.println(sb);
		
	}
}
