import java.io.*;
import java.util.*;

public class BOJ9251 {
	public static void main(String[] args) {
		FastReader fr = new FastReader();

		String line1 = fr.nextLine();
		String line2 = fr.nextLine();

		int[][] board = new int[line1.length() + 1][line2.length() + 1];

		for (int i = 1; i < line1.length() + 1; i++) {
			for (int j = 1; j < line2.length() + 1; j++) {
				if (line1.charAt(i - 1) == line2.charAt(j - 1)) {
					board[i][j] =  board[i-1][j-1] + 1;
				} else {
					board[i][j] = Math.max(board[i - 1][j], board[i][j - 1]);
				}

			}
		}

		System.out.println(board[line1.length()][line2.length()]);
	}
}

class FastReader {
	BufferedReader br;
	StringTokenizer st;

	public FastReader() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	String nextLine() {
		try {
			return br.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}