package week2;

import java.io.*;

public class BOJ1018 {

    static String[] ln;
    static int n, m; // n = 주어진 체스판의 행, m = 주어진 체스판의 열;
    static int min = Integer.MAX_VALUE; // min = 다시 칠할 블록의 최소 수
    static char[][] board; // 주어진 체스판
    static char[][][] correctBoard = new char[2][8][8]; // 옳게 된 체스판 : B가 먼저인 체스판, W가 먼저인 체스판

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ln = br.readLine().split(" ");
        n = Integer.parseInt(ln[0]);
        m = Integer.parseInt(ln[1]);

        board = new char[n][m];
        for (int i = 0; i < n; i++) {
            String l = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = l.charAt(j);
            }
        }

        // 옳게 된 체스판 채우기
        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    char color = (k + i + j) % 2 == 0 ? 'B' : 'W';
                    correctBoard[k][i][j] = color;
                }
            }
        }

        // 주어진 체스판에 시작 지점(i, j)에서 8X8로 된 체스판과 옳게 된 체스판을 비교
        for (int i = 0; i <= n - 8; i++) {
            for (int j = 0; j <= m - 8; j++) {
                min = Math.min(min, countIncorrect(i, j));
            }
        }
        System.out.println(min);
    }

    // 옳게 된 체스판을 돌면서, 다시 칠할 블록의 최소 수를 구함
    private static int countIncorrect(int r, int c) {
        int result = min;
        for (int k = 0; k < 2; k++) {
            int sum = 0;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (correctBoard[k][i][j] != board[r + i][c + j]) sum++;
                }
            }
            result = Math.min(result, sum);
        }
        return result;
    }
}
