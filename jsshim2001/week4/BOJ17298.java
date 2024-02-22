import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");
        int[] arr = new int[T];
        // input을 배열로 받기
        for (int i = 0; i < T; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        Stack<Integer> stack = new Stack<>();
        Stack<Integer> result = new Stack<>();
        
        // stack을 받은 배열의 끝부분 부터 확인하여 stack의 끝이 현재 부분보다 크다면 result stack에 더함
        for (int i = T - 1; i >= 0; i--) {
            while (!stack.isEmpty()) {
                if (stack.peek() > arr[i]) {
                    result.add(stack.peek());
                    break;
                } else {
                    stack.pop();
                }
            } // stack이 비었으면 -1을 result stack에 넣음
            if (stack.isEmpty()) {
                result.add(-1);
            }
            stack.push(arr[i]);
        }
        
        // StringBuilder 이용하여 result의 끝부터 뽑아 더하기
        StringBuilder sb = new StringBuilder();
        while (!result.isEmpty()) {
            sb.append(result.pop()).append(" ");
        }

        System.out.println(sb);
    }
}