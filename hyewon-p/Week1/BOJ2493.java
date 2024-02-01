import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Stack<int[]> container = new Stack<>();
		for (int index = 0; index < T; index++) {
			int tmp = Integer.parseInt(st.nextToken());
			while (!container.isEmpty()) {
				if (container.peek()[0] >= tmp) {
					break;
				} else {
					container.pop();
				}
			}
			System.out.print(( container.isEmpty()?0:container.peek()[1] + 1) + " ");
			container.push(new int[] { tmp, index });
		}
	}
}