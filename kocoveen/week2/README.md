# Week 2
## BOJ1018 체스판 다시 칠하기
### 🎈 해결방법 :
1. 옳게 된 체스판(B가 먼저, W가 먼저인 8X8 체스판) 두 개를 만듦.
2. 입력받은 체스판과 옳게 된 체스판을 위치에 맞게 비교
3. 비교한 두 체스판의 각각 다른 곳의 합의 최대값을 찾아 반환

### 💬 코멘트 :
옳게 된 체스판을 만드는 것 빼고는 각각 비교하는 문제라서 어렵지 않았다.

### 📄 코드
```java
import java.io.*;

public class BOJ1018 {

    static String[] ln;
    static int n, m; // n = 주어진 체스판의 행, m = 주어진 체스판의 열;
    static int min = Integer.MAX_VALUE; // min = 다시 칠할 블록의 최소 수
    static char[][] board; // 주어진 체스판
    static char[][][] correctBoard = new char[2][8][8]; // 옳게 된 체스판 : B가 먼저인 체스판, W가 먼저인 체스판

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ln = br.readLine().split(" ");
        n = Integer.parseInt(ln[0]);
        m = Integer.parseInt(ln[1]);

        board = new char[n][m];
        for (int i = 0; i < n; i++) {
            String l = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = l.charAt(j);
            }
        }

        // 옳게 된 체스판 채우기
        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    char color = (k + i + j) % 2 == 0 ? 'B' : 'W';
                    correctBoard[k][i][j] = color;
                }
            }
        }

        // 주어진 체스판에 시작 지점(i, j)에서 8X8로 된 체스판과 옳게 된 체스판을 비교
        for (int i = 0; i <= n - 8; i++) {
            for (int j = 0; j <= m - 8; j++) {
                min = Math.min(min, countIncorrect(i, j));
            }
        }
        System.out.println(min);
    }

    // 옳게 된 체스판을 돌면서, 다시 칠할 블록의 최소 수를 구함
    private static int countIncorrect(int r, int c) {
        int result = min;
        for (int k = 0; k < 2; k++) {
            int sum = 0;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (correctBoard[k][i][j] != board[r + i][c + j]) sum++;
                }
            }
            result = Math.min(result, sum);
        }
        return result;
    }
}
```

## BOJ1107 리모컨
### 🎈 해결방법 :
1. 목적 채널을 가기 위해 거쳐가야 할 채널이 있다고 가정
   1. 버튼을 눌러야 할 최소 수를 가장 최대로 눌러야할 횟수로 잡음
   2. 최대 999999로 잡은 이유는 숫자 버튼은 9까지고, 눌러야 할 최대 횟수는 6번이 최대이기 때문
2. 0 ~ 999999 까지 반복문
   1. 눌러야 할 버튼의 최소 수 = min(|100 - 최종 채널|, 채널번호의 길이 + |거쳐갈 채널 - 최종 채널|)

### 💬 코멘트 :
예전에 한 번 풀었는데, 처음에 접근 방식이 기억이 나질 않았다.<br>
엄밀하지 못한 풀이는 기억에 빠르게 잊혀진다는 것을 깨달았다.

### 📄 코드
```java
import java.io.*;

public class BOJ1107 {

    static int n, m; // n = 가야할 채널, m = 고장난 버튼 수
    static int broken; // 고장난 버튼 번호를 bit로 저장

    public static void main(String[] args) throws IOException {
        n = read();
        m = read();
        for (int i = 0; i < m; i++) {
            broken |= 1 << read();
        }

        int min = Math.abs(n - 100); // 버튼을 눌러야 할 최소 수를 가장 최대로 눌러야 할 횟수로 잡음
        // 0 ~ 999999 의 채널에서 거쳐갈 채널(이하 i)을 찾음
        // 최대 999999로 잡은 이유는 숫자 버튼은 9까지고, 눌러야 할 최대 횟수는 6번이 최대이기 때문
        for (int i = 0; i <= 999999; i++) {
            String channel = Integer.toString(i);

            // i를 거쳐갈 수 있는지 체크
            boolean isBlocked = false;
            for (int j = 0; j < channel.length(); j++) {
                int button = channel.charAt(j) - '0';
                if ((broken & (1 << button)) == (1 << button)) {
                    isBlocked = true;
                    break;
                }
            }

            // 만약 버튼이 고장나 그 채널을 거치치 못하면,
            //      다음으로 넘어감
            if (isBlocked) continue;

            // 고장이 나지 않아 채널을 거쳐갈 수 있다면,
            //      min(채널번호의 길이 + |거쳐갈 채널 - 최종 채널|)
            int cnt = channel.length() + Math.abs(n - i);
            min = Math.min(min, cnt);
        }
        System.out.println(min);
    }

    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
```

## BOJ2156 포도주 시식
### 🎈 해결방법 :
1. 현재 위치(i)에서 먹을 수 있는 최대값을 가지는 dp 테이블을 만듦.
2. i에서 선택권은 세 가지
   1. i-1 시음 + i-3 시음
   2. i-2 시음
   3. i 시음 X
3. 위 세가지 조건 중 가장 큰 값으로 하는 값이 dp값

### 💬 코멘트 :
i-1의 포도주를 그냥 재귀호출하면 i-2가 이전 포도주를 먹었는지 알 수 있는 방법이 없다는 주의점이 인상 깊은 문제

### 📄 코드
```java
public class BOJ2156 {
    static int[] arr; // 포도주가 있는 위치
    static Integer[] dp;

    public static void main(String[] args) throws Exception {
        int n = read();
        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = read();
        }

        dp = new Integer[n + 1];
        System.out.println(func(n));
    }

    // dp[i]를 구하는 메서드 (3연속 먹지 못함)
    public static int func(int i) {
        if (i <= 0) return 0; // 경계조건 1
        if (i == 1) return arr[1]; // 경계조건 2

        if (dp[i] == null) {
            dp[i] = max(
                    func(i - 2) + arr[i], // 포도주[i]를 먹을 수 있고, 한 칸을 건너 뛴 경우
                    func(i - 3) + arr[i - 1] + arr[i], // 포도주[i]를 먹고, 인접한 포도주[i-1]를 먹어 포도주[i-3]을 먹는 경우
                    func(i - 1) // 포도주[i]를 못 먹는 경우
            );
        }
        return dp[i];
    }

    // 최대를 구하는 메서드
    private static int max(int... nums) {
        int max = 0;
        for (int num : nums) max = Math.max(max, num);
        return max;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
```

## BOJ17144 미세먼지 안녕!
### 🎈 해결방법 :
1. 먼지를 확산시킨다.<br>
(단, 먼지를 차례로 확산할 때 맵에 바로 더하면 다음 먼지 확산에 영향을 주기 때문에, 정보를 저장한 후 한 번에 계산함)
2. 먼지를 이동시킨다.

### 💬 코멘트 :
생각은 쉽지만 구현은 어려운 문제, 개인적으로 이런 문제를 아주 좋아한다.

### 📄 코드
```java
import java.io.*;
import java.util.*;

public class BOJ17144 {

    static String[] line;
    static int R, C, T; // R = 맵의 행 수, C = 맵의 열 수, T = 시간
    static int[][] board; // 먼지 맵
    static int[] aPRows = new int[2]; // 공기청정기의 행, 두 개
    static Queue<Info> q = new LinkedList<>(); // 현재 시간에 확산할 먼지를 담는 큐
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static class Info { // 확산된 먼지 정보 클래스
        int r;
        int c;
        int amount;

        public Info(int r, int c, int amount) {
            this.r = r;
            this.c = c;
            this.amount = amount;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        line = br.readLine().split(" ");
        R = Integer.parseInt(line[0]);
        C = Integer.parseInt(line[1]);
        T = Integer.parseInt(line[2]);

        board = new int[R][C];
        int a = 0; // 공기청정기 인덱스
        for (int i = 0; i < R; i++) {
            line = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                board[i][j] = Integer.parseInt(line[j]);
                if (board[i][j] == -1) {
                    aPRows[a++] = i;
                }
            }
        }

        while (T-- > 0) {
            spread(); // 먼지 확산
            ventilate(); // 공기청정기 작동
        }

        int sum = 0; // T초 후의 전체 먼지량을 담는 변수
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == -1) continue;
                sum += board[i][j];
            }
        }
        System.out.println(sum);
    }

    /**
     * 먼지를 확산시키는 메서드<br>
     * 현재 시간 t에 먼지 맵을 순회하며, 먼지를 확산시킴<br>
     * 확산된 먼지는 큐에 넣어지고, 현재 먼지의 양을 확산된 먼지의 양만큼 뺌<br>
     * 먼지 확산 정보를 전부 담으면, 큐를 비워내면서 먼지 맵에 확산 정보를 넣어줌<br>
     * @see week2.BOJ17144#evaluateDust
     */
    private static void spread() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] <= 0) continue;
                board[i][j] -= evaluateDust(i, j, board[i][j]);
            }
        }

        while (!q.isEmpty()) {
            Info d = q.remove();
            board[d.r][d.c] += d.amount;
        }
    }

    /**
     * 현재 위치의 먼지의 양을 받아 4방향으로 먼지를 확산시키는 메서드<br>
     * 확산시킬 먼지 정보를 큐에 넣고, 확산된 먼지의 양을 반환해 현재 위치에 먼지를 빼줌
     * @param r : 현재 위치의 행
     * @param c : 현재 위치의 열
     * @param curAmount : 현재 위치의 먼지의 양
     * @return sum : 확산된 먼지의 양
     */
    private static int evaluateDust(int r, int c, int curAmount) {
        int sum = 0;
        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if (nr < 0 || R <= nr || nc < 0 || C <= nc) continue;
            if (board[nr][nc] == -1) continue;
            q.add(new Info(nr, nc, curAmount / 5));
            sum += curAmount / 5;
        }
        return sum;
    }

    /**
     * 공기청정기에 먼지가 들어오는 방향부터 나가는 방향까지 돌면서 먼지를 이동시키는 메서드
     */
    private static void ventilate() {
        // 반시계
        int north = aPRows[0];

        for (int r = north - 2; r >= 0; r--) {
            board[r + 1][0] = board[r][0];
        }

        for (int c = 1; c < C; c++) {
            board[0][c - 1] = board[0][c];
        }

        for (int r = 1; r <= north; r++) {
            board[r - 1][C - 1] = board[r][C - 1];
        }

        for (int c = C - 2; c >= 1; c--) {
            board[north][c + 1] = board[north][c];
        }
        board[north][1] = 0;

        //시계
        int south = aPRows[1];

        for (int r = south + 2; r < R; r++) {
            board[r - 1][0] = board[r][0];
        }

        for (int c = 1; c < C; c++) {
            board[R - 1][c - 1] = board[R - 1][c];
        }

        for (int r = R - 2; r >= south; r--) {
            board[r + 1][C - 1] = board[r][C - 1];
        }

        for (int c = C - 2; c >= 1; c--) {
            board[south][c + 1] = board[south][c];
        }
        board[south][1] = 0;
    }
}
```