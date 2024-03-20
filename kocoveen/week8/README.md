# Week 8
## BOJ2636 치즈
### 🎈 해결방법 :
- **탐색(BFS 이용)**
1. 탐색을 하며 최외곽의 치즈를 체크해 따로 담음.
2. 1의 탐색이 끝나면 체크한 것을 세어 개수를 구함.
3. 체크한 치즈를 바탕으로 다시 1 수행
4. 치즈를 전부 찾을 때 까지 1, 2 반복. 더 이상 치즈를 찾지 못하면 끝
5. 마지막으로 기록된 치즈의 개수와 걸린 시간이 답

### 💬 코멘트 :
BFS를 이용한 문제였다. 치즈를 전부 구한 다음에 수를 구하려고 했지만, while 반복을 이용할 수 밖에 없어서 개수를 세는 마지막 순서를 찾지 못했다.
찾으려면 많은 if 문이 필요할 것 같아 처음부터 전에 센 개수를 덮어 씌우는 방식으로 구했다.

### 📄 코드
```java
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
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
```

## BOJ5904 Moo
### 🎈 해결방법 :
- **재귀**
1. Moo 수열을 $S$라고 하면, S의 길이가 int의 범위를 넘기 전까지 $S(n)$의 길이를 구함.
2. $S(k) = S(k - 1) + $"m" $+$ "o" * $(k + 2) + S(k - 1)$ 이고, $S(k)$는 세 부분으로 나눌 수 있음. 
   * $S(k - 1)$ || "m" $+$ "o" * $(k + 2)$ || $S(k - 1)$
3. 각 부분의 길이를 알기 때문에 각 부분의 첫 위치를 알 수 있음.
4. 각 부분의 첫 위치를 기준으로, 입력 숫자가 어느 부분에 들어오는 지 확인을 한 후에 각 부분에 맞게 조건문을 두어 재귀를 수행함.

### 💬 코멘트 :
오랜만에 풀어보는 재귀라 재밌었다.

### 📄 코드
```java
import java.util.Scanner;

public class BOJ5904 {

    static int k;     // 입력값
    static int[] arr; // Moo 수열의 길이를 저장할 배열

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();

        arr = new int[30];
        arr[0] = 0;
        arr[1] = 3;

        for (int i = 1; i < arr.length - 1; i++) {
            arr[i + 1] = arr[i] * 2 + i + 3;
        }

        int index = 30;
        int prevLen = arr[index - 1];
        System.out.println(func(k, index, 1, 1 + prevLen, 1 + prevLen + index + 2));

    }

    // Moo 수열의 k 위치를 구할 메서드
    // k: 입력 값, n: Moo 수열의 항, l_st: 왼쪽 부분의 첫 위치, m_st: 중간 부분의 첫 위치, l_st: 오른쪽 부분의 첫 위치
    static String func(int k, int n, int l_st, int m_st, int r_st) {

        // 항이 0 이면, 알파벳을 구함
        if (n == 0) {
            return getMoo(k, m_st);
        }

        String result;
        int prevLen = arr[n - 1]; // 직전 항의 길이를 구함

        
        if (k < m_st) {         // 만약 입력 값이 m_st 보다 작으면 -> 왼쪽 부분에 대해 func
            result = func(k, n - 1, l_st, l_st + prevLen, l_st + prevLen + n + 2);
        } else if (k < r_st) {  // 만약 입력 값이 r_st 보다 작으면 -> 중간 부분에서 알파벳을 구함
            return getMoo(k, m_st);
        } else {                // 그렇지 않으면 -> 오른쪽 부분에 대해 func
            result = func(k, n - 1, r_st, r_st + prevLen, r_st + prevLen + n + 2);
        }
        
        // 반환 결과를 리턴
        return result;


    }

    // 알파벳을 구하는 메서드
    static String getMoo(int n, int m_st) {
        if (n == m_st) return "m";
        return "o";
    }
}
```

## BOJ16227 의약품 수송
### 🎈 해결방법 :
- **정점 분할, 데이크스트라**
- 정점 분할: (정확한 의미 X, 코테를 위한 설명임) 임의의 정점까지의 최소 비용이 직전 정점의 상태에 의존한다면, 각 정점에 상태를 두어 각 상태마다 최소 비용을 저장하는 방식
1. 한 정점에서 임의의 정점까지 최단 가중치를 얻기 위해 데이크스트라(힙) 사용
2. 임의의 정점까지의 최소 비용은 *직전 정점의 상태*에 의존함.
따라서, 각 정점에 상태를 부여해 현재 상태에 따른 현재 정점까지의 최소 비용을 저장함. 
3. 종점에서 각 상태 중 최소값을 구함.

### 💬 코멘트 :
데이크스트라(힙)를 사용해서 풀었지만 힙을 이용하지 않는 풀이를 만들려다 본인의 능력 부족으로 대참사 발생.
힙 없는 데이크스트라로 풀어줄 천재 구함.

### 📄 코드
```java
import java.util.*;

public class BOJ16227 {

    static final int INF = 987654321;
    static int n, k, u, v, t, size;
    static List<Info>[] graph;

    static class Info {
        int idx, cost, mileage;
        public Info(int idx, int cost, int mileage) {
            this.idx = idx;
            this.cost = cost;
            this.mileage = mileage;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        size = n + 2;
        k = sc.nextInt();

        graph = new List[size];
        for (int i = 0; i < size; i++) {
            graph[i] = new ArrayList<>();
        }

        while (k-- > 0) {
            u = sc.nextInt();
            v = sc.nextInt();
            t = sc.nextInt();
            if (t > 100) continue;
            graph[u].add(new Info(v, t, 0));
            graph[v].add(new Info(u, t, 0));
        }

        int enCosts = dijkstra(0, n + 1, 100);
        System.out.println(enCosts);
    }

    private static int dijkstra(int st, int en, int maxTime) {
        int[][] costs = new int[size][maxTime + 1];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= maxTime; j++) {
                costs[i][j] = INF;
            }
        }
        costs[st][0] = 0;

        PriorityQueue<Info> pq = new PriorityQueue<>((i1, i2) -> i1.cost - i2.cost);
        pq.add(new Info(0, 0, 0));

        while (!pq.isEmpty()) {
            Info cur = pq.remove();

            int curIdx = cur.idx;
            int curMileage = cur.mileage;

            if (costs[curIdx][curMileage] < cur.cost) continue;

            for (Info nxt : graph[curIdx]) {
                int nxtIdx = nxt.idx;
                int nxtCost = nxt.cost;
                int nxtMileage = curMileage + nxtCost;

                if (maxTime >= nxtMileage) {
                    if (costs[nxtIdx][nxtMileage] > costs[curIdx][curMileage] + nxtCost) {
                        costs[nxtIdx][nxtMileage] = costs[curIdx][curMileage] + nxtCost;
                        pq.add(new Info(nxtIdx, costs[nxtIdx][nxtMileage], nxtMileage));
                    }
                } else {
                    nxtMileage = nxtCost;
                    if (costs[nxtIdx][nxtMileage] > costs[curIdx][curMileage] + nxtCost + 5) {
                        costs[nxtIdx][nxtMileage] = costs[curIdx][curMileage] + nxtCost + 5;
                        pq.add(new Info(nxtIdx, costs[nxtIdx][nxtMileage], nxtMileage));
                    }
                }

            }
        }

        int min = INF;
        for (int i = 0; i <= maxTime; i++) {
            min = Math.min(min, costs[en][i]);
        }
        return min;
    }
}
```