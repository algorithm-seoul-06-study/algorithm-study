package week6;

import java.io.*;

public class BOJ1992 {
    static int n;
    static int[][] board;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        quad(n, 0, 0);
        System.out.println(sb.toString());
    }

    private static void quad(int size, int r, int c) {
        if (isSingle(size, r, c)) {
            sb.append(board[r][c]);
            return;
        }

        int newSize = size / 2;

        sb.append("(");

        quad(newSize, r, c);
        quad(newSize, r, c + newSize);
        quad(newSize, r + newSize, c);
        quad(newSize, r + newSize, c + newSize);

        sb.append(")");
    }

    private static boolean isSingle(int size, int r, int c) {
        int value = board[r][c];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (board[r + i][c + j] != value)
                    return false;
        return true;
    }
}