import java.io.IOException;
import java.util.PriorityQueue;

public class BOJ12865 {
	static class Item {
		int w, v;

		Item(int w, int v) {
			this.w = w;
			this.v = v;
		}
	}

	public static void main(String[] args) throws IOException {
		int maxValue = 0;
		int N = readInt();
		int K = readInt();
		PriorityQueue<Item> items = new PriorityQueue<>((a,b)->a.w-b.w);
		int[] values = new int[K+1];
		for (int n = 0; n < N; n++) {
			Item item = new Item(readInt(), readInt());
			if (item.w>K) continue;
			items.add(item);
		}
		while(!items.isEmpty()) {
			Item item = items.poll();
			
			for (int w=K-item.w; w>0; w--) {
				if (values[w]>0) {
					values[w+item.w] = Math.max(values[w+item.w], item.v+values[w]);
					maxValue =  Math.max(values[w+item.w], maxValue);
					
				}
			}
			values[item.w] = Math.max(item.v, values[item.w]);
			maxValue =  Math.max(values[item.w], maxValue);
		}
		System.out.println(maxValue);
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