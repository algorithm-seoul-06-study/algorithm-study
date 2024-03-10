package week7;

import java.util.*;
import java.util.stream.IntStream;

public class BOJ6087 {
    static int w, h, mn = Integer.MAX_VALUE; // min: 도착점에서의 거울 개수
    static char[][] map; // 맵
    static int[][][] mirrors; // mirrors[dir][r][c]: dir 방향일때의 (r, c)에서의 거울의 최소 개수; dir: 방향 (0: 하, 1: 우, 2: 상, 3: 좌)
    static List<Point> points = new ArrayList<>(); // 시작점과 도착점을 저장하는 리스트; index 0: 시작점, index 1: 도착점
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    // 위치와 방향, 거울의 개수를 저장하는 class
    static class Point {
        int r, c, dir, mir; // dir: 바라보고 있는 방향, mir: 위치에서의 거울 개수

        public Point(int r, int c, int dir, int mir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.mir = mir;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        w = sc.nextInt();
        h = sc.nextInt();

        map = new char[h][w];

        for (int i = 0; i < h; i++) {
            String line = sc.next();
            for (int j = 0; j < w; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'C') {
                    points.add(new Point(i, j, -4, -1));
                    // new Point(i, j, -4, -1)
                    // -4, -1 이렇게 설정한 이유는
                    // 시작점일 때는 반대 방향이 없기 때문에, 델타를 돌면서 모든 경우를 넣어야 한다.
                    // 하지만, 시작점이 아닐 때는 반대 방향을 갈 수 없다는 조건을 설정해주어야 한다.
                    // 하나뿐인 시작점을 위해 시작점에 대한 조건을 따로 추가하는 것보다
                    // 조건을 비켜나가는 값 (abs(cur.dir - dir) < 0 || abs(cur.dir - dir) > 3) (단, 0 <= dir <= 3) 을 만족하는 cur.dir 을 정하고,
                    // 이 경우라면 무조건 nMir은 mir + 1이기 떄문에, mir이 -1로 시작하면 시작점은 0이 된다.
                }
            }
        }

        Point start = points.get(0); // 시작점
        bfs(start);
        System.out.println(mn);
    }

    // 힙을 이용한 데이크스트라
    private static void bfs(Point start) {

        Point end = points.get(1); // 도착점
        PriorityQueue<Point> q = new PriorityQueue<>((p1, p2) -> p1.mir - p2.mir); // 거울의 개수를 오름차순으로 하는 힙

        mirrors = new int[4][h][w]; // 방향에 따른 위치에서의 거울 개수 배열
        // 모든 거울의 개수를 무한대(최대)로 채움
        IntStream.range(0, 4)
                .forEach(i -> {
                    Arrays.stream(mirrors[i])
                        .forEach(mirrors -> Arrays.fill(mirrors, Integer.MAX_VALUE));
                    }
                );

        // 탐색 시작
        q.add(start);
        while (!q.isEmpty()) {
            Point cur = q.remove();

            // 만약 도착점이라면, 거울의 개수를 갱신
            if (cur.r == end.r && cur.c == end.c) {
                mn = Math.min(mn, cur.mir);
                continue;
            }

            for (int dir = 0; dir < 4; dir++) { // dir: 다음 방향
                if (Math.abs(cur.dir - dir) == 2) continue; // 현재 방향과 정반대 방향이면 넘어감

                int nr = cur.r + dr[dir];
                int nc = cur.c + dc[dir];
                if (nr < 0 || nc < 0 || nr >= h || nc >= w) continue;
                if (map[nr][nc] == '*') continue;

                // 현재 방향과 다음 방향이 다르면, 거울을 하나 더 놓아야 함
                // 현재 방향과 다음 방향이 같으면, 거울을 더 놓을 필요 없어서 원래 거울 개수와 동일
                int nMir = (cur.dir != dir) ? cur.mir + 1 : cur.mir; // nMir: 다음 위치의 거울 개수

                // if (지금 적혀 있는 다음 방향 및 위치의 거울의 개수 > 다음으로 갈 위치의 거울 개수)
                // then 지금 적혀 있는 다음 방향 및 위치의 거울의 개수를 갱신 후, 힙에 갱신 내용을 push
                if (mirrors[dir][nr][nc] > nMir) {
                    mirrors[dir][nr][nc] = nMir;
                    q.add(new Point(nr, nc, dir, nMir));
                }
            }
        }
    }
}