# Week 5
## BOJ1182 부분수열의 합
### 🎈 해결방법 :
1. 부분 수열의 합의 처음 시작합을 0부터 시작함
2. depth를 더해가며 depth 위치의 수열을 더하거나 더하지 않거나
   1. depth가 N이 될 때, 내가 고른 부분 수열의 합이 S인지 확인
   2. S 이면 카운트 증가, 아니면 그냥 return;
3. 주어진 S가 0이 될 경우에는 공집합의 0이 있기 때문에 부분수열의 합에 꼭 0이 들어가는 것을 유의
   1. 따라서 주어진 S가 0 이면 카운트를 하나 뺌

### 💬 코멘트 :
오늘(수)를 들었다면, 아주아주 쉬운 문제였다. 물론 난 듣지 않았다.

### 📄 코드
```java
package week5;

public class BOJ1182 {

    static int n, s, cnt; // n: 정수 개수, s: 찾을 수, cnt: s 갯수
    static int[] arr; // 수열이 담긴 배열

    public static void main(String[] args) throws Exception {
        n = read(); s = read();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = read();

        // arr[0]부터 더할 부분 수열의 합을 저장하는 변수 sum 설정
        subsequenceSum(0, 0);
        if (s == 0) cnt--; // 공집합의 경우가 항상 포함되기 때문에, s가 0이라면 카운트 1개를 뺴줌
        System.out.println(cnt);
    }


    // depth: 수열의 위치를 나타내는 변수, sum: 현재 depth까지 더한 부분수열의 합
    private static void subsequenceSum(int depth, int sum) {
        if (depth == n) {
            if (sum == s) cnt++;
            return;
        }

        // 현재 arr[depth]을 더하거나
        subsequenceSum(depth + 1, arr[depth] + sum);
        // 현재 arr[depth]을 더하지 않거나
        subsequenceSum(depth + 1, sum);
    }

    public static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;
        if (isNegative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return isNegative ? ~n + 1 : n;
    }
}
```

## BOJ1932 정수 삼각형
### 🎈 해결방법 :
1. 밑의 수와 오른쪽 대각선의 수의 최고값과 현재 위치의 수를 더한 값을 저장할 테이블을 만듦.
2.
```math
  f(i, j) = \left\{
  \begin{matrix}
  0 & \text{if $i > n$} \\
   max( f(i + 1, j), f(i + 1, j + 1) ) + a_{ij} & \text{if $1 <= n <= i$} \\
  \end{matrix}
  \right.$$
```
3. 점화식이 i, j로 나왔으니 2차원 배열로 dp 생성
4. 점화식의 식대로 dp를 작성

### 💬 코멘트 :
DP는 외우자
1. 테이블에 어떤 값을 저장할 건지 생각하고, 테이블 만듦.
2. 테이블을 채워나가며, 규칙을 찾고 점화식을 세움.
3. 세운 점화식을 구현 (재귀적인 방법 or 반복적인 방법)

dp와 친해질 수 있는 문제, 재귀 반복 두 방법 모두 쓰길 권장
재귀와 반복 두 가지 dp 방법을 작성해놓았으니 볼거면 보세요.

### 📄 코드
```java
package week5;

public class BOJ1932 {

    static int n;
    static int[][] tri = new int[505][505]; // tri: 정수 삼각형 저장 배열
    static Integer[][] dp = new Integer[505][505]; // dp: dp[i][j] = dp[i][j] 까지 더해서 내려온 경로 중 가장 큰 값

    public static void main(String[] args) throws Exception {
        n = read();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                tri[i][j] = read();
            }
        }
        System.out.println(findMax(1, 1));
    }

    // findMax(int i, int j) : dp[i][j] 까지 더해서 내려온 경로 중 가장 큰 값
    public static int findMax(int i, int j) {
        if (i == n + 1) return 0; // 만약 i가 끝 행을 넘어갔다면, 0을 반환(멈추는 위치에 영향이 없는 값이아야 하기 떄문)
        if (dp[i][j] == null) dp[i][j] = Math.max(findMax(i + 1, j), findMax(i + 1, j + 1)) + tri[i][j];
        // f(i, j) = max( f(i + 1, j), f(i + 1, j + 1) ) + a_ij;
        return dp[i][j];
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
```

```java
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static String[] l;

    static int n;

    static int[][] arr;
    static Integer[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        dp = new Integer[n][n];

        for (int i = n - 1; i >= 0; i--) {
            l = br.readLine().split(" ");
            for (int j = 0; j < l.length; j++) {
                arr[i][j] = Integer.parseInt(l[j]);
            }
        }

        for (int i = 0; i < n; i++) {
            dp[0][i] = arr[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j + 1]) + arr[i][j];
            }
        }

        System.out.println(dp[n - 1][0]);
    }
}
```

## BOJ1987 알파벳
### 🎈 해결방법 :
1. 완전 탐색
2. 지나온 알파벳을 저장해, 발판을 지나기 전에 비교
   1. 지났으면 못 지나가도록 함
   2. 당연하지만 한번 지나온 곳을 다른 경로로 지나갈 수 있으니 지나온 발판은 원복
   3. 한 번 간 경로는 다시 가지 못함.

### 💬 코멘트 :
쉬운 탐색 문제, 비트 마스킹이나 탐색을 맛볼 수 있어서 좋았다.

### 📄 코드
```java
package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1987 {

    static int R, C, mx;
    static char[][] map; // 표 모양 보드
    static int visitedBit; // A~Z 를 지났는지 체크하는 비트
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] token = br.readLine().split(" ");
        R = Integer.parseInt(token[0]);
        C = Integer.parseInt(token[1]);

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        dfs(0, 0); // 0, 0 부터 시작
        System.out.println(mx);
    }

    private static void dfs(int r, int c) {
        int curVisitedBit = 1 << (map[r][c] - 'A');
        visitedBit |= curVisitedBit; // 현재 위치의 알파벳을 지났다고 체크
        mx = Math.max(mx, count(visitedBit)); // 현재 경로에서 지난 알파벳의 수와 비교
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || R <= nr || nc < 0 || C <= nc) continue;
            int nxtVisitedBit = 1 << (map[nr][nc] - 'A');
            if ((visitedBit & nxtVisitedBit) == nxtVisitedBit) continue; // 만약 지나갈 곳의 알파벳을 이미 지났다면 continue;
            visitedBit |= nxtVisitedBit; // 갈 곳의 알파벳 체크
            dfs(nr, nc); // 다음 스텝
            visitedBit &= (~curVisitedBit); // 현재 스텝이 끝났으면 방문 표시 해제
        }
    }

    // 지난 알파벳의 수를 계산
    private static int count(int visitedBit) {
        return Integer.bitCount(visitedBit);
    }
}
```

## BOJ9251 LCS
### 🎈 해결방법 :
1. 문자열 A의 i번째 문자를 $A_{i}$ 로 표현
2. 두 문자열의 부분 문자열인 $A_{i}$ 까지와 $B_{j}$ 까지의 최장 공통 부분 문자열을 적을 테이블을 만듦.
3. 
```math
   f(i, j) = \left\{
   \begin{matrix}
   max(f(i - 1, j), findMax(i, j - 1)) & \text{if $A_{i} != B_{j}$} \\
   f(i - 1, j - 1) + 1 & \text{if $A_{i} = B_{j}$} \\
   \end{matrix}
   \right.$$
```
4. 점화식이 i, j로 나왔으니 2차원 배열로 dp 생성
5. 점화식의 식대로 dp를 작성

### 💬 코멘트 :
DP는 외우자
1. 테이블에 어떤 값을 저장할 건지 생각하고, 테이블 만듦.
2. 테이블을 채워나가며, 규칙을 찾고 점화식을 세움.
3. 세운 점화식을 구현 (재귀적인 방법 or 반복적인 방법)

우리 클래스의 지능은 문제 유형을 외우는 것이 좋다.
재귀와 반복 두 가지 dp 방법을 작성해놓았으니 볼거면 보세요.

### 📄 코드
```java
// Recursive
package week5;

import java.util.Scanner;

import static java.lang.Math.*;

public class BOJ9251 {

    static char[] s1, s2;
    static Integer[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        s1 = sc.next().toCharArray();
        s2 = sc.next().toCharArray();

        dp = new Integer[s1.length][s2.length];
        System.out.println(findMax(s1.length - 1, s2.length - 1));
    }
    
    // Recursive
    private static Integer findMax(int i1, int i2) {
        if (i1 < 0 || i2 < 0) return 0;
        if (dp[i1][i2] == null) {
            if (s1[i1] != s2[i2]) dp[i1][i2] = max(findMax(i1 - 1, i2), findMax(i1, i2 - 1));
            else dp[i1][i2] = findMax(i1 - 1, i2 - 1) + 1;
        }
        return dp[i1][i2];
    }
}
```

```java
// Iterative
package week5;

import java.util.Scanner;

import static java.lang.Math.*;

public class Main {
    static char[] A, B;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();

        dp = new int[A.length + 1][B.length + 1];

        // Iterative
        for (int i = 1; i <= A.length; i++)
            for (int j = 1; j <= B.length; j++)
                if (A[i - 1] == B[j - 1]) dp[i][j] = dp[i - 1][j - 1] +  1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

        System.out.println(dp[A.length][B.length]);
    }
}
```