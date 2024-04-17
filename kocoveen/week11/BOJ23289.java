package week11;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BOJ23289 {

    static int R, C, K, W, choco;
    static int[][] heaterMap;
    static List<Info> heaters = new ArrayList<>();
    static boolean[][][] wallMap;
    static int[][] temperatureMap;
    static List<Info> checkPlaces = new ArrayList<>();
    static class Info {
        int r, c, k;
        Info(int r, int c, int k) {
            this.r = r;
            this.c = c;
            this.k = k;
        }
    }

    static int[][] wdr = {
            {-1, 0, 1},
            {-1, 0, 1},
            {-1, -1, -1},
            {1, 1, 1}
    };

    static int[][] wdc = {
            {1, 1, 1},
            {-1, -1, -1},
            {-1, 0, 1},
            {-1, 0, 1}
    };

    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        R = read(); C = read(); K = read();

        heaterMap = new int[R][C];
        wallMap = new boolean[R][C][2];
        temperatureMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int dir = read();
                if (0 < dir && dir < 5) {
                    heaterMap[i][j] = dir;
                    heaters.add(new Info(i, j, dir-1));
                } else if (dir == 5) {
                    checkPlaces.add(new Info(i, j, 0));
                }
            }
        }

        W = read();
        for (int i = 0; i < W; i++) {
            int r = read();
            int c = read();
            int t = read();
            wallMap[r - 1][c - 1][t] = true;
        }

        while (choco <= 100) {
            turn_on();
            convect();
            drop_outside();
            choco++;
            if (check()) break;
        }
        System.out.println(choco);
    }

    private static void turn_on() {
        Queue<Info> q = new ArrayDeque<>();

        for (Info heater : heaters) {
            boolean[][] vis = new boolean[R][C];
            int nr = heater.r + dr[heater.k];
            int nc = heater.c + dc[heater.k];
            temperatureMap[nr][nc] += 5;
            q.add(new Info(nr, nc, 5));
            vis[nr][nc] = true;

            while (!q.isEmpty()) {
                Info cur = q.remove();

                if (cur.k == 1) continue;

                for (int i = 0; i < 3; i++) {
                    nr = cur.r + wdr[heater.k][i];
                    nc = cur.c + wdc[heater.k][i];

                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                    if (vis[nr][nc]) continue;
                    if (block(cur.r, cur.c, heater.k, i-1)) continue;

                    temperatureMap[nr][nc] += (cur.k - 1);
                    q.add(new Info(nr, nc, cur.k - 1));
                    vis[nr][nc] = true;
                }
            }
        }
    }

    private static void convect() {
        Queue<Info> q = new ArrayDeque<>();

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {

                int t = temperatureMap[r][c];

                for (int dir = 1; dir <= 2; dir++) {
                    int nr = r + dr[dir];
                    int nc = c + dc[dir];

                    if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
                    if (block(r, c, dir, 0)) continue;

                    int nt = temperatureMap[nr][nc];

                    int dt = Math.abs(t - nt) / 4;

                    if (t > nt) {
                        q.add(new Info(r, c, -dt));
                        q.add(new Info(nr, nc, dt));
                    } else if (t < nt) {
                        q.add(new Info(r, c, dt));
                        q.add(new Info(nr, nc, -dt));
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            Info cur = q.remove();
            temperatureMap[cur.r][cur.c] += cur.k;
        }
    }

    private static boolean block(int curR, int curC, int k, int i) {
        switch (k) {
            case 0: {
                switch (i) {
                    case -1: {return wallMap[curR][curC][0] || wallMap[curR - 1][curC][1];}
                    case 0: {return wallMap[curR][curC][1];}
                    case 1: {return wallMap[curR + 1][curC][0] || wallMap[curR + 1][curC][1];}
                }
            }

            case 1: {
                switch (i) {
                    case -1: {return wallMap[curR][curC][0] || wallMap[curR - 1][curC - 1][1];}
                    case 0: {return wallMap[curR][curC - 1][1];}
                    case 1: {return wallMap[curR + 1][curC][0] || wallMap[curR + 1][curC - 1][1];}
                }
            }

            case 2: {
                switch (i) {
                    case -1: {return wallMap[curR][curC - 1][1] || wallMap[curR][curC - 1][0];}
                    case 0: {return wallMap[curR][curC][0];}
                    case 1: {return wallMap[curR][curC][1] || wallMap[curR][curC + 1][0];}
                }
            }

            case 3: {
                switch (i) {
                    case -1: {return wallMap[curR][curC - 1][1] || wallMap[curR + 1][curC - 1][0];}
                    case 0: {return wallMap[curR + 1][curC][0];}
                    case 1: {return wallMap[curR][curC][1] || wallMap[curR + 1][curC + 1][0];}
                }
            }
        }
        return false;
    }

    private static void drop_outside() {
        for (int i = 0; i < C; i++) {
            if (temperatureMap[0][i] > 0) temperatureMap[0][i]--;
            if (temperatureMap[R-1][i] > 0) temperatureMap[R-1][i]--;
        }

        for (int i = 1; i < R-1; i++) {
            if (temperatureMap[i][0] > 0) temperatureMap[i][0]--;
            if (temperatureMap[i][C-1] > 0) temperatureMap[i][C-1]--;
        }
    }

    private static boolean check() {
        for (Info place : checkPlaces) {
            if (temperatureMap[place.r][place.c] < K) return false;
        }
        return true;
    }

    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}