# Week 9
## BOJ1753 최단경로
### 🎈 해결방법 :
- **데이크스트라**
1. 현재 정점 체크
2. 힙에 현재 정점과 거리 삽입
3. 가장 작은 거리를 가진 노드(정점 + 거리)가 선택되면, 그 정점에서 갈 수 있는 곳 중에 가장 작은 거리의 정점 선택
4. 적혀진 선택된 정점에서 다음에 갈 정점까지의 거리 > 현재 선택된 정점까지의 거리 + 선택된 정점에서 다음에 갈 정점까지의 거리
   를 만족하면, 거리 갱신 후 힙에 삽입
5. 힙이 빌 때까지 2~4 반복

### 💬 코멘트 :
담백한 데이크스트라를 구현하는 문제 

### 📄 코드
```java
package week9;

import java.io.*;
import java.util.*;

public class BOJ1753 {
   static int V, E, start; // V: 정점 수, E: 간선 수, start: 시작 정점
   static ArrayList<ArrayList<Node>> graph; // 그래프

   // idx: 다음 노드 인덱스, cost: 다음 노드로 가는데 필요한 비용
   static class Node {
      int idx, cost;

      Node(int idx, int cost) {
         this.idx = idx;
         this.cost = cost;
      }
   }

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      V = Integer.parseInt(st.nextToken());
      E = Integer.parseInt(st.nextToken());
      start = Integer.parseInt(br.readLine());

      graph = new ArrayList<>();
      for (int i = 0; i < V + 1; i++) {
         graph.add(new ArrayList<>());
      }

      for (int i = 0; i < E; i++) {
         st = new StringTokenizer(br.readLine());
         int u = Integer.parseInt(st.nextToken());
         int v = Integer.parseInt(st.nextToken());
         int w = Integer.parseInt(st.nextToken());

         graph.get(u).add(new Node(v, w));
      }

      int[] dist = dijkstra();

      // 결과 출력
      for (int i = 1; i <= V; i++) {
         if (dist[i] == Integer.MAX_VALUE) {
            System.out.println("INF");
         } else {
            System.out.println(dist[i]);
         }
      }
   }

   private static int[] dijkstra() {
      int[] dist = new int[V + 1]; // 최소 비용을 저장할 배열
      for (int i = 1; i <= V; i++) {
         dist[i] = Integer.MAX_VALUE;
      }
      // 최소 비용 추출 큐
      PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

      // 시작 정점 삽입
      q.offer(new Node(start, 0));

      // 처음 노드는 0의 거리
      dist[start] = 0;

      while (!q.isEmpty()) {
         Node curNode = q.remove();

         // 목표 정점 도착시 바로 탈출하는 코드, but 여기는 전체 정점의 최소 거리를 구함
//			if (curNode.idx == end) {
//				System.out.println(dist[curNode.idx]);
//				return;
//			}

         // 현재 선택된 정점까지의 거리가 이미 적혀진 정점까지의 거리보다 크면, 선택된 거리는 버림
         if (dist[curNode.idx] < curNode.cost) continue;

         // 선택된 정점에서 갈 수 있는 곳 중에 가장 작은 거리의 정점 선택
         for (Node nxtNode : graph.get(curNode.idx)) {
            // 적혀진 선택된 정점에서 다음에 갈 정점까지의 거리 > 현재 선택된 정점까지의 거리 + 선택된 정점에서 다음에 갈 정점까지의 거리
            if (dist[nxtNode.idx] > curNode.cost + nxtNode.cost) {
               // 갱신
               dist[nxtNode.idx] = curNode.cost + nxtNode.cost;
               // 갱신된 경우에만 큐에 삽입
               q.offer(new Node(nxtNode.idx, dist[nxtNode.idx]));
            }
         }
      }

      return dist;
   }
}
```

## BOJ17281 ⚾
### 🎈 해결방법 :
- **백트래킹 + 구현**
1. 백트래킹으로 1~9번 타자의 타순 조합을 정함
2. 그 타순으로 n 이닝까지의 득점을 구함

### 💬 코멘트 :
야구, 그것은 고통

### 📄 코드
```java
package week9;

public class BOJ17281 {

   static int n;
   static int[][] players; // players[i][j]: i+1 이닝에 j번 타자가 얻는 결과;
   // 0 -> 아웃, 1 -> 안타, 2 -> 2루타, 3 -> 3루타, 4 -> 홈런
   static int[] battingOrder = new int[9]; // 타순
   static boolean[] visited = new boolean[9]; // 타자 방문 체크
   static int mxRun; // 최대 점수

   public static void main(String[] args) throws Exception {
      n = read();
      players = new int[n][9];
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < 9; j++) {
            players[i][j] = read();
         }
      }

      visited[0] = true;
      backtracking(0); // zero index
      System.out.println(mxRun);
   }

   private static void backtracking(int depth) {
      if (depth == 9) {
         int run = getRunByCurrentBattingOrder(battingOrder);
         mxRun = Math.max(mxRun, run);
         return;
      }

      // 4번 타자일 때는 넘어감
      if (depth == 3) {
         dfs(depth + 1);
         return;
      }

      for (int i = 1; i < 9; i++) {
         if (visited[i]) continue;
         visited[i] = true;
         battingOrder[depth] = i;
         dfs(depth + 1);
         visited[i] = false;
      }

   }

   /**
    * 현재 타순(battingOrder)으로 n이닝 까지의 점수를 구하는 메서드
    * @param battingOrder : 타순
    * @return run : 점수
    */
   static int getRunByCurrentBattingOrder(int[] battingOrder) {
      int run = 0; // 점수
      int out; // 아웃
      int idx = 0; // 게임 첫 타자

      for (int inning = 0; inning < n; inning++) {
         out = 0; // 아웃 초기화
         int base = 0; // 3루, 2루, 1루, 홈 상태 초기화

         // 3아웃이 되기 전까지
         while (out < 3) {
            int batting = players[inning][battingOrder[idx]];
            if (batting == 0) {
               out++;
            } else {
               base = (base + 1) << batting; // 루타만큼 비트 이동
               run += Integer.bitCount(base >> 4); // 뒤에서 4번째 비트보다 큰 비트들의 1을 세어, 점수에 합
               base %= (1 << 4); // 뒤에서 4번째 비트보다 왼쪽의 비트(수가 큰 비트)는 전부 0으로 바꿈
            }
            // 다음 타자로
            idx = (idx + 1) % 9;
         }
      }
      return run;
   }

   static int read() throws Exception {
      int c, n = System.in.read() & 15;
      while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
      if (c == 13) System.in.read();
      return n;
   }
}
```

## SOF7649 효도 여행
### 🎈 해결방법 :
- **DFS + LCS**
1. DFS 방식으로 여행 루트를 작성함.
2. 여행지를 탐색할 때마다 LCS 실행 
(DFS의 특성 상, 한 방향으로 계속 탐색하기 때문에 이전 경로의 LCS를 활용할 수 있음.)

### 💬 코멘트 :
DFS의 특성을 이용하면, 현재 탐색의 이전 경로의 LCS를 통해 현재 탐색의 LCS를 O(n)으로 구할 수 있는 것이 핵심이었다.

### 📄 코드
```java
package week9;

import java.io.*;
import java.util.*;

public class SOF7649 {
   static int n, m, mx; // mx: 최대 행복 지수
   static String S; // S: 가장 좋아하시는 여행 경로
   static List<Info>[] graph;
   static boolean[] vis; // 백트래킹 방문 체크

   // e: 여행 장소, r: 여행 루트
   static class Info {
      int e;
      String r;

      Info(int e, String r) {
         this.e = e;
         this.r = r;
      }
   }

   static int[][] dp; // LCS를 위한 dp 배열

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;

      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());

      S = br.readLine();

      dp = new int[S.length() + 1][n];

      graph = new List[n];
      vis = new boolean[n];
      for (int i = 0; i < n; i++) {
         graph[i] = new ArrayList<>();
      }

      for (int i = 0; i < n - 1; i++) {
         st = new StringTokenizer(br.readLine());
         int u = Integer.parseInt(st.nextToken()) - 1;
         int v = Integer.parseInt(st.nextToken()) - 1;
         String r = st.nextToken();

         graph[u].add(new Info(v, r));
         graph[v].add(new Info(u, r));
      }

      vis[0] = true;
      backtracking(0, new StringBuilder());

      System.out.println(mx);
   }

   static void backtracking(int cur, StringBuilder B) {
      // 현재 장소에서 더 이상 갈 곳이 없다면
      if (isAllVisited(cur)) {
         return;
      }

      for (Info nxt : graph[cur]) {
         if (vis[nxt.e]) continue;

         vis[nxt.e] = true;

         StringBuilder tmp = B.append(nxt.r); // 여행 루트 추가

         mx = Math.max(mx, LCS(tmp.toString(), tmp.length())); // 여행 루트로 LCS 실행 후, 비교
         backtracking(nxt.e, tmp);

         B.deleteCharAt(B.length() - 1); // 최근에 간 여행 루트 삭제

         vis[nxt.e] = false;
      }
   }

   /**
    * 현재 cur 장소에서 갈 곳이 있는지 확인하는 메서드
    * @param cur : 현재 여행 장소
    * @return true : 갈 곳 존재, false: 갈 곳 부재
    */
   static boolean isAllVisited(int cur) {
      for (Info nxt : graph[cur]) {
         if (!vis[nxt.e]) return false;
      }
      return true;
   }

   /**
    * 가장 좋아하는 여행 루트와 현재 고른 여행 루트를 LCS하는 메서드
    * @param B : 여행 루트
    * @param j : B.length()
    * @return dp[A.length()][B.length()]
    */
   static int LCS(String B, int j) {
      for (int i = 1; i <= S.length(); i++) {
         if (S.charAt(i-1) == B.charAt(j-1)) dp[i][j] = dp[i - 1][j - 1] + 1;
         else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
      }
      return dp[S.length()][B.length()];
   }
}

```