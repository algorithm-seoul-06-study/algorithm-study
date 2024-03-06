# Week 6
## BOJ1790 수 이어 쓰기 2
### 🎈 해결방법 :
1. k를 통해 찾고자 하는 숫자가 들은 수를 찾음.
   1. 10^(n - 1) * 9 * n = n자리 수가 가진 숫자의 개수 = tmp
   2. k를 tmp에 빼가면서, 위치를 찾음
2. 내가 찾고자 하는 숫자를 이어쓴 수에서 찾음
   1. 찾고자 하는 수 = 10^(n - 1) + k / len 값
3. 찾고자 하는 수가 n보다 크면, -1
4. 그렇지 않으면, 내가 찾는 숫자는 target의 (tmp를 뺀 k) % len 부분의 값이 됨.

### 💬 코멘트 :
어려웠다.

### 📄 코드
```java
package week6;

public class BOJ1790 {
   public static void main(String[] args) throws Exception {
      long n = read(); long k = read();

      long len = 1, exp = 1, tmp; // len: 자리 수, exp: 10의 거듭제곱, tmp: 뺼 숫자
      
      // k를 통해 찾고자 하는 숫자가 들은 수를 찾음
      while (k > (tmp = 9L * exp * len)) {
         k -= tmp;
         exp *= 10;
         len++;
      }
      // 현재 k는 자리 수가 len인 수로 부터 k만큼 떨어져 있음

      k--; // 맨 처음 인덱스를 0으로 만듦
      long target = exp + k / len; // target: 찾고자 하는 숫자가 들은 수

      if (target > n) System.out.println(-1);
      else System.out.println(String.valueOf(target).charAt((int) (k % len)));
   }

   static int read() throws Exception {
      int c, n = System.in.read() & 15;
      while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
      return n;
   }
}
```

## BOJ1992 쿼드트리
### 🎈 해결방법 :
1. 쿼드트리의 시작점과 크기를 정하고, 쿼드트리 내부의 모든 수가 같은지 확인
   1. 모든 수가 같다면, 그 수만 출력
   2. 아니면, 4개의 쿼드트리의 자식의 시작점과 크기를 결정 후 1번 반복

### 💬 코멘트 :
재귀를 구현하기 좋은 문제였다.

### 📄 코드
```java
package week5;

import java.io.*;

public class Main {
   static int n;
   static int[][] board;
   static StringBuilder sb = new StringBuilder();

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      n = Integer.parseInt(br.readLine());

      board = new int[n][n];

      for (int i = 0; i < n; i++) {
         String line = br.readLine();
         for (int j = 0; j < n; j++) {
            board[i][j] = line.charAt(j) - '0';
         }
      }

      quad(n, 0, 0); // 쿼드트리의 루트는 크기가 n이고 (0, 0)에서 시작
      System.out.println(sb.toString());
   }

   private static void quad(int size, int r, int c) {
      // 쿼드트리 내부의 모든 수가 같은지 확인
      if (isSingle(size, r, c)) {
         sb.append(board[r][c]);
         return;
      }

      int newSize = size / 2; // 사이즈를 절반 줄임

      sb.append("(");

      quad(newSize, r, c); // 크기가 n/2이고 (r, c)에서 시작
      quad(newSize, r, c + newSize); // 크기가 n/2이고 (r, c + n/2)에서 시작
      quad(newSize, r + newSize, c); // 크기가 n/2이고 (r + n/2, c)에서 시작
      quad(newSize, r + newSize, c + newSize); // 크기가 n/2이고 (r + n/2, c + n/2)에서 시작

      sb.append(")");
   }

   // 쿼드트리에 모든 수가 같은 지 확인하는 메서드
   private static boolean isSingle(int size, int r, int c) {
      int value = board[r][c];
      for (int i = 0; i < size; i++)
         for (int j = 0; j < size; j++)
            if (board[r + i][c + j] != value)
               return false;
      return true;
   }
}
```

## BOJ2011 암호코드
### 🎈 해결방법 :
1. $dp_{i}$ = $pw_{i}$ 까지 탐색했을 때 나올 모든 경우의 수
2. 1 ~ pw길이 까지 반복함
   1. 만약 $pw_{i}$이 0보다 크다면, $dp_{i}$ = $dp_{i-1}$ (무조건 이 자리는 셀 수 있음을 의미)
   2. i를 검사할 때의 $dp_{i}$는, $pw_{i-1}$에 따라 달라지기 때문에, $pw_{i-1}$ * 10 + $pw_{i}$를 구함
   3. 10 <= 구한 수 <= 26 라면, $dp_{i}$ = $dp_{i}$ + $dp_{i-2}$ (현재 자리 + 전전 자리)

### 💬 코멘트 :
시간이 빡빡해서 많이 헤맸다. 반복문 한번에 될 줄은 몰랐다.

### 📄 코드
```java
package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2011 {

   static int n, C = 1000000; // n: 암호 자리수, C = 나눌 수
   static int[] pw; // 암호문
   static int[] dp; // dp

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String s = br.readLine();

      n = s.length();
      pw = new int[n + 1];
      dp = new int[n + 1];
      for (int i = 1; i <= n; i++) pw[i] = s.charAt(i - 1) - '0';

      dp[0] = 1;

      for (int i = 1; i <= n; i++){
         if (pw[i] > 0) dp[i] = dp[i - 1] % C; // pw[i]가 0보다 크다는 건 무조건 이 자리는 셀 수 있음을 의미
         int x = pw[i - 1] * 10 + pw[i];
         // 이전 수(pw[i - 1])를 체크하는데 그 수가 10 ~ 26이면, 전전 자리의 경우의 수 + 현재 이 자리의 경우의 수
         if (x >= 10 && x <= 26) dp[i] = (dp[i] + dp[i - 2]) % C;
      }

      System.out.println(dp[n]);
   }
}
```