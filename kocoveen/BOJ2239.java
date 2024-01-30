//https://www.acmicpc.net/problem/2239

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2239 {

    static int fullNum = (1 << 9) - 1;

    static int[][] board = new int[9][9];
    static int[] rows = new int[9]; //현재 행에 숫자가 차있는지 비트로 표현
    static int[] cols = new int[9]; //현재 열에 숫자가 차있는지 비트로 표현
    static int[] areas = new int[9];
    // [0][1][2]
    // [3][4][5]
    // [6][7][8]

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                int num = line.charAt(j) - '0';
                board[i][j] = num;

                if (num == 0) continue;
                rows[i] |= 1 << (num - 1);
                cols[j] |= 1 << (num - 1);
                int area = ((i / 3) * 3) + (j / 3);
                areas[area] |= 1 << (num - 1);
            }
        }

        // 여기 백트래킹 구현부터 시작하면 됨.
        solve(0);
    }

    private static void solve(int num) {
        int i = num / 9;
        int j = num % 9;

        if (num == 81) {
            for (int[] ints : board) {
                for (int k : ints) {
                    System.out.printf("%d", k);
                }
                System.out.println();
            }
            System.exit(0);
        }

        if (board[i][j] != 0) {
            solve(num + 1);
            return;
        }

        int area = ((i / 3) * 3) + (j / 3);

        // 현재 행, 열, 구역에 있는 숫자 유무
        int cur = rows[i] | cols[j] | areas[area];

        // 있는 숫자를 제외한 현재 위치에 가능한 숫자들
        int possible = cur ^ fullNum;

        // 가능한 숫자가 없으면 return;
        if (possible == 0) {
            return;
        }

        for (int k = 0; k < 9; k++) {
            // 가능성 있는 수 중 가장 가까운 수 위치 구하기
            if (((possible >> k) & 1) == 1) {
                fill(i, j, k, area);
                solve(num + 1);
                unfill(i, j, k, area);
            }
        }
    }

    private static void unfill(int i, int j, int k, int area) {
        board[i][j] = 0;
        rows[i] ^= 1 << k;
        cols[j] ^= 1 << k;
        areas[area] ^= 1 << k;
    }

    private static void fill(int i, int j, int k, int area) {
        board[i][j] = (k + 1);
        rows[i] |= 1 << k;
        cols[j] |= 1 << k;
        areas[area] |= 1 << k;
    }
}
