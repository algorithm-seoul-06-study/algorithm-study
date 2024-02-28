import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1932 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[][] tri = new int[n][n];

		for (int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j <= i; j++) {

				tri[i][j] = Integer.parseInt(line[j]);

			}
		}
		int max = -1;
		if (n > 1) {
			tri[1][0] += tri[0][0];
			tri[1][1] += tri[0][0];

			for (int i = 2; i < n; i++) {
				for (int j = 0; j <= i; j++) {
					if (j == 0) {
						tri[i][j] += tri[i - 1][j];
					} else {
						tri[i][j] = Math.max(tri[i][j] + tri[i - 1][j], tri[i][j] + tri[i - 1][j - 1]);
					}
				}

			}

			for (int i = 0; i < n; i++) {

				if (max < tri[n - 1][i]) {
					max = tri[n - 1][i];
				}
			}

		} else {
			max = tri[0][0];
		}
		System.out.println(max);
	}
}