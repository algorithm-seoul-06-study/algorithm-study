# Week 7
## BOJ1389 케빈 베이컨의 6단계 법칙
### 🎈 해결방법 :
1. 가중치가 같은 모든 간선에 대해 최단 거리를 정하기 위해 큐를 사용
2. 시작점에 대해서 bfs 수행
   1. 현재 값을 저장한 뒤, 큐에서 하나씩 꺼내 다음으로 이동
   2. 그래프의 가중치가 전부 1 + dfs 특성상, 가장 먼저 도착하는 것이 최소 거리(값) -> 다음 값의 거리를 비교할 필요가 없음
   3. 연결되어 있음 && 다음으로 갈 곳을 방문하지 않은 상태면, 다음 곳의 거리를 갱신
   4. bfs가 전부 끝나고 전체 거리를 return;

### 💬 코멘트 :
최단 경로 알고리즘 문제였다. 구현을 두 가지 방법으로 해보았다.
1. 데이크스트라 (완전 데이크스트라는 아님)
2. 플로이드 워셜

### 📄 코드
```java
package week7;

import java.util.*;

public class BOJ1389 {

    static int n, m;
    static int[][] graph;        // 그래프 (인접 행렬 형식)
    static int[] depth;          // num 이 가진 케빈 베이컨 수를 저장할 배열
    static int mn = 5051, mnIdx; // mn: 최소 케빈 베이컨 수, mnIdx: 최소 케빈 베이컨 수를 가진 사람

    public static void main(String[] args) throws Exception {
        n = read(); m = read();
        graph = new int[n + 1][n + 1];
        while (m-- > 0) {
            int a = read(), b = read();
            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            int kevin = bfs(q, i); 
            if (mn > kevin) { // 케빈 베이컨 합의 결과를 비교
                mn = kevin;
                mnIdx = i;
            }
        }
        System.out.println(mnIdx);
    }

    // bfs 수행
    static int bfs(Queue<Integer> q, int num) {
        depth = new int[n + 1];
        Arrays.fill(depth, -1); // 방문하지 않은 곳은 -1

        q.add(num); // 시작점 추가
        while (!q.isEmpty()) {
            int cur = q.remove();

            // n 사람 까지 전부 반복
            for (int nxt = 1; nxt <= n; nxt++) {
                // 그래프의 가중치가 전부 1 + dfs 특성상, 가장 먼저 도착하는 것이 최소 거리(값)임
                // if (cur과 nxt가 연결되어 있음 && nxt를 방문하지 않은 상태)
                if (graph[cur][nxt] > 0 && depth[nxt] < 0) {
                    q.add(nxt); // q에 추가
                    depth[nxt] = depth[cur] + 1; // nxt까지의 케빈 베이컨 거리 =  cur 까지의 거리 + 1
                }
            }
        }
        return Arrays.stream(depth).sum(); // 케빈 베이컨 거리 리턴
    }

    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
```
---
* 플로이드 워셜 방식
```java
package week7;

import java.util.*;

public class BOJ1389 {

    static int n, m;
    static List<Integer>[] graph;   // 그래프 (인접 리스트 형식)
    static int[][] dist;            // 가중치 인접 행렬
    static int mn = 5051, mnIdx;    // mn: 최소 케빈 베이컨 수, mnIdx: 최소 케빈 베이컨 수를 가진 사람

    public static void main(String[] args) throws Exception {
        n = read(); m = read();
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        while (m-- > 0) {
            int a = read(), b = read();
            graph[a].add(b);
            graph[b].add(a);
        }

        dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) dist[i][j] = mn;   // 최대값으로 채음
            for (int j : graph[i]) dist[i][j] = 1;          // 연결되어 있으면 1
            dist[i][i] = 0;                                 // 루프는 0
        }

        floyd_warshall();
        for (int i = 1; i <= n; i++) {
            int kevin = sum(i);
            if (mn > kevin) {
                mn = kevin;
                mnIdx = i;
            }
        }
        System.out.println(mnIdx);
    }

    // floyd warshall
    static void floyd_warshall() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    private static int sum(int i) {
        return Arrays.stream(dist[i]).sum();
    }

    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
```

## BOJ6087 레이저 통신
### 🎈 해결방법 :
1. dir 방향일때의 (r, c)에서의 거울의 최소 개수를 정의하는 3차원 배열 mirrors을 선언
2. 데이크스트라 알고리즘(힙 사용)을 이용하면서, mirrors[dir][r][c] 를 갱신
3. 도착점에 오면, 최소 거울 개수를 갱신

### 💬 코멘트 :
처음에는 2차원 배열을 이용해서 풀어봤는데, 메모리 초과가 나왔다. 
그래서 조건을 이리저리 바꿨지만, 계속 틀리고서 조건이 문제가 아니라는 것을 깨닫고 코드를 바꾸기로 했다.
3차원 배열은 생각치 못해서 구글링으로 로직을 보니 댄스 댄스 레볼루션이 생각났다.
3차원 배열로 구현하니 말도 안되게 데이크스트라처럼 생긴 코드가 나왔다. 

### 📄 코드
```java
package week7;

import java.util.*;
import java.util.stream.IntStream;

public class BOJ6087 {
    static int w, h, mn = Integer.MAX_VALUE;       // min: 도착점에서의 거울 개수
    static char[][] map;                           // 맵
    static int[][][] mirrors;                      // mirrors[dir][r][c]: dir 방향일때의 (r, c)에서의 거울의 최소 개수;
                                                   // dir: 방향 (0: 하, 1: 우, 2: 상, 3: 좌)
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

            for (int dir = 0; dir < 4; dir++) {             // dir: 다음 방향
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
```

## BOJ9328 열쇠
### 🎈 해결방법 :
1. BFS 탐색을 위한 큐 선언 및 가장자리에 있는 곳을 전부 검사
* 검사 로직
  1. 빈 공간 => BFS 큐에 위치 추가
  2. 문서 => 문서를 세고 BFS 큐에 위치 추가
  3. 문 => 열렸을 때는 BFS 큐에 위치 추가, 닫혔을 때는 문 큐에 따로 추가
  4. 열쇠 => 문을 열고 문 큐에 있는 모든 위치 Point를 BFS 큐로 이전
  5. 벽 => return;
2. BFS를 수행하면서 검사 로직 반복

### 💬 코멘트 :
문과 열쇠가 작동하는 방식을 구현하기가 어려웠다.

### 📄 코드
```java
package week7;

import java.util.*;

public class BOJ9328 {

    static int t, h, w, document;   // document: 문서 개수
    static char[][] map;            // 맵
    static boolean[][] visited;     // 방문 체크 배열
    static boolean[] isUnlocked;    // A~Z의 문이 열렸는지(열쇠를 획득했는지) 체크하는 배열
    static Queue<Point> q;          // BFS를 위한 큐
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
        if (map[r][c] == '*') return;   // 벽일 때, 나가리

        if (map[r][c] == '.') {         // 빈 공간일 때
            q.add(new Point(r, c));     // q에 추가
        }
        else if (map[r][c] == '$') {    // document 일 때
            q.add(new Point(r, c));     // q에 추가
            document++;                 // document 증가
        }
        else if (isDoors(map[r][c])) {  // 문일 때
            if (isUnlocked[map[r][c] - 'A']) q.add(new Point(r, c));    // 열렸으면, q에 추가
            else doors[map[r][c] - 'A'].add(new Point(r, c));           // 닫혔으면, doors에 추가
        }
        else if (isKeys(map[r][c])) {           // 열쇠일 때
            isUnlocked[map[r][c] - 'a'] = true; // 문 열기
            q.add(new Point(r, c));             // q에 추가
            q.addAll(doors[map[r][c] - 'a']);   // doors의 모든 내용을 q에 추가
            doors[map[r][c] - 'a'].clear();     // doors 내용 삭제
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
```