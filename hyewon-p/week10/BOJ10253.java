import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		int T = readInt();
		for (int t = 0; t < T; t++) {
			int a = readInt(), b = readInt();
			int[] curr = new int[] { a, b };
			
			while (curr[0]>1) {
				int lastDeno = (curr[1] + (curr[0]-curr[1]%curr[0])) / curr[0];
				int lcmNum = lcm(curr[1], lastDeno);
				curr[0] = curr[0]*(lcmNum/curr[1]) - (lcmNum/lastDeno);
				curr[1] = lcmNum;
				int tmp = gcd(curr[0], curr[1]);
				if (tmp>1) {
					curr[0]/=tmp;
					curr[1]/=tmp;
				}
			}
			System.out.println(curr[1]);
		}
	}

    public static int gcd(int a, int b) {
		if (b==0) return a;
		return gcd(b, a%b);
	}
	
	public static int lcm(int a, int b) {
		return a*b/gcd(a, b);
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