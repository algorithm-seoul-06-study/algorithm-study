# Week 11
## BOJ1208 부분수열의 합 2
### 🎈 해결방법 :
- **분할정복**
1. 집합 arr을 두 개의 부분 집합으로 나누고, 각각의 집합은 L, R
2. 한 쪽의 부분 집합의 합을 key로 개수를 value로 하는 Map을 만들고, 부분 집합의 합을 기록함
3. 나머지 한 쪽의 부분 집합의 합을 구하고, 구하는 값 S에서 부분 집합의 합(sum) 을 뺀 (S - sum)을 key로 하는 value를 구한 전체 개수을 구함.

- 원래 부분 집합의 합을 구하면 최고 n 이 40이기 때문에, 2^40을 구해야 함.
- 하지만, 반 씩 나누면 2^20 + 2^20 = 2^21 이 되어 절반의 효율이 있음.

### 💬 코멘트 :
이분탐색이라고 나와있긴 한데 이분탐색으로 풀지 않아도 되는 것 같다.

### 📄 코드
```java
package week11;

import java.util.HashMap;
import java.util.Map;

public class BOJ1208 {

   static int n, s; // n : 원소 개수, s : 구할 값
   static long cnt; // cnt : 구할 값이 몇 개 있는지
   static int[] arr; // arr : 집합
   static Map<Integer, Integer> subSum = new HashMap<>(); // 두 개로 나눈 집합 중 1개의 부분 집합의 합을 기록함.
   // key : 부분 집합의 합, value : 개수

   public static void main(String[] args) throws Exception {
      n = read(); s = read();
      arr = new int[n];
      for (int i = 0; i < n; i++) arr[i] = read();

      rightPart(n / 2, 0);
      leftPart(0, 0);

      System.out.println(s == 0 ? cnt - 1 : cnt);
   }

   /**
    * 오른쪽의 부분 집합의 합을 구하고, subSum에 그 개수를 기록하는 메서드
    * @param mid : 인덱스 (오른쪽이니 중간부터 시작)
    * @param sum : 부분 집합의 합
    */
   private static void rightPart(int mid, int sum){
      if (mid == n){
         subSum.put(sum, subSum.getOrDefault(sum, 0) + 1);
         return;
      }
      rightPart(mid + 1, sum + arr[mid]);
      rightPart(mid + 1, sum);
   }

   /**
    * 왼쪽의 부분 집합의 합을 구하면서, 구하는 값 S에서 부분 집합의 합(sum) 을 뺀 (S - sum)을 key로 하는 value를 구한 전체 개수를 구함.
    * @param st : 인덱스 (오른쪽이니 중간부터 시작)
    * @param sum : 부분 집합의 합
    */
   private static void leftPart(int st, int sum){
      if (st == n / 2) {
         cnt += subSum.getOrDefault(s - sum, 0);
         return;
      }

      leftPart(st + 1, sum + arr[st]);
      leftPart(st + 1, sum);
   }

   private static int read() throws Exception {
      int c, n = System.in.read() & 15;
      boolean isNegative = n == 13;
      if (isNegative) n = System.in.read() & 15;
      while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
      return isNegative ? ~n + 1 : n;
   }
}
```

## BOJ23289 온풍기 안녕!
### 🎈 해결방법 :
- **구현**
1. 집에 있는 모든 온풍기에서 바람이 한 번 나옴
2. 온도가 조절됨
3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
4. 초콜릿을 하나 먹는다.
5. 조사하는 모든 칸의 온도가 K 이상이 되었는지 검사. 모든 칸의 온도가 K이상이면 테스트를 중단하고, 아니면 1부터 다시 시작한다.

### 💬 코멘트 :
6시간 대장정 끝에 풀었다. 근데 난 A형 못 땀ㅋㅋ

### 📄 코드
```java
package week11;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BOJ23289 {

   static int R, C, K, W, choco; // R : 행, C : 열, K : 온도, W : 벽 개수, choco : 먹은 초콜릿
   static int[][] heaterMap;     // 히터 및 측정 정보를 저장하는 맵
   static List<Info> heaters = new ArrayList<>(); // 히터 리스트
   static boolean[][][] wallMap; // 벽 정보, 0 벽과 1 벽이 있는지의 유무를 boolean으로
   static int[][] temperatureMap; // 온도 맵
   static List<Info> checkPlaces = new ArrayList<>(); // 측정 위치 정보
   
   // r, c 위치의 각종 정보를 저장하는 클래스
   // 히터면 k 는 히터의 바람 발사 방향 정보
   // 온도면 k 는 r, c 위치의 온도
   static class Info {
      int r, c, k;
      Info(int r, int c, int k) {
         this.r = r;
         this.c = c;
         this.k = k;
      }
   }

   // 히터 정보에 따른 벽을 탐색할 r 위치
   static int[][] wdr = {
           {-1, 0, 1},
           {-1, 0, 1},
           {-1, -1, -1},
           {1, 1, 1}
   };

   // 히터 정보에 따른 벽을 탐색할 c 위치
   static int[][] wdc = {
           {1, 1, 1},
           {-1, -1, -1},
           {-1, 0, 1},
           {-1, 0, 1}
   };

   // 사방 탐색을 위한 배열
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
               heaters.add(new Info(i, j, dir-1)); // 히터 정보 저장 (0의 인덱스를 가져오기 위해 1을 뻄)
            } else if (dir == 5) {
               checkPlaces.add(new Info(i, j, 0)); // 온도를 측정할 위치만을 담음
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
         turn_on();      //집에 있는 모든 온풍기에서 바람이 한 번 나옴
         convect();      //온도가 조절됨
         drop_outside(); //온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
         choco++;        //초콜릿을 하나 먹음
         if (check()) break; //조사하는 모든 칸의 온도가 K 이상이 되었는지 검사
      }
      System.out.println(choco);
   }

   /**
    * 모든 온풍기에서 바람을 발사하는 메서드 (BFS 이용)
    */
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

            // 온도가 1이면 넘어감
            if (cur.k == 1) continue;

            for (int i = 0; i < 3; i++) {
               // 히터 바람 발사 방향에 따른 탐색
               nr = cur.r + wdr[heater.k][i];
               nc = cur.c + wdc[heater.k][i];

               if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
               if (vis[nr][nc]) continue;
               if (block(cur.r, cur.c, heater.k, i-1)) continue; // 현재 위치에서 다음으로 갈 곳이 벽에 막혀있다면 넘어감

               temperatureMap[nr][nc] += (cur.k - 1); // 온도를 입력
               q.add(new Info(nr, nc, cur.k - 1));
               vis[nr][nc] = true;
            }
         }
      }
   }

   /**
    * 온도를 조절하는 메서드
    */
   private static void convect() {
      Queue<Info> q = new ArrayDeque<>();

      for (int r = 0; r < R; r++) {
         for (int c = 0; c < C; c++) {

            int t = temperatureMap[r][c]; // r, c 위치의 온도를 가져옴

            // 방향이 횡 한개, 종 한개인 이유는 한꺼번에 적용을 하기 위해 어딘가에 정보를 저장해야 하는데,
            // 그 과정에서 사방탐색을 하게 되면, 두 번 적용되는 결과가 됨.
            for (int dir = 1; dir <= 2; dir++) {
               int nr = r + dr[dir];
               int nc = c + dc[dir];

               if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
               if (block(r, c, dir, 0)) continue;

               int nt = temperatureMap[nr][nc]; // 근접 장소의 온도

               int dt = Math.abs(t - nt) / 4; // 변화할 온도

               // 온도는 높은 곳에서 낮은 곳으로 흐름
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

      // 담아둔 온도 변화를 전부 적용
      while (!q.isEmpty()) {
         Info cur = q.remove();
         temperatureMap[cur.r][cur.c] += cur.k;
      }
   }

   /**
    * 현재 위치에서 다음으로 갈 곳이 벽에 막혀있는 것을 검사하는 메서드
    * @param curR : 현재 행
    * @param curC : 현재 열
    * @param k : 바람 방향
    * @param i : 현재 위치로 부터 다음 위치를 찾는 변수
    * @return 막혔는지(true) 막히지 않았는지(false)
    */
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

   /**
    * 가장자리의 온도를 1씩 떨구는 메서드
    * 0이면 넘어감
    */
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

   /**
    * 온도 측정 위치의 모든 온도가 K임을 확인하는 메서드
    * @return 전부 K 이상(true), 그 외 (false)
    */
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
```