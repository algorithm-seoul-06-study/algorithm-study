package week2;

import java.io.*;
import java.util.*;

public class BOJ17144 {

    static String[] line;
    static int R, C, T; // R = 맵의 행 수, C = 맵의 열 수, T = 시간
    static int[][] board; // 먼지 맵
    static int[] aPRows = new int[2]; // 공기청정기의 행, 두 개
    static Queue<Info> q = new LinkedList<>(); // 현재 시간에 확산할 먼지를 담는 큐
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static class Info { // 확산된 먼지 정보 클래스
        int r;
        int c;
        int amount;

        public Info(int r, int c, int amount) {
            this.r = r;
            this.c = c;
            this.amount = amount;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        line = br.readLine().split(" ");
        R = Integer.parseInt(line[0]);
        C = Integer.parseInt(line[1]);
        T = Integer.parseInt(line[2]);

        board = new int[R][C];
        int a = 0; // 공기청정기 인덱스
        for (int i = 0; i < R; i++) {
            line = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                board[i][j] = Integer.parseInt(line[j]);
                if (board[i][j] == -1) {
                    aPRows[a++] = i;
                }
            }
        }

        while (T-- > 0) {
            spread(); // 먼지 확산
            ventilate(); // 공기청정기 작동
        }

        int sum = 0; // T초 후의 전체 먼지량을 담는 변수
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == -1) continue;
                sum += board[i][j];
            }
        }
        System.out.println(sum);
    }

    /**
     * 먼지를 확산시키는 메서드<br>
     * 현재 시간 t에 먼지 맵을 순회하며, 먼지를 확산시킴<br>
     * 확산된 먼지는 큐에 넣어지고, 현재 먼지의 양을 확산된 먼지의 양만큼 뺌<br>
     * 먼지 확산 정보를 전부 담으면, 큐를 비워내면서 먼지 맵에 확산 정보를 넣어줌<br>
     * @see week2.BOJ17144#evaluateDust
     */
    private static void spread() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] <= 0) continue;
                board[i][j] -= evaluateDust(i, j, board[i][j]);
            }
        }

        while (!q.isEmpty()) {
            Info d = q.remove();
            board[d.r][d.c] += d.amount;
        }
    }

    /**
     * 현재 위치의 먼지의 양을 받아 4방향으로 먼지를 확산시키는 메서드<br>
     * 확산시킬 먼지 정보를 큐에 넣고, 확산된 먼지의 양을 반환해 현재 위치에 먼지를 빼줌
     * @param r : 현재 위치의 행
     * @param c : 현재 위치의 열
     * @param curAmount : 현재 위치의 먼지의 양
     * @return sum : 확산된 먼지의 양
     */
    private static int evaluateDust(int r, int c, int curAmount) {
        int sum = 0;
        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if (nr < 0 || R <= nr || nc < 0 || C <= nc) continue;
            if (board[nr][nc] == -1) continue;
            q.add(new Info(nr, nc, curAmount / 5));
            sum += curAmount / 5;
        }
        return sum;
    }

    /**
     * 공기청정기에 먼지가 들어오는 방향부터 나가는 방향까지 돌면서 먼지를 이동시키는 메서드
     */
    private static void ventilate() {
        // 반시계
        int north = aPRows[0];

        for (int r = north - 2; r >= 0; r--) {
            board[r + 1][0] = board[r][0];
        }

        for (int c = 1; c < C; c++) {
            board[0][c - 1] = board[0][c];
        }

        for (int r = 1; r <= north; r++) {
            board[r - 1][C - 1] = board[r][C - 1];
        }

        for (int c = C - 2; c >= 1; c--) {
            board[north][c + 1] = board[north][c];
        }
        board[north][1] = 0;

        //시계
        int south = aPRows[1];

        for (int r = south + 2; r < R; r++) {
            board[r - 1][0] = board[r][0];
        }

        for (int c = 1; c < C; c++) {
            board[R - 1][c - 1] = board[R - 1][c];
        }

        for (int r = R - 2; r >= south; r--) {
            board[r + 1][C - 1] = board[r][C - 1];
        }

        for (int c = C - 2; c >= 1; c--) {
            board[south][c + 1] = board[south][c];
        }
        board[south][1] = 0;
    }
}
