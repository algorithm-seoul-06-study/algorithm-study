import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BOJ1932 {
	public static void main(String[] args) throws IOException {
		int n = readInt();
		List<Integer> next = new ArrayList<>();
		int max = 0;

		for (int i = 0; i < n; i++) {
			int[] rec = new int[i+1];
			for (int j=0; j<i;j++) {		
				rec[j] = next.get(j) + readInt();
			}
			next.clear();
			for (int j = 0; j < i+1; j++) {
				if (j > 0) {
					next.set(j, Math.max(next.get(j), rec[j]));
				}else {
					next.add(rec[j]);
				}
				next.add(rec[j]);

			}
		}
		for (int j=0; j<n;j++) {		
			max= Math.max(max, next.get(j) + readInt());
		}
		System.out.println(max);
		

	}

	public static int readInt() throws IOException {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		if(c==13) c= System.in.read();
		return n;
	}
}
