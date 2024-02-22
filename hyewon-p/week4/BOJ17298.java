import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ17298 {
	public static void main(String[] args) throws IOException {
		int N = readInt();
		StringBuilder sb = new StringBuilder();
		Queue<Comp> queue = new PriorityQueue<>();
		int[] arr = new int[N];
		Arrays.fill(arr, -1);
		for (int i = 0; i < N; i++) {
			int nextNum = readInt();
			while (!queue.isEmpty()) {
				Comp tmp = queue.poll();
				if (nextNum > tmp.value) {
					arr[tmp.idx] = nextNum;
				} else {
					queue.add(tmp);
					break;
				}
			}
			queue.add(new Comp(i, nextNum));
		}
		for (int a : arr) {
			sb.append(a + " ");
		}
		System.out.println(sb);
	}

	public static int readInt() throws IOException {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		if (c == 13)
			System.in.read();
		return n;
	}

}

class Comp implements Comparable<Comp> {
	int idx;
	int value;

	Comp(int idx, int value) {
		this.idx = idx;
		this.value = value;
	}

	@Override
	public int compareTo(Comp o) {
		// TODO Auto-generated method stub
		return this.value - o.value;
	}
}