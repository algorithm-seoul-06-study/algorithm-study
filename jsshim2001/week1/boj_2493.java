import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        int[] arr = new int[T];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < T; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T; i++) {
            while (!stack.isEmpty()) {
                if (arr[stack.peek()] < arr[i]) {
                    stack.pop();
                } else {
                    System.out.print((stack.peek() + 1) + " ");
                    break;
                }
            }
            if (stack.isEmpty()) {
                System.out.print(0 + " ");
            }
            stack.push(i);
        }
    }
}
