import java.io.IOException;

public class BOJ1790 {
	public static void main(String[] args) throws IOException {
		int N = readInt();
		long k = readInt();

		long limit = 1;
		int i = 0;

		while (true) {
			long tmp = i * (long) Math.pow(10, i - 1) * 9;
			if (limit + tmp > k) {
				break;
			}
			limit += tmp;
			i++;
		}
		long num = (long) (Math.pow(10, i-1) + (k - limit) / i);
		if (num > N) {
			System.out.println(-1);
		} else {
			System.out.println((num + "").charAt((int) (k - limit) % i));
		}
	}

	static int readInt() throws IOException {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		if (c == 13)
			System.in.read();
		return n;
	}
}
