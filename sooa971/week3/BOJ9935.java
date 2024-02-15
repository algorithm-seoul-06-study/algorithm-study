package aStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9935 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack = new Stack<>();

		char[] line = br.readLine().toCharArray();
		String str = br.readLine();
		int len = str.length();

		for (int i = 0; i < line.length; i++) {
			stack.push(line[i]);
			if (stack.size() >= len && stack.peek() == (str.charAt(len - 1))) {
				boolean flag = true;
				for (int j = 0; j < len; j++) {
					if (str.charAt(len - j - 1) != stack.get(stack.size() - j - 1)) {
						flag = false;
						break;
					}
				}
				if (flag) {
					for (int j = 0; j < len; j++) {
						stack.pop();
					}
				}
			}
		}

		if (stack.isEmpty()) {
			System.out.println("FRULA");
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < stack.size(); i++) {
				sb.append(stack.get(i));
			}
			System.out.println(sb);
		}
	}
}
