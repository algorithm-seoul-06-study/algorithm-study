# Week 12
## BOJ1647 도시 분할 계획
### 🎈 해결방법 :
- **크루스칼**
1. 크루스칼로 거리의 합의 최소비용을 구함.
2. 그 중 가장 거리가 큰 값을 뺌.

### 💬 코멘트 :
크루스칼을 연습하기 좋은 문제이다.

### 📄 코드
```java
package week12;

import java.util.PriorityQueue;

public class BOJ1647 {

    static int n, m, mx, ans;

    static class Info {
        int a, b, c;
        Info(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    static int[] p;
    static PriorityQueue<Info> pq = new PriorityQueue<>((i1, i2) -> i1.c - i2.c);

    public static void main(String[] args) throws Exception {
        n = read(); m = read();
        while (m-- > 0) {
            int a = read();
            int b = read();
            int c = read();
            pq.add(new Info(a, b, c));
        }

        make_set();

        while (!pq.isEmpty()) {
            Info info = pq.remove();

            int ap = find(info.a);
            int bp = find(info.b);

            if (ap != bp) {
                union(ap, bp);
                ans += info.c; // 거리의 합을 구함.
                mx = Math.max(mx, info.c); // 그 중 최대를 구함.
            }
        }
        System.out.println(ans - mx); // 거리의 합에서 최대 거리를 뺌
    }


    private static void make_set() {
        p = new int[n + 1];
        for (int i = 1; i <= n; i++) p[i] = i;
    }

    private static void union(int up, int vp) {
        p[up] = vp;
    }

    private static int find(int u) {
        if (p[u] == u) return u;
        return p[u] = find(p[u]);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
```

## BOJ16235 나무 재테크
### 🎈 해결방법 :
- **구현**
1. 양분 먹기 + 나이 증가 (죽을 나무는 양분을 먹지 못하고 죽음!!)
2. 죽은 나무 양분 변화
3. 나무 번식
4. 땅에 양분 추가
5. 남아 있는 나무의 수 출력

### 💬 코멘트 :
한국말이 가장 어렵다. 죽은 나무의 양분을 전부 더해서 나누라는 건지 하나씩 나누라는 건지...

### 📄 코드
```java
package week12;

import java.util.PriorityQueue;

public class BOJ16235 {

    static int n, m, k; // n : 크기; m : 심은 나무 정보 수; k : 측정 연수
    static int[][] food, A; // food[][] : 현재 양분 정보; A[][] : 양분 주입량

    static PriorityQueue<Integer>[][] tree, dead; 
    // tree : 산 나무 -> 나이 오름차순
    // dead : 죽은 나무

    // 팔방 탐색을 위한 배열
    static int[] dr = {1, 1, 0, -1, -1, -1, 0, 1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws Exception {
        n = read(); m = read(); k = read();

        food = new int[n][n];
        A = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                food[i][j] = 5;
                A[i][j] = read();
            }
        }

        tree = new PriorityQueue[n][n];
        dead = new PriorityQueue[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tree[i][j] = new PriorityQueue<>();
                dead[i][j] = new PriorityQueue<>();
            }
        }

        for (int i = 0; i < m; i++) {
            int r = read() - 1;
            int c = read() - 1;
            int a = read();
            tree[r][c].add(a);
        }

        while (k-- > 0) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 양분을 받은 나무는 tmp에 들어가고, 죽은 나무는 tree에 남아 있음
                    PriorityQueue<Integer> tmp = new PriorityQueue<>();
                    while (!tree[i][j].isEmpty()) {
                        int cur = tree[i][j].remove();
                        if (food[i][j] >= cur) {
                            food[i][j] -= cur;
                            tmp.add(cur + 1);
                        } else {
                            tree[i][j].add(cur);
                            break;
                        }
                    }
                    dead[i][j].addAll(tree[i][j]); // 남아 있는 나무는 죽었기 때문에 dead에 전부 넣음
                    tree[i][j].clear(); // 현재 나무의 pq를 비움
                    tree[i][j].addAll(tmp); // 산 나무들을 다시 tree에 넣음
                }
            }

            // 죽은 나무가 변환될 양분만큼 양분의 양을 증가시킴.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    while (!dead[i][j].isEmpty()) {
                        int cur = dead[i][j].remove(); // 죽은 나무를 꺼내서
                        food[i][j] += (cur / 2); // 넣음
                    }
                }
            }

            // 나무가 번식함
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 각 위치의 나무들을 꺼냄
                    for (int t : tree[i][j]) {
                        // 나무가 5의 배수이면 (0은 들어가지 않기 때문에 0에 대한 조건은 없어도 됨)
                        if (t % 5 == 0) {
                            // 팔방으로 번식
                            for (int k = 0; k < 8; k++) {
                                int nr = dr[k] + i;
                                int nc = dc[k] + j;
                                if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
                                tree[nr][nc].add(1);
                            }
                        }
                    }
                }
            }

            // 로봇이 땅에 양분을 줌
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    food[i][j] += A[i][j];
                }
            }
        }

        // 살아남은 나무의 개수를 더함
        int size = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                size += tree[i][j].size();
            }
        }
        System.out.println(size);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
```