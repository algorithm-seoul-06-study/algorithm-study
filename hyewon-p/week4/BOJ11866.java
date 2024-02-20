import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BOJ11866 {
	public static void main(String[] args) throws IOException {
		int N = readInt();
		int K = readInt();
		StringBuilder sb = new StringBuilder();
		List<Integer> people = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			people.add(i + 1);
		}
		int idx = 0;
		sb.append("<");
		for (int i = 0; i < N; i++) {
			idx = (idx + K - 1) % people.size();
			sb.append(people.get(idx)+", ");
			people.remove(idx);
		}

		sb.delete(sb.length() - 2, sb.length());
		System.out.println(sb + ">");
	}

	static int readInt() throws IOException {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		return n;
	}
}
