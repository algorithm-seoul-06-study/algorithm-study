import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17144 {
	static int[][] house;
	static List<int[]> cleaner;
	static int R;
	static int C;
	static int T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		house = new int[R][C];
		cleaner = new ArrayList<>();
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				int dust = Integer.parseInt(st.nextToken());
				if (dust == -1) {
					cleaner.add(new int[] { c, r });
				}
				house[r][c] = dust;
			}
		}

		for (int t = 0; t < T; t++) {
			int[][] tmpHouse = new int[R][C];
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					tmpHouse[r][c] += house[r][c];
					if (house[r][c] > 0) {
						spreadDust(c, r, tmpHouse);
					}
				}
			}
			house = tmpHouse;
			cleanTop(cleaner.get(0)[0], cleaner.get(0)[1]);
			cleanBottom(cleaner.get(1)[0], cleaner.get(1)[1]);
		}
		
		int total = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (house[r][c] > 0) {
					total += house[r][c];
				}
			}
		}
		System.out.println(total);
	}

	static void spreadDust(int x, int y, int[][] tmpHouse) {
		int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		int dustAmount = house[y][x];
		int spreaded = 0;
		for (int[] d : dir) {
			if ((x + d[0] >= 0 && x + d[0] < C) && (y + d[1] >= 0 && y + d[1] < R)) {
				if (house[y + d[1]][x + d[0]] != -1) {
					tmpHouse[y + d[1]][x + d[0]] += dustAmount / 5;
					spreaded++;
				}
			}
		}
		tmpHouse[y][x] -= dustAmount / 5 * spreaded;
	}

	static void cleanTop(int x, int y) {
		int tmp = house[y][C - 1];
		System.arraycopy(house[y], 1, house[y], 2, C - 2);
		house[y][1] = 0;
		int tmp3 = house[0][0];
		System.arraycopy(house[0], 1, house[0], 0, C - 1);
		int tmp2 = 0;
		for (int i = y - 1; i >= 0; i--) {
			tmp2 = house[i][C - 1];
			house[i][C - 1] = tmp;
			tmp = tmp2;
		}
		for (int i = 1; i <= y - 1; i++) {
			tmp2 = house[i][0];
			house[i][0] = tmp3;
			tmp3 = tmp2;
		}
	}

	static void cleanBottom(int x, int y) {
		int tmp = house[y][C - 1];
		System.arraycopy(house[y], 1, house[y], 2, C - 2);
		house[y][1] = 0;
		int tmp3 = house[R - 1][0];
		System.arraycopy(house[R - 1], 1, house[R - 1], 0, C - 1);
		int tmp2 = 0;
		for (int i = y + 1; i <= R - 1; i++) {
			tmp2 = house[i][C - 1];
			house[i][C - 1] = tmp;
			tmp = tmp2;
		}
		for (int i = R - 2; i > y; i--) {
			tmp2 = house[i][0];
			house[i][0] = tmp3;
			tmp3 = tmp2;
		}

	}
}
