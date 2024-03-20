package week8;

import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ2636 {
    static int h, w, t, outerSize; // h: 행 크기, w: 열 크기, t: 시간, outerSize: 체크한 치즈를 센 개수
    static int[][] map;            // 맵
    static boolean[][] vis;        // 방문 체크 배열
    
    static Queue<Info> q = new ArrayDeque<>();     // 탐색을 할 큐
    static Queue<Info> outer = new ArrayDeque<>(); // 전에 체크한 치즈를 따로 담을 큐

    // 행과 열의 정보를 담을 클래스
    static class Info {
        int r, c;
        public Info(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        h = read(); w = read();
        map = new int[h][w];
        vis = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                map[i][j] = read();
            }
        }

        q.add(new Info(0, 0));  // 첫 방문 위치
        vis[0][0] = true;       // 첫 방문 위치에 방문 체크
        while (true) {
            q.addAll(outer); // 전에 찾은 치즈의 위치 정보를 탐색하는 큐에 담음
            outer.clear();   // 전에 찾은 치즈 삭제

            bfs(); // 탐색

            if (outer.isEmpty()) break; // 만약 전에 찾은 치즈가 존재하지 않는다면, 더 이상 찾을 치즈가 없다는 뜻 -> 반복문 탈출

            outerSize = outer.size();   // 전에 찾은 치즈 위치 정보의 개수를 저장
            t++; // 시간 증가
        }
        System.out.println(t);
        System.out.println(outerSize);
    }

    // BFS 탐색
    static void bfs() {
        while(!q.isEmpty()) {
            Info cur = q.remove();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (nr < 0 || nc < 0 || nr >= h || nc >= w) continue;
                if (vis[nr][nc]) continue;

                if (map[nr][nc] != 1) q.add(new Info(nr, nc)); // 치즈가 아니면 계속 탐색
                else outer.add(new Info(nr, nc));              // 치즈이면 치즈의 위치 정보를 따로 담을 배열에 저장
                vis[nr][nc] = true; // 방문 체크
            }
        }

    }

    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}