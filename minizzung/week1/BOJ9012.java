import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9012 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			String str = br.readLine();
			
			// 스택: '('를 쌓다가 ')'를 만나면 pop
			// 스택에 하나도 남지 않으면 YES, 하나라도 남으면 NO
			Stack<Character> stack = new Stack<>();
			
			for(int i=0; i<str.length(); i++) {
				if(str.charAt(i)==')' && !stack.empty() && stack.peek()=='(')
					stack.pop();
				else
					stack.push(str.charAt(i));
			}
			
			if(stack.isEmpty()) System.out.println("YES");
			else System.out.println("NO");
		}
		
		br.close();
	}
}
