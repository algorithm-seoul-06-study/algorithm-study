package week7;

import java.util.*;

public class BOJ9328 {

    static int t, h, w, document; // document: 문서 개수
    static char[][] map; // 맵
    static boolean[][] visited; // 방문 체크 배열
    static boolean[] isUnlocked; // A~Z의 문이 열렸는지(열쇠를 획득했는지) 체크하는 배열
    static Queue<Point> q; // BFS를 위한 큐
    static Queue<Point>[] doors = new Queue[26]; // 문이 열리지 않았을 때 방문 내용을 집어넣을 큐, 문이 열렸다면 doors의 내용을 q로 이전
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();
        while (t-- > 0) {
            h = sc.nextInt(); w = sc.nextInt();
            map = new char[h][w];
            visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                String line = sc.next();
                for (int j = 0; j < w; j++) {
                    map[i][j] = line.charAt(j);
                }
            }

            isUnlocked = new boolean[26];
            String line = sc.next();
            // 만약 열쇠가 있다면, 문을 여는 배열에 체크
            if (!line.equals("0")) {
                for (int i = 0; i < line.length(); i++) isUnlocked[line.charAt(i) - 'a'] = true;
            }

            for (int i = 0; i < 26; i++) doors[i] = new ArrayDeque<>();

            q = new ArrayDeque<>();
            document = 0;

            // 초기화: 가장자리에 있는 것들에 대해, 탐색 수행
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (!isBoundary(i, j)) continue; // 가장자리가 아니면 continue;
                    process(i, j); // map[r][c] 위치에 있는 내용에 대해, 알맞은 프로세스를 수행
                }
            }

            /* 없어도 동작하는 코드 : 없으면 시간과 공간 비용이 증가함
            for (int i = 0; i < 26; i++) {
                if (isUnlocked[i]) {
                    q.addAll(doors[i]);
                    doors[i].clear();
                }
            }
            */

            while (!q.isEmpty()) {
                Point cur = q.remove();

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];
                    if (nr < 0 || nc < 0 || nr >= h || nc >= w) continue;
                    if (visited[nr][nc]) continue;
                    process(nr, nc);
                }
            }
            System.out.println(document);
        }
    }

    // map[r][c] 위치에 있는 내용에 대해, 알맞은 프로세스를 수행하는 메서드
    private static void process(int r, int c) {
        if (map[r][c] == '*') return; // 벽일 때, 나가리

        if (map[r][c] == '.') { // 빈 공간일 때
            q.add(new Point(r, c)); // q에 추가
        }
        else if (map[r][c] == '$') { // document 일 때
            q.add(new Point(r, c)); // q에 추가
            document++; // document 증가
        }
        else if (isDoors(map[r][c])) { // 문일 때
            // 열렸으면, q에 추가
            if (isUnlocked[map[r][c] - 'A']) q.add(new Point(r, c));
            // 닫혔으면, doors에 추가
            else doors[map[r][c] - 'A'].add(new Point(r, c));
        }
        else if (isKeys(map[r][c])) { // 열쇠일 때
            isUnlocked[map[r][c] - 'a'] = true; // 문 열기
            q.add(new Point(r, c)); // q에 추가
            q.addAll(doors[map[r][c] - 'a']); // doors의 모든 내용을 q에 추가
            doors[map[r][c] - 'a'].clear(); // doors 내용 삭제
        }
        visited[r][c] = true; // 방문 체크
    }

    // key 인지 확인하는 메서드
    private static boolean isKeys(char c) {
        return Character.isLowerCase(c);
    }

    // door 인지 확인하는 메서드
    private static boolean isDoors(char c) {
        return Character.isUpperCase(c);
    }

    // 가장자리인지 확인하는 메서드
    private static boolean isBoundary(int r, int c) {
        return r == 0 || c == 0 || r == h - 1 || c == w - 1;
    }
}
