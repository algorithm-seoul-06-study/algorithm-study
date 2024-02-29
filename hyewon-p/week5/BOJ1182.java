import java.io.*;
import java.util.*;

public class Main {
	static int N, S;
	static List<Integer> nums;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		FastReader fr = new FastReader();
		N = fr.nextInt();
		S = fr.nextInt();
		nums = new ArrayList<>();
		for (int n = 0; n < N; n++) {
			nums.add(fr.nextInt());
		}
		getSum(0, 0, 0, 0);
		System.out.println(answer);
	}

	static void getSum(int idx, int sum, int prev, int count) {
		if (sum == S && prev != count && count > 0) {
			answer++;
		}
		if (idx == N) {
			return;
		}
		// 현재 인덱스의 값 미포함
		getSum(idx + 1, sum, count, count);
		// 현재 인덱스의 값 포함
		getSum(idx + 1, sum + nums.get(idx), count, count + 1);
	}

}

class FastReader {
	BufferedReader br;
	StringTokenizer st;

	public FastReader() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}
    
	int nextInt() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return Integer.parseInt(st.nextToken());
	}

}